package it.usuratonkachi.springsession.kafkademo.listener;

import it.usuratonkachi.springsession.kafkademo.config.KafkaChannels;
import it.usuratonkachi.springsession.kafkademo.datasource.entity.Template;
import it.usuratonkachi.springsession.kafkademo.datasource.entity.TemplateEventConfig;
import it.usuratonkachi.springsession.kafkademo.datasource.entity.TemplateField;
import it.usuratonkachi.springsession.kafkademo.datasource.entity.User;
import it.usuratonkachi.springsession.kafkademo.datasource.repository.TemplateEventConfigRepository;
import it.usuratonkachi.springsession.kafkademo.datasource.repository.TemplateFieldRepository;
import it.usuratonkachi.springsession.kafkademo.datasource.repository.TemplateRepository;
import it.usuratonkachi.springsession.kafkademo.datasource.repository.UserRepository;
import it.usuratonkachi.springsession.kafkademo.dto.ExampleMessage;
import it.usuratonkachi.springsession.kafkademo.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.Payloads;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationListener {

	private final UserRepository userRepository;
	private final SpringTemplateEngine thymeleafTE;
	private final TemplateEventConfigRepository templateEventConfigRepository;
	private final TemplateFieldRepository templateFieldRepository;

	@Autowired
	@Qualifier(KafkaChannels.MAIL_OUT)
	private MessageChannel pecUploadQueueChannel;

	@SneakyThrows
	@StreamListener(KafkaChannels.NOTIFICATION_TOPIC_IN)
	public void notificationListener(@Payloads ExampleMessage message, @Headers Map<String, Object> map) {
		final String username = message.getUsername();
		userRepository.findByUsernameAndNotificationEnabled(username, true)
				.ifPresent(user -> sendTxtNotification(user, message));
	}

	private void  sendTxtNotification(User user, ExampleMessage message) {
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
		pecUploadQueueChannel.send(MessageBuilder.withPayload(messageDto).build());
		log.info("Notification sent");
	}

}
