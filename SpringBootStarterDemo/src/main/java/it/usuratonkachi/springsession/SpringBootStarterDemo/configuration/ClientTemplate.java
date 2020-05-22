package it.usuratonkachi.springsession.SpringBootStarterDemo.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ClientTemplate {

    private final ClientProperties clientProperties;

    public boolean login(){
        log.info(String.format("Logging to %s with user %s and password %s", clientProperties.getUrl(), clientProperties.getUsername(), "*****" ));
        return true;
    }

}
