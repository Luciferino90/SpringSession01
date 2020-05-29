package it.usuratonkachi.springsession.kafkademo.service;

import it.usuratonkachi.springsession.kafkademo.datasource.entity.Template;
import it.usuratonkachi.springsession.kafkademo.datasource.entity.TemplateEventConfig;
import it.usuratonkachi.springsession.kafkademo.datasource.entity.TemplateField;
import it.usuratonkachi.springsession.kafkademo.datasource.entity.User;
import it.usuratonkachi.springsession.kafkademo.datasource.repository.TemplateEventConfigRepository;
import it.usuratonkachi.springsession.kafkademo.datasource.repository.TemplateFieldRepository;
import it.usuratonkachi.springsession.kafkademo.datasource.repository.TemplateRepository;
import it.usuratonkachi.springsession.kafkademo.datasource.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MockService {

    private final UserRepository userRepository;
    private final SpringTemplateEngine thymeleafTE;
    private final TemplateEventConfigRepository templateEventConfigRepository;
    private final TemplateRepository templateRepository;
    private final TemplateFieldRepository templateFieldRepository;

    @PostConstruct
    public void init(){
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setNotificationEnabled(true);
        user.setUsername("luca.fanciullini@staff.aruba.it");
        userRepository.saveAndFlush(user);

        User userTwo = new User();
        userTwo.setId(UUID.randomUUID().toString());
        userTwo.setNotificationEnabled(false);
        userTwo.setUsername("foo@fighter.it");
        userRepository.saveAndFlush(userTwo);

        Template template = new Template();
        template.setId(UUID.randomUUID().toString());
        templateRepository.saveAndFlush(template);

        TemplateEventConfig templateEventConfig = new TemplateEventConfig();
        templateEventConfig.setEventTypeId("PASSWORD_CHANGE");
        templateEventConfig.setNotificationEnabled(true);
        templateEventConfig.setTemplateId(template.getId());
        templateEventConfig.setId(UUID.randomUUID().toString());
        templateEventConfigRepository.saveAndFlush(templateEventConfig);

        TemplateField from = new TemplateField();
        from.setId(UUID.randomUUID().toString());
        from.setTemplateId(template.getId());
        from.setName("from");
        from.setValue("[(${email_from})]");
        templateFieldRepository.saveAndFlush(from);

        TemplateField to = new TemplateField();
        to.setId(UUID.randomUUID().toString());
        to.setTemplateId(template.getId());
        to.setName("to");
        to.setValue("[(${USER_PEC})]");
        templateFieldRepository.saveAndFlush(to);

        TemplateField subject = new TemplateField();
        subject.setId(UUID.randomUUID().toString());
        subject.setTemplateId(template.getId());
        subject.setName("subject");
        subject.setValue("Password change request from [(${username})]");
        templateFieldRepository.saveAndFlush(subject);

        TemplateField body = new TemplateField();
        body.setId(UUID.randomUUID().toString());
        body.setTemplateId(template.getId());
        body.setName("body");
        body.setValue("<h1> Ciao [[${username}]]</h1>\n" +
                "<br>\n" +
                "<h2> [[${subject}]] @ [[${date}]]</h2>\n"
        );
        templateFieldRepository.saveAndFlush(body);
    }

}
