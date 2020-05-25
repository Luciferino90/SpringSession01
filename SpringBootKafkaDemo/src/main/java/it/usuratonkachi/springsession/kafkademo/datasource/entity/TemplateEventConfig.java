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
@Entity(name = "template_event_config")
public class TemplateEventConfig implements Serializable {

	private static final long serializeUid = 20200518_1525L;

	@Id
	private String id;
	@Column(name = "template_id")
	private String templateId;
	@Column(name = "event_type_id")
	private String eventTypeId;
	@Column(name = "notification_enabled")
	private Boolean notificationEnabled;

}
