package it.usuratonkachi.searchspecificationflux.querycontroller;


import com.fasterxml.jackson.databind.ObjectMapper;
import it.usuratonkachi.searchspecificationflux.domain.mongo.repository.CompanyRepository;
import it.usuratonkachi.searchspecificationflux.domain.mongo.repository.UserRepository;
import it.usuratonkachi.searchspecificationflux.domain.mysql.repository.CompanyJpaRepository;
import it.usuratonkachi.searchspecificationflux.domain.mysql.repository.UserJpaRepository;
import it.usuratonkachi.searchspecificationflux.dto.request.CompanyInsertRequestDto;
import it.usuratonkachi.searchspecificationflux.dto.request.UserInsertRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.stream.IntStream;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseTest {

    @Autowired private UserRepository userRepository;
    @Autowired private UserJpaRepository userJpaRepository;
    @Autowired private CompanyRepository companyRepository;
    @Autowired private CompanyJpaRepository companyJpaRepository;
    @Autowired protected ObjectMapper objectMapper;


    @LocalServerPort
    private int port;
    private static final String url = "http://localhost";
    protected static final RestTemplate restTemplate = new RestTemplate();
    protected static int totalValue = 100;

    protected String getUrl(String uri){
        return String.format("%s:%s/%s", url, port, uri);
    }

    @BeforeEach
    private void populate(){
        IntStream.rangeClosed(1, totalValue)
                .mapToObj(i -> {
                    UserInsertRequestDto userInsertRequestDto = new UserInsertRequestDto();
                    userInsertRequestDto.setUsername("username_" + i);
                    userInsertRequestDto.setCompanyid("company_" + i);
                    userInsertRequestDto.setFirstname("firstname_" + i);
                    userInsertRequestDto.setLastname("lastName_" + i);
                    userInsertRequestDto.setCreateddate(new Date());
                    userInsertRequestDto.setSequentialid(i);
                    userInsertRequestDto.setDoubleid((long) i);
                    return userInsertRequestDto;
                })
                .forEach(user -> restTemplate.put(getUrl("/mongo/user/insert"), user));
        IntStream.rangeClosed(1, totalValue)
                .mapToObj(i -> {
                    CompanyInsertRequestDto companyInsertRequestDto = new CompanyInsertRequestDto();
                    companyInsertRequestDto.setBusinessname("businessname_" + i);
                    companyInsertRequestDto.setAddress("address_" + i);
                    companyInsertRequestDto.setCap("cap_" + i);
                    companyInsertRequestDto.setPiva("piva_" + i);
                    companyInsertRequestDto.setCreateddate(new Date());
                    companyInsertRequestDto.setSequentialid(i);
                    companyInsertRequestDto.setDoubleid((long) i);
                    return companyInsertRequestDto;
                })
                .forEach(company -> restTemplate.put(getUrl("/mongo/company/insert"), company));


        IntStream.rangeClosed(1, totalValue)
                .mapToObj(i -> {
                    UserInsertRequestDto userInsertRequestDto = new UserInsertRequestDto();
                    userInsertRequestDto.setUsername("username_" + i);
                    userInsertRequestDto.setCompanyid("company_" + i);
                    userInsertRequestDto.setFirstname("firstname_" + i);
                    userInsertRequestDto.setLastname("lastName_" + i);
                    userInsertRequestDto.setCreateddate(new Date());
                    userInsertRequestDto.setSequentialid(i);
                    userInsertRequestDto.setDoubleid((long) i);
                    return userInsertRequestDto;
                })
                .forEach(user -> restTemplate.put(getUrl("/mysql/user/insert"), user));
        IntStream.rangeClosed(1, totalValue)
                .mapToObj(i -> {
                    CompanyInsertRequestDto companyInsertRequestDto = new CompanyInsertRequestDto();
                    companyInsertRequestDto.setBusinessname("businessname_" + i);
                    companyInsertRequestDto.setAddress("address_" + i);
                    companyInsertRequestDto.setCap("cap_" + i);
                    companyInsertRequestDto.setPiva("piva_" + i);
                    companyInsertRequestDto.setCreateddate(new Date());
                    companyInsertRequestDto.setSequentialid(i);
                    companyInsertRequestDto.setDoubleid((long) i);
                    return companyInsertRequestDto;
                })
                .forEach(company -> restTemplate.put(getUrl("/mysql/company/insert"), company));
    }

    @AfterEach
    private void destroy() {
        companyRepository.deleteAll().block();
        userRepository.deleteAll().block();
        companyJpaRepository.deleteAll();
        userJpaRepository.deleteAll();
    }

}
