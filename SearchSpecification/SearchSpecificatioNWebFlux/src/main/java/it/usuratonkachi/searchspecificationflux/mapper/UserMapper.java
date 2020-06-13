package it.usuratonkachi.searchspecificationflux.mapper;

import it.usuratonkachi.searchspecificationflux.domain.mongo.entity.User;
import it.usuratonkachi.searchspecificationflux.domain.mysql.entity.UserJpa;
import it.usuratonkachi.searchspecificationflux.dto.request.UserInsertRequestDto;
import it.usuratonkachi.searchspecificationflux.dto.request.UserUpdateRequestDto;
import it.usuratonkachi.searchspecificationflux.dto.response.UserResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto mapperEntityToResponseDto(User user);
    User mapperUpdateDtoToEntity(UserUpdateRequestDto user);
    User mapperInsertDtoToEntity(UserInsertRequestDto user);

    UserResponseDto mapperEntityToResponseDtoJpa(UserJpa user);
    UserJpa mapperUpdateDtoToEntityJpa(UserUpdateRequestDto user);
    UserJpa mapperInsertDtoToEntityJpa(UserInsertRequestDto user);

}
