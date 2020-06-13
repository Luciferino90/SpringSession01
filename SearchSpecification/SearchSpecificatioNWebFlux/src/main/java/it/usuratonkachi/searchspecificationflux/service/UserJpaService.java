package it.usuratonkachi.searchspecificationflux.service;

import it.usuratonkachi.searchspecificationflux.domain.mysql.entity.UserJpa;
import it.usuratonkachi.searchspecificationflux.domain.mysql.service.SearchCriteriaJpaService;
import it.usuratonkachi.searchspecificationflux.dto.request.SearchCriteriaRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
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

    @Bean
    @Lazy
    private SearchCriteriaJpaService<UserJpa> userSearchCriteriaJpaService(){
        return new SearchCriteriaJpaService<>(UserJpa.class, userJpaReactive.getUserJpaRepository());
    }

    public Mono<Tuple3<Long, Pageable, Flux<UserJpa>>> search(SearchCriteriaRequestDto searchCriteriaRequestDto, Pageable pageable) {
        return userSearchCriteriaJpaService().search(searchCriteriaRequestDto.getSearchCriteriaList(), pageable);
    }

    public Mono<Tuple3<Long, Pageable, Flux<UserJpa>>> findByUsernameLike(String username, Pageable pageable) {
        return userJpaReactive.findByUsernameLike(username, pageable);
    }

    public Mono<UserJpa> findByUserId(String userid) {
        return userJpaReactive.findByUserid(userid)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new RuntimeException("Company not found for id " + userid))));
    }

    public Mono<UserJpa> create(UserJpa userJpa) {
        userJpa.setCompanyId(UUID.randomUUID().toString());
        return userJpaReactive.save(userJpa);
    }

    public Mono<UserJpa> update(UserJpa user) {
        return findByUserId(user.getUserId())
                .doOnNext(olduser -> {
                    olduser
                            .setUsername(user.getUsername())
                            .setCompanyId(user.getCompanyId())
                            .setFirstname(user.getFirstname())
                            .setLastname(user.getLastname());
                })
                .flatMap(userJpaReactive::save);
    }



}
