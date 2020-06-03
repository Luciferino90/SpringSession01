package it.usuratonkachi.springsession.kafkademo.datasource.repository;

import it.usuratonkachi.springsession.kafkademo.datasource.entity.NotificationPec;
import it.usuratonkachi.springsession.kafkademo.datasource.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationPecRepository extends JpaRepository<NotificationPec, String> {

}
