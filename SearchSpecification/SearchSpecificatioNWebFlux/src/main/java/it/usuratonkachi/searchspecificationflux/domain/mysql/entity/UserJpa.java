package it.usuratonkachi.searchspecificationflux.domain.mysql.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Data
@Accessors(chain = true)
@Entity(name = "user")
public class UserJpa {

    @Id
    private String id = UUID.randomUUID().toString();
    private String userid;
    private String username;
    private String firstname;
    private String lastname;
    private String companyid;
    private Date createddate;
    private Integer sequentialid;
    private Long doubleid;

}
