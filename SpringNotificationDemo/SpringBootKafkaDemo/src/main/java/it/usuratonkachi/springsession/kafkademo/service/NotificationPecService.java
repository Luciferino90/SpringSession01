package it.usuratonkachi.springsession.kafkademo.service;

import it.usuratonkachi.springsession.kafkademo.datasource.entity.NotificationPec;
import it.usuratonkachi.springsession.kafkademo.datasource.repository.NotificationPecRepository;
import it.usuratonkachi.springsession.kafkademo.dto.MessageDto;
import it.usuratonkachi.springsession.kafkademo.mapper.MessageDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationPecService {

    private final MessageDtoMapper messageDtoMapper;
    private final NotificationPecRepository notificationPecRepository;

    public NotificationPec saveNotification(MessageDto messageDto) {
        NotificationPec notificationPec = messageDtoMapper.mapMessageDtoToNotificationPec(messageDto);
        return notificationPecRepository.save(notificationPec);
    }

}
