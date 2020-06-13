package it.usuratonkachi.searchspecificationflux.handler;

import it.usuratonkachi.searchspecificationflux.dto.request.CompanyInsertRequestDto;
import it.usuratonkachi.searchspecificationflux.dto.request.CompanyUpdateRequestDto;
import it.usuratonkachi.searchspecificationflux.mapper.CompanyMapper;
import it.usuratonkachi.searchspecificationflux.service.CompanyJpaService;
import it.usuratonkachi.searchspecificationflux.service.CompanyService;
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
public class CompanyJpaHandler {

    private final CompanyMapper companyMapper;
    private final CompanyJpaService companyService;

    public Mono<ServerResponse> getCompanyByBusinessNameLike(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("businessname"))
                .zipWith(Mono.just(ServerRequestUtils.extractPageable(serverRequest)))
                .flatMap(TupleUtils.function(companyService::findByBusinessNameLike))
                .flatMap(TupleUtils.function((count, pageable, companyFlux) ->
                        companyFlux.map(companyMapper::mapperEntityToResponseDtoJpa)
                                .collectList()
                                .map(companyResponseDtos -> new PageImpl<>(companyResponseDtos, pageable, count))
                ))
                .flatMap(company -> ServerResponse.ok().body(BodyInserters.fromValue(company)));
    }

    public Mono<ServerResponse> getCompany(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("companyid"))
                .flatMap(companyService::findByCompanyId)
                .map(companyMapper::mapperEntityToResponseDtoJpa)
                .flatMap(user -> ServerResponse.ok().body(BodyInserters.fromValue(user)));
    }

    public Mono<ServerResponse> insertCompany(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CompanyInsertRequestDto.class)
                .map(companyMapper::mapperInsertDtoToEntityJpa)
                .flatMap(companyService::create)
                .map(companyMapper::mapperEntityToResponseDtoJpa)
                .flatMap(user -> ServerResponse.ok().body(BodyInserters.fromValue(user)));
    }

    public Mono<ServerResponse> updateCompany(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CompanyUpdateRequestDto.class)
                .map(companyMapper::mapperUpdateDtoToEntityJpa)
                .flatMap(companyService::update)
                .map(companyMapper::mapperEntityToResponseDtoJpa)
                .flatMap(user -> ServerResponse.ok().body(BodyInserters.fromValue(user)));
    }

}
