package it.usuratonkachi.staticinjection.bean;

import it.usuratonkachi.staticinjection.wrapper.Wrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Slf4j
@Component
@RequestScope
@RequiredArgsConstructor
public class RequestBean {

    private Wrapper wrapper;

    private final SessionBean sessionBean;

    @PostConstruct
    public void init(){
        log.info("New Request received");
        String requestId = UUID.randomUUID().toString();
        wrapper = new Wrapper().setRequestId(requestId).setSessionId(sessionBean.getSessionValue());
    }

    public Wrapper getRequestValue(){
        return wrapper;
    }

}
