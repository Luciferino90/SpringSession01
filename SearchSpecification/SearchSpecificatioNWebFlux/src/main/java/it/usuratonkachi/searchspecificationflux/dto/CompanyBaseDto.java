package it.usuratonkachi.searchspecificationflux.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class CompanyBaseDto implements Serializable {

    private static final long serialVersionUID = 20200507_2143L;
    
    @NotNull @NotBlank @NotEmpty
    private String piva;
    @NotNull @NotBlank @NotEmpty
    private String businessname;
    @NotNull @NotBlank @NotEmpty
    private String address;
    @NotNull @NotBlank @NotEmpty
    private String cap;
    @NotNull
    private Date createddate;
    @NotNull
    private Integer sequentialid;
    @NotNull
    private Long doubleid;

}
