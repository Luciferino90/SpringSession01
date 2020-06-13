package it.usuratonkachi.searchspecificationflux.service;

import it.usuratonkachi.searchcriteria.common.SearchCriteria;
import it.usuratonkachi.searchcriteria.common.SearchCriteriaSpecification;
import it.usuratonkachi.searchcriteria.common.SearchOperator;
import it.usuratonkachi.searchcriteria.mongo.GenericMongoQueryBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SearchCriteriaService<T> {

    private final MongoTemplate mongoTemplate;

    public Page<T> search(List<SearchCriteria> searchCriterias, Class<T> clazz, Pageable page) {
        List<SearchCriteriaSpecification> searchCriteriaSpecification = searchCriterias.stream()
                .map(sc -> SearchCriteriaSpecification.builder()
                        .field(sc.getField())
                        .operator(SearchOperator.valueOf(sc.getOperator()))
                        .value(sc.getValue())
                        .valueAdditional(sc.getValueAdditional())
                        .build()
                )
                .collect(Collectors.toList());

        Query query = new GenericMongoQueryBuilder(clazz).buildQuery(searchCriteriaSpecification);

        long count = mongoTemplate.count(query, clazz);

        return PageableExecutionUtils.getPage(
                count > 0 ? mongoTemplate.find(query.with(page), clazz) : new ArrayList<>(),
                page,
                () -> count
        );
    }

}
