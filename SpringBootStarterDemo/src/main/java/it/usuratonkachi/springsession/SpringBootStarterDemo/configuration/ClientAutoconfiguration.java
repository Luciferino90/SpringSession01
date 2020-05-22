package it.usuratonkachi.springsession.SpringBootStarterDemo.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@Slf4j
@RequiredArgsConstructor
@EnableConfigurationProperties(ClientProperties.class)
@ConditionalOnClass(ClientTemplate.class)
//@ConditionalOnProperty(prefix = "service.client", value = "enabled", havingValue = "true")
public class ClientAutoconfiguration {

    private final ClientProperties clientProperties;

    @Bean
    public ClientTemplate client(){
        return new ClientTemplate(clientProperties);
    }

}
