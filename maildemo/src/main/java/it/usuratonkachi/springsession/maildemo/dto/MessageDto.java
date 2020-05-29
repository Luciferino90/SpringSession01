package it.usuratonkachi.springsession.maildemo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageDto implements Serializable {


    private static final long serializeUid = 20200518_1525L;

    private String from;
    private String to;
    private String subject;
    private String body;


}
