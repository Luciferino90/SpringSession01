package it.usuratonkachi.springsession.kafkademo.datasource.repository;

import it.usuratonkachi.springsession.kafkademo.datasource.entity.TemplateField;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemplateFieldRepository extends JpaRepository<TemplateField, String> {

    List<TemplateField> findByTemplateId(String templateId);

}
