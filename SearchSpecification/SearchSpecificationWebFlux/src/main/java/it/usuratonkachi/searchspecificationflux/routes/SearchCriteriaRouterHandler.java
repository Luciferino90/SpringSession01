package it.usuratonkachi.searchspecificationflux.routes;

import it.usuratonkachi.searchspecificationflux.handler.CompanyHandler;
import it.usuratonkachi.searchspecificationflux.handler.CompanyJpaHandler;
import it.usuratonkachi.searchspecificationflux.handler.UserHandler;
import it.usuratonkachi.searchspecificationflux.handler.UserJpaHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
@RequiredArgsConstructor
public class SearchCriteriaRouterHandler {

    private final UserHandler userHandler;
    private final CompanyHandler companyHandler;
    private final UserJpaHandler userJpaHandler;
    private final CompanyJpaHandler companyJpaHandler;

    @Bean
    public RouterFunction<ServerResponse> mongoSearchRoute(){
        return RouterFunctions.route()
                .POST("/mongo/search/user", userHandler::search)
                .POST("/mongo/search/company", companyHandler::search)
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> mysqlSearchRoute(){
        return RouterFunctions.route()
                .POST("/mysql/search/user", userJpaHandler::search)
                .POST("/mysql/search/company", companyJpaHandler::search)
                .build();
    }

}
