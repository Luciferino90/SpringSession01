package it.usuratonkachi.searchspecificationflux.utils;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.reactive.function.server.ServerRequest;

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

}
