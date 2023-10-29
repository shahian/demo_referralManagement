package com.haytech.demo_referralmanagement.service.intrface;

import com.haytech.demo_referralmanagement.model.entity.Company;

import java.util.List;
import java.util.Optional;


public interface CompanyService {
    Company createCompany(Company company);

    List<Company> getAllCompanies();

    Company updateCompany(Long companyId, Company updatedCompany);

    void deleteCompany(Long companyId);

    Company getCompany(Long companyId);
}