package it.usuratonkachi.searchspecificationflux.dto.request;

import it.usuratonkachi.searchspecificationflux.dto.CompanyBaseDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CompanyUpdateRequestDto extends CompanyBaseDto implements Serializable {

    private static final long serialVersionUID = 20200507_2143L;

    @NotNull @NotBlank @NotEmpty
    private String companyid;

}
