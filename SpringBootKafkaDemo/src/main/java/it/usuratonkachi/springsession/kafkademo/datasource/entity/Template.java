package it.usuratonkachi.springsession.kafkademo.datasource.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "template")
public class Template implements Serializable {

	private static final long serializeUid = 20200518_1525L;

	@Id
	private String id;

}
