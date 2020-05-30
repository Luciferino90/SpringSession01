package it.usuratonkachi.springsession.kafkademo.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaChannels {

	String NOTIFICATION_TOPIC_IN = "notification-in";
	String MAIL_OUT = "mail-out";

	@Input(NOTIFICATION_TOPIC_IN)
	SubscribableChannel notificationInputChannel();

	@Output(MAIL_OUT)
	SubscribableChannel mailOutputChannel();

}
