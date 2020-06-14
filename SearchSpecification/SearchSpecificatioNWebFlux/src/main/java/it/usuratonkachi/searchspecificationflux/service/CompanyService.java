package it.usuratonkachi.searchspecificationflux.service;

import it.usuratonkachi.searchspecificationflux.domain.mongo.entity.Company;
import it.usuratonkachi.searchspecificationflux.domain.mongo.repository.CompanyRepository;
import it.usuratonkachi.searchspecificationflux.domain.mongo.service.SearchCriteriaMongoService;
import it.usuratonkachi.searchspecificationflux.dto.request.SearchCriteriaRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;
import reactor.util.function.Tuples;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Bean
    @Lazy
    private SearchCriteriaMongoService<Company> companySearchCriteriaService(){
        return new SearchCriteriaMongoService<>(Company.class, reactiveMongoTemplate);
    }

    public Mono<Tuple3<Long, Pageable, Flux<Company>>> search(SearchCriteriaRequestDto searchCriteriaRequestDto, Pageable pageable){
        return companySearchCriteriaService().search(searchCriteriaRequestDto.getSearchCriteriaList(), pageable)
                .switchIfEmpty(Mono.defer(() -> Mono.just(Tuples.of(0L, pageable, Flux.empty()))));
    }

    public Mono<Tuple3<Long, Pageable, Flux<Company>>> findByBusinessNameLike(String businessName, Pageable pageable) {
        return companyRepository.countByBusinessnameRegex(businessName)
                .filter(count -> count > 0)
                .map(count -> Tuples.of(count, pageable, companyRepository.findByBusinessnameRegex(businessName, pageable)))
                .switchIfEmpty(Mono.defer(() -> Mono.just(Tuples.of(0L, pageable, Flux.empty()))));
    }

    public Mono<Company> findByCompanyId(String companyid) {
        return companyRepository.findByCompanyid(companyid)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new RuntimeException("Company not found for id " + companyid))));
    }

    public Mono<Company> create(Company company) {
        company.setCompanyid(UUID.randomUUID().toString());
        return companyRepository.save(company);
    }

    public Mono<Company> update(Company company) {
        return findByCompanyId(company.getCompanyid())
                .doOnNext(oldcompany -> oldcompany
                        .setAddress(company.getAddress())
                        .setBusinessname(company.getBusinessname())
                        .setCap(company.getCap())
                        .setPiva(company.getPiva()))
                .flatMap(companyRepository::save);
    }

}
