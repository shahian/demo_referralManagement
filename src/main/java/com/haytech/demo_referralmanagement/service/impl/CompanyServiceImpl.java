package com.haytech.demo_referralmanagement.service.impl;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.base.MetaDTO;
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
    public Company createCompany(Company company) {

        return companyRepository.save(company);
    }

    @Override
    public BaseDTO getAllCompanies() {
        List<Company> result = companyRepository.findAll();
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), companyMapper.DTO_LIST(result));
    }

    @Override
    public Company updateCompany(Long companyId, Company updatedCompany) {
        Company existingCompany = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));

        existingCompany.setName(updatedCompany.getName());
        existingCompany.setCode(updatedCompany.getCode());

        return companyRepository.save(existingCompany);
    }

    @Override
    public void deleteCompany(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
        if (company.getCourses().stream().count()>0) {
            throw new EntityExistsException("company has courses");
        }
        companyRepository.deleteById(companyId);
    }

    @Override
    public Company getCompany(Long companyId) {
        return companyRepository.getById(companyId);
    }
}