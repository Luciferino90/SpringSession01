package it.usuratonkachi.searchspecificationflux.service;

import it.usuratonkachi.searchspecificationflux.domain.mysql.entity.CompanyJpa;
import it.usuratonkachi.searchspecificationflux.domain.mysql.entity.UserJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserJpaService {

    private final UserJpaReactive userJpaReactive;

    public Mono<Tuple3<Long, Pageable, Flux<UserJpa>>> findByUsernameLike(String username, Pageable pageable) {
        return userJpaReactive.findByUsernameLike(username, pageable);
    }

    public Mono<UserJpa> findByUserId(String userid) {
        return userJpaReactive.findByUserid(userid)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new RuntimeException("Company not found for id " + userid))));
    }

    public Mono<UserJpa> create(UserJpa userJpa) {
        userJpa.setCompanyid(UUID.randomUUID().toString());
        return userJpaReactive.save(userJpa);
    }

    public Mono<UserJpa> update(UserJpa user) {
        return findByUserId(user.getUserid())
                .doOnNext(olduser -> {
                    olduser
                            .setUsername(user.getUsername())
                            .setCompanyid(user.getCompanyid())
                            .setFirstname(user.getFirstname())
                            .setLastname(user.getLastname());
                })
                .flatMap(userJpaReactive::save);
    }



}
