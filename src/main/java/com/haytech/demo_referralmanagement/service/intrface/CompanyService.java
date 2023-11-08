package com.haytech.demo_referralmanagement.service.intrface;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.dto.CompanyDTO;


public interface CompanyService {
    BaseDTO createCompany(CompanyDTO company);

    BaseDTO getAllCompanies(int page, int size);

    BaseDTO updateCompany(Long companyId, CompanyDTO updatedCompany);

    BaseDTO deleteCompany(Long companyId);

    BaseDTO getCompany(Long companyId);

    BaseDTO getAllCompaniesById(int page, int size, Long companyId);
}