package it.usuratonkachi.searchspecificationflux.handler;

import it.usuratonkachi.searchspecificationflux.dto.request.SearchCriteriaRequestDto;
import it.usuratonkachi.searchspecificationflux.dto.request.UserInsertRequestDto;
import it.usuratonkachi.searchspecificationflux.dto.request.UserUpdateRequestDto;
import it.usuratonkachi.searchspecificationflux.mapper.UserMapper;
import it.usuratonkachi.searchspecificationflux.service.UserJpaService;
import it.usuratonkachi.searchspecificationflux.utils.ServerRequestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.function.TupleUtils;

@Component
@RequiredArgsConstructor
public class UserJpaHandler {

    private final UserMapper userMapper;
    private final UserJpaService userService;

    public Mono<ServerResponse> search(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(SearchCriteriaRequestDto.class)
                .zipWith(Mono.just(ServerRequestUtils.extractPageable(serverRequest)))
                .flatMap(TupleUtils.function(userService::search))
                .flatMap(TupleUtils.function((count, pageable, companyFlux) ->
                        companyFlux.map(userMapper::mapperEntityToResponseDtoJpa)
                                .collectList()
                                .map(companyResponseDtos -> new PageImpl<>(companyResponseDtos, pageable, count))
                ))
                .flatMap(company -> ServerResponse.ok().body(BodyInserters.fromValue(company)));
    }

    public Mono<ServerResponse> getUserByUsernameLike(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("username"))
                .zipWith(Mono.just(ServerRequestUtils.extractPageable(serverRequest)))
                .flatMap(TupleUtils.function(userService::findByUsernameLike))
                .flatMap(TupleUtils.function((count, pageable, userFlux) ->
                        userFlux.map(userMapper::mapperEntityToResponseDtoJpa)
                                .collectList()
                                .map(userResponseDtos -> new PageImpl<>(userResponseDtos, pageable, count))
                ))
                .flatMap(company -> ServerResponse.ok().body(BodyInserters.fromValue(company)));
    }

    public Mono<ServerResponse> getUser(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("userid"))
                .flatMap(userService::findByUserId)
                .map(userMapper::mapperEntityToResponseDtoJpa)
                .flatMap(user -> ServerResponse.ok().body(BodyInserters.fromValue(user)));
    }

    public Mono<ServerResponse> insertUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UserInsertRequestDto.class)
                .map(userMapper::mapperInsertDtoToEntityJpa)
                .flatMap(userService::create)
                .map(userMapper::mapperEntityToResponseDtoJpa)
                .flatMap(user -> ServerResponse.ok().body(BodyInserters.fromValue(user)));
    }

    public Mono<ServerResponse> updateUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UserUpdateRequestDto.class)
                .map(userMapper::mapperUpdateDtoToEntityJpa)
                .flatMap(userService::update)
                .map(userMapper::mapperEntityToResponseDtoJpa)
                .flatMap(user -> ServerResponse.ok().body(BodyInserters.fromValue(user)));
    }

}
