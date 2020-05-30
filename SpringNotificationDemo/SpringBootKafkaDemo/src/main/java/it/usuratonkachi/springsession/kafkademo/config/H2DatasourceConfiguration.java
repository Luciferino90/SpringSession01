package it.usuratonkachi.springsession.kafkademo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "it.usuratonkachi.springsession.kafkademo.datasource")
public class H2DatasourceConfiguration {
}
