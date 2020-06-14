package it.usuratonkachi.searchspecificationflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "it.usuratonkachi.searchspecificationflux.domain.mongo")
@EnableJpaRepositories(basePackages = "it.usuratonkachi.searchspecificationflux.domain.mysql")
public class SearchSpecificationWebFluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchSpecificationWebFluxApplication.class, args);
	}

}
