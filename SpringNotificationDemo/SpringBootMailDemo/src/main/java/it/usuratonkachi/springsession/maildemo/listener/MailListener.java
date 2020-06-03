package it.usuratonkachi.springsession.maildemo.listener;

import it.usuratonkachi.springsession.maildemo.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Map;

import static it.usuratonkachi.springsession.maildemo.config.KafkaChannels.MAIL_TOPIC_IN;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailListener {

    private final JavaMailSenderImpl mailSender;

    @StreamListener(MAIL_TOPIC_IN)
    public void sendMail(@Payload MessageDto messageDto, @Headers Map<String, Object> headers){
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(messageDto.getTo());
            simpleMailMessage.setFrom(messageDto.getFrom());
            simpleMailMessage.setSubject(messageDto.getSubject());
            simpleMailMessage.setText(messageDto.getBody());

            mailSender.send(simpleMailMessage);
            log.info("Pec sent");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

}
