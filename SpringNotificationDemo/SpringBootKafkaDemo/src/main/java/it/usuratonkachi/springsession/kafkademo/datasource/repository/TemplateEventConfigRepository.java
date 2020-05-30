package it.usuratonkachi.springsession.kafkademo.datasource.repository;

import it.usuratonkachi.springsession.kafkademo.datasource.entity.TemplateEventConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemplateEventConfigRepository extends JpaRepository<TemplateEventConfig, String> {

	Optional<TemplateEventConfig> findByEventTypeId(String eventTypeId);

}
