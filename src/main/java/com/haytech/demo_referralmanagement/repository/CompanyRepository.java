package com.haytech.demo_referralmanagement.repository;

import com.haytech.demo_referralmanagement.model.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company getById(Long companyId);

    Page<Company> findAllById(Pageable pageable, Long companyId);

}
