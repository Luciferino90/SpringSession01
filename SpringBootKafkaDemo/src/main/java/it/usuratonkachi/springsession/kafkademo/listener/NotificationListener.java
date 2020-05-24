package it.usuratonkachi.springsession.kafkademo.listener;

import it.usuratonkachi.springsession.kafkademo.config.KafkaChannels;
import it.usuratonkachi.springsession.kafkademo.datasource.entity.User;
import it.usuratonkachi.springsession.kafkademo.datasource.repository.UserRepository;
import it.usuratonkachi.springsession.kafkademo.dto.ExampleMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.Payloads;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.annotation.PostConstruct;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationListener {

	private final UserRepository userRepository;
	private final SpringTemplateEngine thymeleafTE;

	@SneakyThrows
	@StreamListener(KafkaChannels.NOTIFICATION_TOPIC_IN)
	public void notificationListener(@Payloads ExampleMessage message, @Headers Map<String, Object> map) {
		final String username = message.getUsername();
		userRepository.findByUsernameAndNotificationEnabled(username, true)
				.ifPresent(user -> sendNotification(user, message.getTemplateMap()));
	}

	private void sendNotification(User user, Map<String, Object> templateMap){
		log.info("Sending notification to user " + user.getUsername());
		templateMap = Map.of(
				"subject", "Template di prova",
				"date", ZonedDateTime.now(),
				"username", user.getUsername()
		);

		Context thymeleafContext = new Context();
		thymeleafContext.setVariables(templateMap);

		//final String message = thymeleafTE.process("mail.html", thymeleafContext);

		String template = "<h1> Ciao [[${username}]]</h1>\n" +
				"<br>\n" +
				"<h2> [[${subject}]] @ [[${date}]]</h2>\n";

		final String messageString = thymeleafTE.process(template, thymeleafContext);

		//System.out.println(message);
		System.out.println("\n\n\n");
		System.out.println(messageString);

		log.info("Notification sent");

	}

	@PostConstruct
	public void init(){
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setNotificationEnabled(true);
		user.setUsername("luca.fanciullini@pec.it");
		userRepository.saveAndFlush(user);

		User userTwo = new User();
		userTwo.setId(UUID.randomUUID().toString());
		userTwo.setNotificationEnabled(false);
		userTwo.setUsername("luca.fanciulli@pec.it");
		userRepository.saveAndFlush(userTwo);
	}

}
