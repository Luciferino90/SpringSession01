package it.usuratonkachi.searchspecificationflux.routes;

import it.usuratonkachi.searchspecificationflux.handler.CompanyHandler;
import it.usuratonkachi.searchspecificationflux.handler.UserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
@RequiredArgsConstructor
public class RouterHandler {

    private final UserHandler userHandler;
    private final CompanyHandler companyHandler;

    @Bean
    public RouterFunction<ServerResponse> mongoRoute(){
        return RouterFunctions.route()
                .GET("/mongo/user/{userid}", userHandler::getUser)
                .GET("/mongo/user/{username}", userHandler::getUserByUsernameLike)
                .PUT("/mongo/user/insert", userHandler::insertUser)
                .POST("/mongo/user/update", userHandler::updateUser)
                .GET("/mongo/company/{companyid}", companyHandler::getCompany)
                .GET("/mongo/company/{businessname}", companyHandler::getCompanyByBusinessNameLike)
                .PUT("/mongo/company/insert", companyHandler::insertCompany)
                .POST("/mongo/company/update", companyHandler::updateCompany)
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> mysqlRoute(){
        return RouterFunctions.route()
                .GET("/mysql/user/{username}", serverRequest -> ServerResponse.ok().build())
                .PUT("/mysql/user/insert", serverRequest -> ServerResponse.ok().build())
                .POST("/mysql/user/update", serverRequest -> ServerResponse.ok().build())
                .GET("/mysql/company/{username}", serverRequest -> ServerResponse.ok().build())
                .PUT("/mysql/company/insert", serverRequest -> ServerResponse.ok().build())
                .POST("/mysql/company/update", serverRequest -> ServerResponse.ok().build())
                .build();
    }

}
