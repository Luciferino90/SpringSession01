package it.usuratonkachi.searchspecificationflux.domain.mysql.repository;

import it.usuratonkachi.searchspecificationflux.domain.mysql.entity.UserJpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpa, String>, JpaSpecificationExecutor<UserJpa> {

    Optional<UserJpa> findByUserId(String userid);
    Page<UserJpa> findByUsernameLike(String username, Pageable pageable);


}
