package it.usuratonkachi.searchspecificationflux.service;

import it.usuratonkachi.searchspecificationflux.domain.mysql.entity.CompanyJpa;
import it.usuratonkachi.searchspecificationflux.domain.mysql.repository.CompanyJpaRepository;
import lombok.Getter;
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
public class CompanyJpaReactive {

    @Getter
    private final CompanyJpaRepository companyRepository;

    public Mono<CompanyJpa> findByCompanyid(String companyid) {
        return Mono.justOrEmpty(companyRepository.findByCompanyId(companyid));
    }

    public Mono<Tuple3<Long, Pageable, Flux<CompanyJpa>>> findByBusinessnameLike(String businessName, Pageable pageable){
        Page<CompanyJpa> companies = companyRepository.findByBusinessnameLike(businessName, pageable);
        Long count = companies.getTotalElements();
        return Mono.just(Tuples.of(count, pageable, Flux.fromIterable(companies)));
    }

    public Mono<CompanyJpa> save(CompanyJpa companyJpa) {
        return Mono.just(companyRepository.save(companyJpa));
    }


}