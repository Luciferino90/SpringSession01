package it.arubapec.esecurity.SpringBootStarterDemo.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "service.client", value = "enabled", havingValue = "true")
public class ClientAutoconfiguration {

    private final ClientProperties clientProperties;

    @Bean
    public ClientTemplate client(){
        return new ClientTemplate(clientProperties);
    }

}
