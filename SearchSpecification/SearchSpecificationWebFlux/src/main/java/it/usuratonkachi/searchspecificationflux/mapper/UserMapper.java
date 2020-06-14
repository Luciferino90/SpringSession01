package it.usuratonkachi.searchspecificationflux.mapper;

import it.usuratonkachi.searchspecificationflux.domain.mongo.entity.User;
import it.usuratonkachi.searchspecificationflux.domain.mysql.entity.UserJpa;
import it.usuratonkachi.searchspecificationflux.dto.request.UserInsertRequestDto;
import it.usuratonkachi.searchspecificationflux.dto.request.UserUpdateRequestDto;
import it.usuratonkachi.searchspecificationflux.dto.response.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto mapperEntityToResponseDto(User user);
    User mapperUpdateDtoToEntity(UserUpdateRequestDto user);
    User mapperInsertDtoToEntity(UserInsertRequestDto user);

    @Mapping(target = "userid", source = "userId")
    @Mapping(target = "companyid", source = "companyId")
    UserResponseDto mapperEntityToResponseDtoJpa(UserJpa user);

    @Mapping(source = "sequentialid", target = "sequentialId")
    @Mapping(source = "userid", target = "userId")
    @Mapping(source = "createddate", target = "createdDate")
    @Mapping(source = "doubleid", target = "doubleId")
    UserJpa mapperUpdateDtoToEntityJpa(UserUpdateRequestDto user);

    @Mapping(source = "sequentialid", target = "sequentialId")
    @Mapping(source = "createddate", target = "createdDate")
    @Mapping(source = "doubleid", target = "doubleId")
    UserJpa mapperInsertDtoToEntityJpa(UserInsertRequestDto user);

}
