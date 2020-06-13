package it.usuratonkachi.searchspecificationflux.domain.mongo.service;

import it.usuratonkachi.searchcriteria.common.SearchCriteria;
import it.usuratonkachi.searchcriteria.mongo.GenericMongoQueryBuilder;
import it.usuratonkachi.searchspecificationflux.utils.ServerRequestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;
import reactor.util.function.Tuples;

import java.util.List;

@RequiredArgsConstructor
public class SearchCriteriaMongoService<T> {

    private final Class<T> clazz;
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public Mono<Tuple3<Long, Pageable, Flux<T>>> search(List<SearchCriteria> searchCriterias, Pageable page) {
        Query query = new GenericMongoQueryBuilder(clazz).buildQuery(ServerRequestUtils.searchCriteriaConverter(searchCriterias));
        return reactiveMongoTemplate.count(query, clazz)
                .filter(count -> count > 0)
                .map(count -> Tuples.of(count, page, reactiveMongoTemplate.find(query.with(page), clazz)));

    }

}
