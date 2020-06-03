package it.usuratonkachi.springsession.kafkademo.listener;

import it.usuratonkachi.springsession.kafkademo.config.KafkaChannels;
import it.usuratonkachi.springsession.kafkademo.datasource.entity.TemplateEventConfig;
import it.usuratonkachi.springsession.kafkademo.datasource.entity.User;
import it.usuratonkachi.springsession.kafkademo.datasource.repository.TemplateEventConfigRepository;
import it.usuratonkachi.springsession.kafkademo.datasource.repository.TemplateFieldRepository;
import it.usuratonkachi.springsession.kafkademo.datasource.repository.UserRepository;
import it.usuratonkachi.springsession.kafkademo.dto.ExampleMessage;
import it.usuratonkachi.springsession.kafkademo.dto.MessageDto;
import it.usuratonkachi.springsession.kafkademo.service.NotificationPecService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.Payloads;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.stream.Collectors;

import static it.usuratonkachi.springsession.kafkademo.config.KafkaChannels.MAIL_OUT;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationListener {

	private final UserRepository userRepository;
	private final SpringTemplateEngine thymeleafTE;
	private final TemplateEventConfigRepository templateEventConfigRepository;
	private final TemplateFieldRepository templateFieldRepository;
	private final NotificationPecService notificationPecService;

	@Autowired
	@Qualifier(MAIL_OUT)
	private MessageChannel mailOutChannel;

	@SneakyThrows
	@StreamListener(KafkaChannels.NOTIFICATION_TOPIC_IN)
	public void notificationListener(@Payloads ExampleMessage message, @Headers Map<String, Object> headersMap) {
		final String username = message.getUsername();
		userRepository.findByUsernameAndNotificationEnabled(username, true)
				.ifPresent(user -> sendTxtNotification(user, message, headersMap));
	}

	private void  sendTxtNotification(User user, ExampleMessage message, Map<String, Object> headersMap) {
		log.info("Sending notification to user " + user.getUsername());

		Map<String, Object> map = Map.of(
				"date", ZonedDateTime.now(),
				"username", user.getUsername(),
				"email_from", "test.sender@pec.it",
				"USER_PEC", user.getUsername()
		);

		message.setTemplateMap(map);

		Context thymeleafContext = new Context();
		thymeleafContext.setVariables(message.getTemplateMap());

		TemplateEventConfig templateEventConfig = templateEventConfigRepository.findByEventTypeId(message.getEventTypeId())
				.orElseThrow(() -> new RuntimeException("No template found for id " + message.getEventTypeId()));

		if (!templateEventConfig.getNotificationEnabled())
			return;

		Map<String, Object> partsMap = templateFieldRepository.findByTemplateId(templateEventConfig.getTemplateId())
				.stream()
				.map(templateField -> Tuples.of(templateField.getName(), thymeleafTE.process(templateField.getValue(), thymeleafContext)))
				.collect(Collectors.groupingBy(
						Tuple2::getT1,
						Collectors.collectingAndThen(
								Collectors.toList(),
								values -> values.get(0).getT2()
						)
				));
		MessageDto messageDto = MessageDto.createFromMap(partsMap);
		System.out.println(messageDto.toString());

		Message<MessageDto> dtoGenericMessage = MessageBuilder
				.withPayload(messageDto)
				.copyHeaders(headersMap)
				.build();

		mailOutChannel.send(dtoGenericMessage);
		notificationPecService.saveNotification(messageDto);
		log.info("Notification sent");
	}

}
