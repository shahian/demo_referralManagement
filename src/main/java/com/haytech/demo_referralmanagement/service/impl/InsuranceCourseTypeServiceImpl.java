package com.haytech.demo_referralmanagement.service.impl;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.base.MetaDTO;
import com.haytech.demo_referralmanagement.model.entity.Company;
import com.haytech.demo_referralmanagement.model.entity.InsuranceCourseType;
import com.haytech.demo_referralmanagement.model.mapper.InsuranceCourseTypeMapper;
import com.haytech.demo_referralmanagement.repository.CompanyRepository;
import com.haytech.demo_referralmanagement.repository.InsuranceCourseTypeRepository;
import com.haytech.demo_referralmanagement.service.intrface.InsuranceCourseTypeService;
import com.haytech.demo_referralmanagement.utility.ApplicationProperties;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Service
public class InsuranceCourseTypeServiceImpl implements InsuranceCourseTypeService {
    private final CompanyRepository companyRepository;
    private InsuranceCourseTypeRepository insuranceCourseTypeRepository;
    private final ApplicationProperties applicationProperties;
    private final InsuranceCourseTypeMapper insuranceCourseTypeMapper;

    public InsuranceCourseTypeServiceImpl(InsuranceCourseTypeRepository insuranceCourseTypeRepository, CompanyRepository companyRepository, ApplicationProperties applicationProperties, InsuranceCourseTypeMapper insuranceCourseTypeMapper) {
        this.insuranceCourseTypeRepository = insuranceCourseTypeRepository;
        this.companyRepository = companyRepository;
        this.applicationProperties = applicationProperties;
        this.insuranceCourseTypeMapper = insuranceCourseTypeMapper;
    }

    @Override
    public BaseDTO getInsuranceCourseType(Long courseId) {
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), insuranceCourseTypeMapper.DTO_Course(insuranceCourseTypeRepository.getById(courseId)));
    }


    @Override
    public BaseDTO getAllInsuranceCoursesType() {
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), insuranceCourseTypeMapper.DTO_LIST(insuranceCourseTypeRepository.findAll()));
    }

    @Override
    public BaseDTO getAllInsuranceCourseTypeByCompanyId(Long companyId) {
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), insuranceCourseTypeMapper.DTO_LIST(insuranceCourseTypeRepository.findAllByCompany_Id(companyId)));
    }

    @Override
    public BaseDTO createInsuranceCourseType(Long companyId, InsuranceCourseType insuranceCourseType) {
        Company company = companyRepository.getById(companyId);
        insuranceCourseType.setCompany(company);
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), insuranceCourseTypeMapper.DTO_Course(insuranceCourseTypeRepository.save(insuranceCourseType)));

    }

    @Override
    public BaseDTO updateInsuranceCourseType(Long courseId, InsuranceCourseType updatedInsuranceCourseType) {
        InsuranceCourseType existingInsuranceCourseType = insuranceCourseTypeRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("InsuranceCourseType not found"));
        existingInsuranceCourseType.setCode(updatedInsuranceCourseType.getCode());
        existingInsuranceCourseType.setName(updatedInsuranceCourseType.getName());
        insuranceCourseTypeRepository.save(existingInsuranceCourseType);
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), insuranceCourseTypeMapper.DTO_Course(insuranceCourseTypeRepository.save(existingInsuranceCourseType)));

    }

    @Override
    public BaseDTO deleteInsuranceCourseType(Long courseId) {
        InsuranceCourseType insuranceCourseType = insuranceCourseTypeRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("InsuranceCourseType not found"));
        insuranceCourseType.setDeleted(true);
        insuranceCourseTypeRepository.save(insuranceCourseType);
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), insuranceCourseTypeMapper.DTO_Course(insuranceCourseType));

    }


}