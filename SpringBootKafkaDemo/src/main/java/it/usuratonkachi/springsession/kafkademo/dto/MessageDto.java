package it.usuratonkachi.springsession.kafkademo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class MessageDto implements Serializable {


    private static final long serializeUid = 20200518_1525L;

    private String from;
    private String to;
    private String subject;
    private String body;

    public static MessageDto createFromMap(Map<String, Object> map){
        MessageDto messageDto = new MessageDto();
        messageDto.setFrom((String) map.getOrDefault("from", null));
        messageDto.setTo((String) map.getOrDefault("to", null));
        messageDto.setSubject((String) map.getOrDefault("subject", null));
        messageDto.setBody((String) map.getOrDefault("body", null));
        return messageDto;
    }

}
