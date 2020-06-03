package it.usuratonkachi.springsession.kafkademo.mapper;

import it.usuratonkachi.springsession.kafkademo.datasource.entity.NotificationPec;
import it.usuratonkachi.springsession.kafkademo.dto.MessageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;


@Mapper(config = MapperSharedConfig.class, imports = {UUID.class}, componentModel = "spring")
public interface MessageDtoMapper {

    @Mapping(target = "id", expression = "java(UUID.randomUUID().toString())")
    @Mapping(target = "sent", constant = "true")
    @Mapping(target = "content", source = "body")
    NotificationPec mapMessageDtoToNotificationPec(MessageDto messageDto);

}
