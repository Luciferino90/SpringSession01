package it.usuratonkachi.searchspecificationflux.dto.response;

import it.usuratonkachi.searchspecificationflux.dto.CompanyBaseDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CompanyResponseDto extends CompanyBaseDto implements Serializable {

    private static final long serialVersionUID = 20200507_2143L;

    @NotNull @NotEmpty @NotBlank
    private String companyid;

}
