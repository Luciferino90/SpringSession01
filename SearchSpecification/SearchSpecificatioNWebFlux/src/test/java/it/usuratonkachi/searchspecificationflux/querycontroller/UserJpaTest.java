package it.usuratonkachi.searchspecificationflux.querycontroller;

import it.usuratonkachi.searchcriteria.common.SearchCriteria;
import it.usuratonkachi.searchcriteria.common.SearchOperator;
import it.usuratonkachi.searchspecificationflux.dto.request.SearchCriteriaRequestDto;
import it.usuratonkachi.searchspecificationflux.dto.response.UserResponseDto;
import it.usuratonkachi.searchspecificationflux.wrapper.RestPageImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserJpaTest extends BaseTest {

    @Test
    public void testDefaultPage(){
        SearchCriteria sc = new SearchCriteria();
        sc.setField("username");
        sc.setOperator(SearchOperator.LIKE.toString());
        sc.setValue("username");
        SearchCriteriaRequestDto searchCriteriaRequestDto = new SearchCriteriaRequestDto();
        searchCriteriaRequestDto.setSearchCriteriaList(List.of(sc));

        ParameterizedTypeReference<RestPageImpl<UserResponseDto>> type = new ParameterizedTypeReference<>() {};

        ResponseEntity<RestPageImpl<UserResponseDto>> response = restTemplate.exchange(getUrl("/mysql/search/user"), HttpMethod.POST, new HttpEntity(searchCriteriaRequestDto), type);
        RestPageImpl<UserResponseDto> page = response.getBody();
        Assertions.assertEquals(20, Objects.requireNonNull(page).getNumberOfElements());
        Assertions.assertEquals(totalValue, page.getTotalElements());
    }

    @Test
    public void testPageSmall(){
        SearchCriteria sc = new SearchCriteria();
        sc.setField("username");
        sc.setOperator(SearchOperator.LIKE.toString());
        sc.setValue("username");
        SearchCriteriaRequestDto searchCriteriaRequestDto = new SearchCriteriaRequestDto();
        searchCriteriaRequestDto.setSearchCriteriaList(List.of(sc));

        ParameterizedTypeReference<RestPageImpl<UserResponseDto>> type = new ParameterizedTypeReference<>() {};

        int pageNumber;
        int size = 5;
        pageNumber = 0;

        ResponseEntity<RestPageImpl<UserResponseDto>> response = restTemplate.exchange(getUrl("/mysql/search/user?page="+pageNumber+"&size="+size), HttpMethod.POST, new HttpEntity(searchCriteriaRequestDto), type);
        RestPageImpl<UserResponseDto> page = response.getBody();
        Assertions.assertEquals(size, Objects.requireNonNull(page).getNumberOfElements());
        Assertions.assertEquals(totalValue, page.getTotalElements());
    }

    @Test
    @SneakyThrows
    public void testDateSmall(){
        SearchCriteria sc = new SearchCriteria();
        sc.setField("createdDate");
        sc.setOperator(SearchOperator.GTE.toString());
        sc.setValue(objectMapper.writeValueAsString(new Date()).split("\\+")[0].replace("\"", ""));
        SearchCriteriaRequestDto searchCriteriaRequestDto = new SearchCriteriaRequestDto();
        searchCriteriaRequestDto.setSearchCriteriaList(List.of(sc));

        ParameterizedTypeReference<RestPageImpl<UserResponseDto>> type = new ParameterizedTypeReference<>() {};

        int pageNumber;
        int size = 5;
        pageNumber = 0;

        ResponseEntity<RestPageImpl<UserResponseDto>> response = restTemplate.exchange(getUrl("/mysql/search/user?page="+pageNumber+"&size="+size), HttpMethod.POST, new HttpEntity(searchCriteriaRequestDto), type);
        RestPageImpl<UserResponseDto> page = response.getBody();
        Assertions.assertEquals(size, Objects.requireNonNull(page).getNumberOfElements());
        Assertions.assertEquals(totalValue, page.getTotalElements());
    }

    @Test
    @SneakyThrows
    public void testIntegerSmall(){
        SearchCriteria sc = new SearchCriteria();
        sc.setField("sequentialId");
        sc.setOperator(SearchOperator.EQUAL.toString());
        sc.setValue("1");
        SearchCriteriaRequestDto searchCriteriaRequestDto = new SearchCriteriaRequestDto();
        searchCriteriaRequestDto.setSearchCriteriaList(List.of(sc));

        ParameterizedTypeReference<RestPageImpl<UserResponseDto>> type = new ParameterizedTypeReference<>() {};

        int pageNumber;
        int size = 5;
        pageNumber = 0;

        ResponseEntity<RestPageImpl<UserResponseDto>> response = restTemplate.exchange(getUrl("/mysql/search/user?page="+pageNumber+"&size="+size), HttpMethod.POST, new HttpEntity(searchCriteriaRequestDto), type);
        RestPageImpl<UserResponseDto> page = response.getBody();
        Assertions.assertEquals(1, Objects.requireNonNull(page).getNumberOfElements());
        Assertions.assertEquals(1, page.getTotalElements());
    }

    @Test
    @SneakyThrows
    public void testDoubleSmall(){
        SearchCriteria sc = new SearchCriteria();
        sc.setField("sequentialId");
        sc.setOperator(SearchOperator.EQUAL.toString());
        sc.setValue(String.valueOf(Long.valueOf(1)));
        SearchCriteriaRequestDto searchCriteriaRequestDto = new SearchCriteriaRequestDto();
        searchCriteriaRequestDto.setSearchCriteriaList(List.of(sc));

        ParameterizedTypeReference<RestPageImpl<UserResponseDto>> type = new ParameterizedTypeReference<>() {};

        int pageNumber;
        int size = 5;
        pageNumber = 0;

        ResponseEntity<RestPageImpl<UserResponseDto>> response = restTemplate.exchange(getUrl("/mysql/search/user?page="+pageNumber+"&size="+size), HttpMethod.POST, new HttpEntity(searchCriteriaRequestDto), type);
        RestPageImpl<UserResponseDto> page = response.getBody();
        Assertions.assertEquals(1, Objects.requireNonNull(page).getNumberOfElements());
        Assertions.assertEquals(1, page.getTotalElements());
    }

}
