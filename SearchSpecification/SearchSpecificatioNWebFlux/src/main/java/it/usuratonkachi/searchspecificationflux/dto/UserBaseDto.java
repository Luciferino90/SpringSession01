package it.usuratonkachi.searchspecificationflux.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class UserBaseDto implements Serializable {

    private static final long serialVersionUID = 20200507_2143L;

    @NotNull @NotBlank @NotEmpty
    private String username;
    @NotNull @NotBlank @NotEmpty
    private String firstname;
    @NotNull @NotBlank @NotEmpty
    private String lastname;
    @NotNull @NotBlank @NotEmpty
    private String companyid;
    @NotNull
    private Date createddate;
    @NotNull
    private Integer sequentialid;
    @NotNull
    private Long doubleid;

}
