package it.usuratonkachi.searchspecificationflux.domain.mysql.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@Accessors(chain = true)
@Entity(name = "company")
public class CompanyJpa implements Serializable {

    private static final long serialVersionUID = 20200507_2146L;

    @Id
    private String id = UUID.randomUUID().toString();
    private String companyId;
    private String piva;
    private String businessname;
    private String address;
    private String cap;
    private Date createdDate;
    private Integer sequentialId;
    private Long doubleId;

}
