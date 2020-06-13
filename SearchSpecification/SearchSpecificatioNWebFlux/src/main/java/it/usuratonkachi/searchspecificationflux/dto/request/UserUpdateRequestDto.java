package it.usuratonkachi.searchspecificationflux.dto.request;

import it.usuratonkachi.searchspecificationflux.dto.UserBaseDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserUpdateRequestDto extends UserBaseDto implements Serializable {

    private static final long serialVersionUID = 20200507_2143L;

    @NotNull @NotBlank @NotEmpty
    private String userid;

}
