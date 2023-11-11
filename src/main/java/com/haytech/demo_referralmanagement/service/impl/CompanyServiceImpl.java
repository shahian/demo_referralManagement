package com.haytech.demo_referralmanagement.service.impl;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.base.MetaDTO;
import com.haytech.demo_referralmanagement.model.dto.CompanyDTO;
import com.haytech.demo_referralmanagement.model.entity.Company;
import com.haytech.demo_referralmanagement.model.mapper.CompanyMapper;
import com.haytech.demo_referralmanagement.repository.CompanyRepository;
import com.haytech.demo_referralmanagement.service.intrface.CompanyService;
import com.haytech.demo_referralmanagement.utility.ApplicationProperties;
import com.haytech.demo_referralmanagement.utility.PageableUtility;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final ApplicationProperties applicationProperties;
    private final CompanyMapper companyMapper;
    private final PageableUtility pageableUtility;

    public CompanyServiceImpl(CompanyRepository companyRepository, ApplicationProperties applicationProperties, CompanyMapper companyMapper, PageableUtility pageableUtility) {
        this.companyRepository = companyRepository;
        this.applicationProperties = applicationProperties;
        this.companyMapper = companyMapper;
        this.pageableUtility = pageableUtility;
    }

    @Override
    public BaseDTO getCompany(Long companyId) {
        return new BaseDTO(MetaDTO.getInstance(applicationProperties),
                companyMapper.DTO_Company(
                        companyRepository.findById(companyId)
                                .orElseThrow(() -> new EntityNotFoundException("company Not Found..."))));

    }

    @Override
    public BaseDTO getAllCompaniesById(int page, int size, Long companyId) {
        Pageable pageable = pageableUtility.createPageable(page, size,Sort.by(Sort.Order.asc("id")));

        if (companyId != null) {
            Page<Company> companies = companyRepository.findAllById(pageable, companyId);
            List<CompanyDTO> companyDTOS = companies.stream()
                    .map(companyMapper::DTO_Company)
                    .collect(Collectors.toList());
            Page<CompanyDTO> companyDTOPage = new PageImpl<>(companyDTOS, pageable, companies.getTotalElements());
            return new BaseDTO(MetaDTO.getInstance(applicationProperties), companyDTOPage);
        }else {
            Page<Company> companies = companyRepository.findAll(pageable);
            List<CompanyDTO> companyDTOS = companies.stream()
                    .map(companyMapper::DTO_Company)
                    .collect(Collectors.toList());
            Page<CompanyDTO> companyDTOPage = new PageImpl<>(companyDTOS, pageable, companies.getTotalElements());
            return new BaseDTO(MetaDTO.getInstance(applicationProperties), companyDTOPage);
        }
    }
    @Override
    public BaseDTO getAllCompanies() {
        List<Company> result = companyRepository.findAll();
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), companyMapper.DTO_LIST(result));
    }
    @Override
    public BaseDTO getAllCompanies(int page, int size) {
        Pageable pageable = pageableUtility.createPageable(page, size,Sort.by(Sort.Order.asc("id")));
        Page<Company> companies = companyRepository.findAll(pageable);
        List<CompanyDTO> companyDTOS = companies.stream()
                .map(companyMapper::DTO_Company)
                .collect(Collectors.toList());
        Page<CompanyDTO> companyDTOPage = new PageImpl<>(companyDTOS, pageable, companies.getTotalElements());
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), companyDTOPage);
    }

    @Override
    public BaseDTO createCompany(CompanyDTO company) {
        Company newCompany = companyRepository.save(companyMapper.Company_DTO(company));
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), companyMapper.DTO_Company(newCompany));
    }

    @Override
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
        company.setDeleted(true);
        companyRepository.save(company);
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), company);
    }
}