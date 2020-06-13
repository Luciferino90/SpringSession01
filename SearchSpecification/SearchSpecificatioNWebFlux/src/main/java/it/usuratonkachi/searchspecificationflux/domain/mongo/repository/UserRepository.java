package it.usuratonkachi.searchspecificationflux.domain.mongo.repository;

import it.usuratonkachi.searchspecificationflux.domain.mongo.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Mono<User> findByUserid(String userid);
    Mono<Long> countByUsernameRegex(String username);
    Flux<User> findByUsernameRegex(String username, Pageable pageable);

}
