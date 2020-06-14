package it.usuratonkachi.searchspecificationflux.handler;

import it.usuratonkachi.searchspecificationflux.dto.request.CompanyInsertRequestDto;
import it.usuratonkachi.searchspecificationflux.dto.request.CompanyUpdateRequestDto;
import it.usuratonkachi.searchspecificationflux.dto.request.SearchCriteriaRequestDto;
import it.usuratonkachi.searchspecificationflux.mapper.CompanyMapper;
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
public class CompanyHandler {

    private final CompanyMapper companyMapper;
    private final CompanyService companyService;

    public Mono<ServerResponse> search(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(SearchCriteriaRequestDto.class)
                .zipWith(Mono.just(ServerRequestUtils.extractPageable(serverRequest)))
                .flatMap(TupleUtils.function(companyService::search))
                .flatMap(TupleUtils.function((count, pageable, companyFlux) ->
                        companyFlux.map(companyMapper::mapperEntityToResponseDto)
                                .collectList()
                                .map(companyResponseDtos -> new PageImpl<>(companyResponseDtos, pageable, count))
                ))
                .flatMap(company -> ServerResponse.ok().body(BodyInserters.fromValue(company)));
    }

    public Mono<ServerResponse> getCompanyByBusinessNameLike(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("businessname"))
                .zipWith(Mono.just(ServerRequestUtils.extractPageable(serverRequest)))
                .flatMap(TupleUtils.function(companyService::findByBusinessNameLike))
                .flatMap(TupleUtils.function((count, pageable, companyFlux) ->
                        companyFlux.map(companyMapper::mapperEntityToResponseDto)
                                .collectList()
                                .map(companyResponseDtos -> new PageImpl<>(companyResponseDtos, pageable, count))
                ))
                .flatMap(company -> ServerResponse.ok().body(BodyInserters.fromValue(company)));
    }

    public Mono<ServerResponse> getCompany(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("companyid"))
                .flatMap(companyService::findByCompanyId)
                .map(companyMapper::mapperEntityToResponseDto)
                .flatMap(user -> ServerResponse.ok().body(BodyInserters.fromValue(user)));
    }

    public Mono<ServerResponse> insertCompany(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CompanyInsertRequestDto.class)
                .map(companyMapper::mapperInsertDtoToEntity)
                .flatMap(companyService::create)
                .map(companyMapper::mapperEntityToResponseDto)
                .flatMap(user -> ServerResponse.ok().body(BodyInserters.fromValue(user)));
    }

    public Mono<ServerResponse> updateCompany(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CompanyUpdateRequestDto.class)
                .map(companyMapper::mapperUpdateDtoToEntity)
                .flatMap(companyService::update)
                .map(companyMapper::mapperEntityToResponseDto)
                .flatMap(user -> ServerResponse.ok().body(BodyInserters.fromValue(user)));
    }

}
