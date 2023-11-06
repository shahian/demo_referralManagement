package com.haytech.demo_referralmanagement.service.intrface;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.dto.CompanyDTO;
import com.haytech.demo_referralmanagement.model.entity.Company;

import java.util.List;
import java.util.Optional;


public interface CompanyService {
    BaseDTO createCompany(CompanyDTO company);

    BaseDTO getAllCompanies();

    BaseDTO updateCompany(Long companyId, CompanyDTO updatedCompany);

    BaseDTO deleteCompany(Long companyId);

    BaseDTO getCompany(Long companyId);
}