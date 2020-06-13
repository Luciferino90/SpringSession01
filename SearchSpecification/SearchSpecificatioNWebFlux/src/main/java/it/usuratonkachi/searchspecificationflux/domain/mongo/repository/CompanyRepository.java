package it.usuratonkachi.searchspecificationflux.domain.mongo.repository;

import it.usuratonkachi.searchspecificationflux.domain.mongo.entity.Company;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CompanyRepository extends ReactiveMongoRepository<Company, String> {

    Mono<Company> findByCompanyid(String companyid);
    Mono<Long> countByBusinessnameRegex(String businessName);
    Flux<Company> findByBusinessnameRegex(String businessName, Pageable pageable);

}
