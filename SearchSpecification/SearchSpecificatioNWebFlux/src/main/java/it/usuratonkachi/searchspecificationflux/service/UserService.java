package it.usuratonkachi.searchspecificationflux.service;

import it.usuratonkachi.searchspecificationflux.domain.mongo.entity.User;
import it.usuratonkachi.searchspecificationflux.domain.mongo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;
import reactor.util.function.Tuples;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Mono<Tuple3<Long, Pageable, Flux<User>>> findByUsernameLike(String businessName, Pageable pageable) {
        return userRepository.countByUsernameRegex(businessName)
                .filter(count -> count > 0)
                .map(count -> Tuples.of(count, pageable, userRepository.findByUsernameRegex(businessName, pageable)));
    }

    public Mono<User> findByUserId(String userid) {
        return userRepository.findByUserid(userid)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new RuntimeException("user not found for id " + userid))));
    }

    public Mono<User> create(User user) {
        user.setUserid(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    public Mono<User> update(User user) {
        return findByUserId(user.getUserid())
                .doOnNext(olduser -> {
                    olduser
                            .setUsername(user.getUsername())
                            .setCompanyid(user.getCompanyid())
                            .setFirstname(user.getFirstname())
                            .setLastname(user.getLastname());
                })
                .flatMap(userRepository::save);
    }

}
