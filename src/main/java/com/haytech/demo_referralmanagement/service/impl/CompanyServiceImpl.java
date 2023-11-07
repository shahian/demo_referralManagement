package com.haytech.demo_referralmanagement.service.impl;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.base.MetaDTO;
import com.haytech.demo_referralmanagement.model.dto.CompanyDTO;
import com.haytech.demo_referralmanagement.model.entity.Company;
import com.haytech.demo_referralmanagement.model.mapper.CompanyMapper;
import com.haytech.demo_referralmanagement.repository.CompanyRepository;
import com.haytech.demo_referralmanagement.service.intrface.CompanyService;
import com.haytech.demo_referralmanagement.utility.ApplicationProperties;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final ApplicationProperties applicationProperties;
    private final CompanyMapper companyMapper;

    public CompanyServiceImpl(CompanyRepository companyRepository, ApplicationProperties applicationProperties, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.applicationProperties = applicationProperties;
        this.companyMapper = companyMapper;
    }
    @Override
    public BaseDTO getCompany(Long companyId) {
        return new BaseDTO(MetaDTO.getInstance(applicationProperties),
                companyMapper.DTO_Company(
                        companyRepository.findById(companyId).orElseThrow(() -> new EntityNotFoundException("company Not Found..."))));

    }
    @Override
    public BaseDTO getAllCompanies() {
        List<Company> result = companyRepository.findAll();
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), companyMapper.DTO_LIST(result));
    }
    @Override
    public BaseDTO createCompany(CompanyDTO company) {
        Company newCompany = companyRepository.save(companyMapper.Company_DTO(company));
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), companyMapper.DTO_Company(newCompany));
    }    @Override
    public BaseDTO updateCompany(Long companyId, CompanyDTO updatedCompany) {
        Company existingCompany = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
        existingCompany.setName(updatedCompany.getName());
        existingCompany.setCode(updatedCompany.getCode());
        companyRepository.save(existingCompany);
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), companyMapper.DTO_Company(existingCompany));
    }
    @Override
    public BaseDTO deleteCompany(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
        if (company.getInsuranceCourseTypeList().stream().count() > 0) {
            throw new EntityExistsException("company has courses");
        }
        companyRepository.deleteById(companyId);
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), company);
    }
}