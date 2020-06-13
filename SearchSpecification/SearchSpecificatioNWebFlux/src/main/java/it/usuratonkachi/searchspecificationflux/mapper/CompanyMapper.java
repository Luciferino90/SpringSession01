package it.usuratonkachi.searchspecificationflux.mapper;

import it.usuratonkachi.searchspecificationflux.domain.mongo.entity.Company;
import it.usuratonkachi.searchspecificationflux.domain.mysql.entity.CompanyJpa;
import it.usuratonkachi.searchspecificationflux.dto.request.CompanyInsertRequestDto;
import it.usuratonkachi.searchspecificationflux.dto.request.CompanyUpdateRequestDto;
import it.usuratonkachi.searchspecificationflux.dto.response.CompanyResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyResponseDto mapperEntityToResponseDto(Company company);
    Company mapperUpdateDtoToEntity(CompanyUpdateRequestDto company);
    Company mapperInsertDtoToEntity(CompanyInsertRequestDto company);


    CompanyResponseDto mapperEntityToResponseDtoJpa(CompanyJpa company);
    CompanyJpa mapperUpdateDtoToEntityJpa(CompanyUpdateRequestDto company);
    CompanyJpa mapperInsertDtoToEntityJpa(CompanyInsertRequestDto company);

}
