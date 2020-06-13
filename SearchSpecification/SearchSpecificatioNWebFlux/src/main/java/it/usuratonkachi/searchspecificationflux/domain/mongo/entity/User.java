package it.usuratonkachi.searchspecificationflux.domain.mongo.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@Document(value = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 20200507_2144L;

    @Id
    private String id;
    private String userid;
    private String username;
    private String firstname;
    private String lastname;
    private String companyid;
    private Date createddate;
    private Integer sequentialid;
    private Long doubleid;

}
