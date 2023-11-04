package com.haytech.demo_referralmanagement.service.impl;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.base.MetaDTO;
import com.haytech.demo_referralmanagement.model.entity.Company;
import com.haytech.demo_referralmanagement.model.entity.CoreInsuranceCourseType;
import com.haytech.demo_referralmanagement.model.entity.InsuranceCourseType;
import com.haytech.demo_referralmanagement.model.mapper.InsuranceCourseTypeMapper;
import com.haytech.demo_referralmanagement.repository.CompanyRepository;
import com.haytech.demo_referralmanagement.repository.CoreInsuranceCourseTypeRepository;
import com.haytech.demo_referralmanagement.repository.InsuranceCourseTypeRepository;
import com.haytech.demo_referralmanagement.service.intrface.InsuranceCourseTypeService;
import com.haytech.demo_referralmanagement.utility.ApplicationProperties;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class InsuranceCourseTypeServiceImpl implements InsuranceCourseTypeService {
    private final CompanyRepository companyRepository;
    private final InsuranceCourseTypeRepository insuranceCourseTypeRepository;
    private final CoreInsuranceCourseTypeRepository coreInsuranceCourseTypeRepository;
    private final ApplicationProperties applicationProperties;
    private final InsuranceCourseTypeMapper insuranceCourseTypeMapper;

    public InsuranceCourseTypeServiceImpl(InsuranceCourseTypeRepository insuranceCourseTypeRepository, CompanyRepository companyRepository, CoreInsuranceCourseTypeRepository coreInsuranceCourseTypeRepository, ApplicationProperties applicationProperties, InsuranceCourseTypeMapper insuranceCourseTypeMapper) {
        this.insuranceCourseTypeRepository = insuranceCourseTypeRepository;
        this.companyRepository = companyRepository;
        this.coreInsuranceCourseTypeRepository = coreInsuranceCourseTypeRepository;
        this.applicationProperties = applicationProperties;
        this.insuranceCourseTypeMapper = insuranceCourseTypeMapper;
    }

    @Override
    public BaseDTO getInsuranceCourseType(Long courseId) {
        return new BaseDTO(MetaDTO.getInstance(applicationProperties),
                insuranceCourseTypeMapper.DTO_Course(insuranceCourseTypeRepository.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Not Exist..."))));
    }


    @Override
    public BaseDTO getAllInsuranceCoursesType() {
        return new BaseDTO(MetaDTO.getInstance(applicationProperties),
                insuranceCourseTypeMapper.DTO_LIST(insuranceCourseTypeRepository.findAll()));
    }

    @Override
    public BaseDTO getAllInsuranceCourseTypeByCompanyId(Long companyId) {
        return new BaseDTO(MetaDTO.getInstance(applicationProperties),
                insuranceCourseTypeMapper.DTO_LIST(insuranceCourseTypeRepository.findAllByCompany_Id(companyId)));
    }

    @Override
    public BaseDTO getAllInsuranceCourseTypeByCoreIctId(Long coreIctId) {
        return new BaseDTO(MetaDTO.getInstance(applicationProperties),
                insuranceCourseTypeMapper.DTO_LIST(insuranceCourseTypeRepository.findAllByCoreInsuranceCourseType_Id(coreIctId)));

    }

    @Override
    public BaseDTO getAllInsuranceCoursesTypeByCoreIdAndCompanyId(Long coreIctId, Long companyId) {
        return new BaseDTO(MetaDTO.getInstance(applicationProperties),
                insuranceCourseTypeMapper.DTO_LIST(insuranceCourseTypeRepository.findAllByCoreInsuranceCourseType_IdAndAndCompany_Id(coreIctId, companyId)));
    }

    @Override
    public BaseDTO createInsuranceCourseType(Long companyId, Long coreIctId, InsuranceCourseType insuranceCourseType) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new EntityNotFoundException("Not Exist..."));
        CoreInsuranceCourseType coreInsuranceCourseType = coreInsuranceCourseTypeRepository.findById(coreIctId).orElseThrow(() -> new EntityNotFoundException("Not Exist..."));
        insuranceCourseType.setCompany(company);
        insuranceCourseType.setCoreInsuranceCourseType(coreInsuranceCourseType);
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