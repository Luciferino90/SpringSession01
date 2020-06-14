package it.usuratonkachi.searchspecificationflux.dto.request;

import it.usuratonkachi.searchcriteria.common.SearchCriteria;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


@Data
public class SearchCriteriaRequestDto implements Serializable {

    private static final long serialVersionUID = 20200516_1403L;

    @NotNull @NotEmpty
    private List<SearchCriteria> searchCriteriaList;

}
