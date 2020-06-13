package it.usuratonkachi.searchspecificationflux.handler;

import it.usuratonkachi.searchspecificationflux.dto.request.UserInsertRequestDto;
import it.usuratonkachi.searchspecificationflux.dto.request.UserUpdateRequestDto;
import it.usuratonkachi.searchspecificationflux.mapper.UserMapper;
import it.usuratonkachi.searchspecificationflux.service.UserService;
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
public class UserHandler {

    private final UserMapper userMapper;
    private final UserService userService;

    public Mono<ServerResponse> getUserByUsernameLike(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("username"))
                .zipWith(Mono.just(ServerRequestUtils.extractPageable(serverRequest)))
                .flatMap(TupleUtils.function(userService::findByUsernameLike))
                .flatMap(TupleUtils.function((count, pageable, userFlux) ->
                        userFlux.map(userMapper::mapperEntityToResponseDto)
                                .collectList()
                                .map(userResponseDtos -> new PageImpl<>(userResponseDtos, pageable, count))
                ))
                .flatMap(company -> ServerResponse.ok().body(BodyInserters.fromValue(company)));
    }

    public Mono<ServerResponse> getUser(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("userid"))
                .flatMap(userService::findByUserId)
                .map(userMapper::mapperEntityToResponseDto)
                .flatMap(user -> ServerResponse.ok().body(BodyInserters.fromValue(user)));
    }

    public Mono<ServerResponse> insertUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UserInsertRequestDto.class)
                .map(userMapper::mapperInsertDtoToEntity)
                //.zipWith(Mono.just(ServerRequestUtils.extractPageable(serverRequest)))
                .flatMap(userService::create)
                .map(userMapper::mapperEntityToResponseDto)
                .flatMap(user -> ServerResponse.ok().body(BodyInserters.fromValue(user)));
    }

    public Mono<ServerResponse> updateUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UserUpdateRequestDto.class)
                .map(userMapper::mapperUpdateDtoToEntity)
                //.zipWith(Mono.just(ServerRequestUtils.extractPageable(serverRequest)))
                .flatMap(userService::update)
                .map(userMapper::mapperEntityToResponseDto)
                .flatMap(user -> ServerResponse.ok().body(BodyInserters.fromValue(user)));
    }

}
