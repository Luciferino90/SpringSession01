package it.usuratonkachi.staticinjection.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Slf4j
@Component
@SessionScope
public class SessionBean {

    private String value;

    @PostConstruct
    public void init(){
        log.info("New Request received");
        value = UUID.randomUUID().toString();
    }

    public String getSessionValue(){
        return value;
    }

}
