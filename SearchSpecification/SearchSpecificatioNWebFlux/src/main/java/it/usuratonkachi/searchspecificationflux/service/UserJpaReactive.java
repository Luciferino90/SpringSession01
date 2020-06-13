package it.usuratonkachi.searchspecificationflux.service;

import it.usuratonkachi.searchspecificationflux.domain.mongo.entity.Company;
import it.usuratonkachi.searchspecificationflux.domain.mysql.entity.UserJpa;
import it.usuratonkachi.searchspecificationflux.domain.mysql.repository.CompanyJpaRepository;
import it.usuratonkachi.searchspecificationflux.domain.mysql.repository.UserJpaRepository;
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

    private final UserJpaRepository userJpaRepository;

    public Mono<UserJpa> findByUserid(String userid) {
        return Mono.justOrEmpty(userJpaRepository.findByUserid(userid));
    }

    public Mono<Tuple3<Long, Pageable, Flux<UserJpa>>> findByUsernameLike(String username, Pageable pageable){
        Page<UserJpa> companies = userJpaRepository.findByUsernameLike(username, pageable);
        Long count = companies.getTotalElements();
        return Mono.just(Tuples.of(count, pageable, Flux.fromIterable(companies)));
    }

    public Mono<UserJpa> save(UserJpa userJpa) {
        return Mono.just(userJpaRepository.save(userJpa));
    }


}
