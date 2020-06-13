package it.usuratonkachi.searchspecificationflux.service;

import it.usuratonkachi.searchspecificationflux.domain.mysql.entity.CompanyJpa;
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
public class CompanyJpaService {

    private final CompanyJpaReactive companyJpaReactive;


    @Bean
    @Lazy
    private SearchCriteriaJpaService<CompanyJpa> companySearchCriteriaJpaService(){
        return new SearchCriteriaJpaService<>(CompanyJpa.class, companyJpaReactive.getCompanyRepository());
    }

    public Mono<Tuple3<Long, Pageable, Flux<CompanyJpa>>> search(SearchCriteriaRequestDto searchCriteriaRequestDto, Pageable pageable) {
        return companySearchCriteriaJpaService().search(searchCriteriaRequestDto.getSearchCriteriaList(), pageable);
    }

    public Mono<Tuple3<Long, Pageable, Flux<CompanyJpa>>> findByBusinessNameLike(String businessName, Pageable pageable) {
        return companyJpaReactive.findByBusinessnameLike(businessName, pageable);
    }

    public Mono<CompanyJpa> findByCompanyId(String companyid) {
        return companyJpaReactive.findByCompanyid(companyid)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new RuntimeException("Company not found for id " + companyid))));
    }

    public Mono<CompanyJpa> create(CompanyJpa company) {
        company.setCompanyId(UUID.randomUUID().toString());
        return companyJpaReactive.save(company);
    }

    public Mono<CompanyJpa> update(CompanyJpa company) {
        return findByCompanyId(company.getCompanyId())
                .doOnNext(oldcompany -> oldcompany
                        .setAddress(company.getAddress())
                        .setBusinessname(company.getBusinessname())
                        .setCap(company.getCap())
                        .setPiva(company.getPiva()))
                .flatMap(companyJpaReactive::save);
    }



}
