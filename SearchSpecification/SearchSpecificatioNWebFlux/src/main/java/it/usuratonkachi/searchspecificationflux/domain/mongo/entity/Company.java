package it.usuratonkachi.searchspecificationflux.domain.mongo.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@Document(value = "company")
public class Company implements Serializable {

    private static final long serialVersionUID = 20200507_2146L;

    @Id
    private String id;
    private String companyid;
    private String piva;
    private String businessname;
    private String address;
    private String cap;
    private Date createddate;
    private Integer sequentialid;
    private Long doubleid;

}
