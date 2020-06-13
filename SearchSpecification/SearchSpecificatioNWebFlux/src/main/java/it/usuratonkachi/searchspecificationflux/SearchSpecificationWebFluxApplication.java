package it.usuratonkachi.searchspecificationflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableMongoRepositories(basePackages = "it.usuratonkachi.searchspecificationflux.domain")
public class SearchSpecificationWebFluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchSpecificationWebFluxApplication.class, args);
	}

}
