package it.usuratonkachi.springsession.kafkademo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class ExampleMessage implements Serializable {


	private static final long serializeUid = 20200518_1525L;

	private String eventTypeId;
	private String username; // pecaddress
	private Map<String, Object> templateMap;

}
