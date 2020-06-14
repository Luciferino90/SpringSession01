package it.usuratonkachi.searchspecificationflux.domain.mysql.service;

import it.usuratonkachi.searchcriteria.common.SearchCriteria;
import it.usuratonkachi.searchcriteria.mysql.QueryBuilder;
import it.usuratonkachi.searchspecificationflux.utils.ServerRequestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;
import reactor.util.function.Tuples;

import java.util.List;

@RequiredArgsConstructor
public class SearchCriteriaJpaService<T> {

    private final Class<T> clazz;
    private final JpaSpecificationExecutor<T> repository;

    @SuppressWarnings("unchecked")
    public Mono<Page<T>> search(List<SearchCriteria> searchCriterias, Pageable page) {
        return Mono.just((Specification<T>) new QueryBuilder<>(clazz).specificationBuilder(ServerRequestUtils.searchCriteriaConverter(searchCriterias)))
                .map(query -> repository.findAll(query, page));
    }

}
