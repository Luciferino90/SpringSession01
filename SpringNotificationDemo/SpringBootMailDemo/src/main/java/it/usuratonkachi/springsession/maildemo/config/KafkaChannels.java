package it.usuratonkachi.springsession.maildemo.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaChannels {

    String MAIL_TOPIC_IN = "mail-in";

    @Input(MAIL_TOPIC_IN)
    SubscribableChannel notificationInputChannel();

}
