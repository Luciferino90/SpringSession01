package it.usuratonkachi.searchspecificationflux.mapper;

import it.usuratonkachi.searchspecificationflux.domain.mongo.entity.Company;
import it.usuratonkachi.searchspecificationflux.domain.mysql.entity.CompanyJpa;
import it.usuratonkachi.searchspecificationflux.dto.request.CompanyInsertRequestDto;
import it.usuratonkachi.searchspecificationflux.dto.request.CompanyUpdateRequestDto;
import it.usuratonkachi.searchspecificationflux.dto.response.CompanyResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyResponseDto mapperEntityToResponseDto(Company company);
    Company mapperUpdateDtoToEntity(CompanyUpdateRequestDto company);
    Company mapperInsertDtoToEntity(CompanyInsertRequestDto company);

    @Mapping(target = "sequentialid", source = "sequentialId")
    @Mapping(target = "doubleid", source = "doubleId")
    @Mapping(target = "createddate", source = "createdDate")
    @Mapping(target = "companyid", source = "companyId")
    CompanyResponseDto mapperEntityToResponseDtoJpa(CompanyJpa company);

    @Mapping(source = "sequentialid", target = "sequentialId")
    @Mapping(source = "doubleid", target = "doubleId")
    @Mapping(source = "createddate", target = "createdDate")
    @Mapping(source = "companyid", target = "companyId")
    CompanyJpa mapperUpdateDtoToEntityJpa(CompanyUpdateRequestDto company);

    @Mapping(source = "sequentialid", target = "sequentialId")
    @Mapping(source = "doubleid", target = "doubleId")
    @Mapping(source = "createddate", target = "createdDate")
    CompanyJpa mapperInsertDtoToEntityJpa(CompanyInsertRequestDto company);

}
