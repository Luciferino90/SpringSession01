package it.usuratonkachi.springsession.kafkademo.datasource.repository;

import it.usuratonkachi.springsession.kafkademo.datasource.entity.Template;
import it.usuratonkachi.springsession.kafkademo.datasource.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemplateRepository extends JpaRepository<Template, String> {

}
