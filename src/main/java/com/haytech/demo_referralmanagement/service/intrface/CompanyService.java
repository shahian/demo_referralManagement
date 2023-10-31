package com.haytech.demo_referralmanagement.service.intrface;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.entity.Company;

import java.util.List;
import java.util.Optional;


public interface CompanyService {
    BaseDTO createCompany(Company company);

    BaseDTO getAllCompanies();

    BaseDTO updateCompany(Long companyId, Company updatedCompany);

    BaseDTO deleteCompany(Long companyId);

    BaseDTO getCompany(Long companyId);
}