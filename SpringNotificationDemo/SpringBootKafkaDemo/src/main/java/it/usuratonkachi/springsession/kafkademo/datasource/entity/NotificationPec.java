package it.usuratonkachi.springsession.kafkademo.datasource.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
public class NotificationPec implements Serializable {

	private static final long serializeUid = 20200518_1525L;

	@Id
	private String id;
	private String from;
	private String to;
	private String subject;
	private String content;
	private boolean sent;

}
