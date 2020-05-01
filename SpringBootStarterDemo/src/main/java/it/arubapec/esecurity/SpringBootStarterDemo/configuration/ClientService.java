package it.arubapec.esecurity.SpringBootStarterDemo.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "service.client", value = "enabled", havingValue = "true")
public class ClientService {

    private final ClientProperties clientProperties;

    public boolean login(){
        log.info(String.format("Logging to %s with user %s and password %s", clientProperties.getUrl(), clientProperties.getUsername(), "*****" ));
        return true;
    }

}
