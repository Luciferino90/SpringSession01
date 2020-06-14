package it.usuratonkachi.searchspecificationflux.service;

import it.usuratonkachi.searchspecificationflux.domain.mysql.entity.UserJpa;
import it.usuratonkachi.searchspecificationflux.domain.mysql.repository.UserJpaRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;
import reactor.util.function.Tuples;

@Service
@RequiredArgsConstructor
public class UserJpaReactive {

    @Getter
    private final UserJpaRepository userJpaRepository;

    public Mono<UserJpa> findByUserid(String userid) {
        return Mono.justOrEmpty(userJpaRepository.findByUserId(userid));
    }

    public Mono<Page<UserJpa>> findByUsernameLike(String username, Pageable pageable){
        return Mono.just(userJpaRepository.findByUsernameLike(username, pageable));
    }

    public Mono<UserJpa> save(UserJpa userJpa) {
        return Mono.just(userJpaRepository.save(userJpa));
    }


}
