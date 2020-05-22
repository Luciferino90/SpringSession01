package it.usuratonkachi.springsession.springbootsession.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Component
@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionBean implements Serializable {

    private static final long serialVersionUID = 20200506_0820L;

    @Getter @Setter
    private int customSession;

}
