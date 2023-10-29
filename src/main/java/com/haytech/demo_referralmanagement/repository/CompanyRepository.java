package com.haytech.demo_referralmanagement.repository;

import com.haytech.demo_referralmanagement.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CompanyRepository extends JpaRepository<Company,Long> {
    Company getById(Long companyId);

}
