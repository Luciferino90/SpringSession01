package it.usuratonkachi.springsession.kafkademo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;

@Configuration
public class ThymeleafConfiguration {

	@Bean
	public SpringResourceTemplateResolver thymeleafTemplateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setPrefix("/WEB-INF/views/mail/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML");
		templateResolver.setCharacterEncoding("UTF-8");
		return templateResolver;
	}

	@Bean
	public StringTemplateResolver stringTemplateResolver(){
		StringTemplateResolver stringTemplateResolver = new StringTemplateResolver();
		stringTemplateResolver.setTemplateMode(TemplateMode.HTML);
		return stringTemplateResolver;
	}

	@Bean
	public SpringTemplateEngine thymeleafTemplateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		//templateEngine.setTemplateResolver(thymeleafTemplateResolver());
		templateEngine.setTemplateResolver(stringTemplateResolver());
		return templateEngine;
	}

}
