package it.usuratonkachi.searchspecificationflux.domain.mysql.repository;

import it.usuratonkachi.searchspecificationflux.domain.mongo.entity.User;
import it.usuratonkachi.searchspecificationflux.domain.mysql.entity.UserJpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpa, String>, JpaSpecificationExecutor<UserJpa> {

    Optional<UserJpa> findByUserid(String userid);
    Page<UserJpa> findByUsernameLike(String username, Pageable pageable);


}
