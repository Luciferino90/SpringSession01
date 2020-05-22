package it.usuratonkachi.springsession.kafkademo.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaChannels {

	String NOTIFICATION_TOPIC_IN = "notification-in";

	@Input(NOTIFICATION_TOPIC_IN)
	SubscribableChannel notificationInputChannel();

}
