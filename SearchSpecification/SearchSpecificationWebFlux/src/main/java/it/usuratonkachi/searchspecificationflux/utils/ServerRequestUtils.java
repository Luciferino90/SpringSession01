package it.usuratonkachi.searchspecificationflux.utils;

import it.usuratonkachi.searchcriteria.common.SearchCriteria;
import it.usuratonkachi.searchcriteria.common.SearchCriteriaSpecification;
import it.usuratonkachi.searchcriteria.common.SearchOperator;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ServerRequestUtils {

    public Pageable extractPageable(ServerRequest request) {
        int pageNum = Integer.parseInt(request.queryParam("page").orElse("0"));
        int pageSize = Integer.parseInt(request.queryParam("size").orElse("20"));

        return request.queryParam("sort").map(sort -> {
            String fieldName = sort.split(",")[0];
            Sort.Direction direction = Sort.Direction.fromString(sort.split(",")[1]);
            return Sort.by(direction, fieldName);
        })
        .map(sort -> PageRequest.of(pageNum, pageSize, sort))
        .orElseGet(() -> PageRequest.of(pageNum, pageSize));
    }

    public List<SearchCriteriaSpecification> searchCriteriaConverter(List<SearchCriteria> searchCriterias){
        return searchCriterias.stream()
                .map(sc -> SearchCriteriaSpecification.builder()
                        .field(sc.getField())
                        .operator(SearchOperator.valueOf(sc.getOperator()))
                        .value(sc.getValue())
                        .valueAdditional(sc.getValueAdditional())
                        .build()
                )
                .collect(Collectors.toList());
    }

}
