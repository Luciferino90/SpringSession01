package it.usuratonkachi.searchspecificationflux.domain.mysql.repository;

import it.usuratonkachi.searchspecificationflux.domain.mysql.entity.CompanyJpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyJpaRepository extends JpaRepository<CompanyJpa, String>, JpaSpecificationExecutor<CompanyJpa> {

    Optional<CompanyJpa> findByCompanyId(String companyid);
    Page<CompanyJpa> findByBusinessnameLike(String businessName, Pageable pageable);

}
