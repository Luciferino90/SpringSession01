package it.arubapec.esecurity.SpringBootStarterDemo.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "service.client")
public class ClientProperties {

    private String username;
    private String password;
    private String url;

}
