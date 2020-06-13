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
public class CrudRouterHandler {

    private final UserHandler userHandler;
    private final CompanyHandler companyHandler;
    private final UserJpaHandler userJpaHandler;
    private final CompanyJpaHandler companyJpaHandler;

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
                .GET("/mysql/user/{userid}", userJpaHandler::getUser)
                .GET("/mysql/user/{username}", userJpaHandler::getUserByUsernameLike)
                .PUT("/mysql/user/insert", userJpaHandler::insertUser)
                .POST("/mysql/user/update", userJpaHandler::updateUser)
                .GET("/mysql/company/{companyid}", companyJpaHandler::getCompany)
                .GET("/mysql/company/{businessname}", companyJpaHandler::getCompanyByBusinessNameLike)
                .PUT("/mysql/company/insert", companyJpaHandler::insertCompany)
                .POST("/mysql/company/update", companyJpaHandler::updateCompany)
                .build();
    }

}
