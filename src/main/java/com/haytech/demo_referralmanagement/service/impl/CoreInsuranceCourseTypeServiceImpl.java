package com.haytech.demo_referralmanagement.service.impl;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.base.MetaDTO;
import com.haytech.demo_referralmanagement.model.dto.CoreInsuranceCourseTypeDTO;
import com.haytech.demo_referralmanagement.model.entity.CoreInsuranceCourseType;
import com.haytech.demo_referralmanagement.model.mapper.CoreInsuranceCourseTypeMapper;
import com.haytech.demo_referralmanagement.repository.CoreInsuranceCourseTypeRepository;
import com.haytech.demo_referralmanagement.service.intrface.CoreInsuranceCourseTypeService;
import com.haytech.demo_referralmanagement.utility.ApplicationProperties;
import com.haytech.demo_referralmanagement.utility.PageableUtility;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoreInsuranceCourseTypeServiceImpl implements CoreInsuranceCourseTypeService {
    private final ApplicationProperties applicationProperties;

    private final CoreInsuranceCourseTypeMapper coreInsuranceCourseTypeMapper;

    private final CoreInsuranceCourseTypeRepository coreInsuranceCourseTypeRepository;
    private final PageableUtility pageableUtility;

    public CoreInsuranceCourseTypeServiceImpl(ApplicationProperties applicationProperties, CoreInsuranceCourseTypeMapper coreInsuranceCourseTypeMapper, CoreInsuranceCourseTypeRepository coreInsuranceCourseTypeRepository, PageableUtility pageableUtility) {
        this.applicationProperties = applicationProperties;
        this.coreInsuranceCourseTypeMapper = coreInsuranceCourseTypeMapper;
        this.coreInsuranceCourseTypeRepository = coreInsuranceCourseTypeRepository;
        this.pageableUtility = pageableUtility;
    }
    @Override
    public BaseDTO findById(Long coreInsuranceCourseId) {
        return new BaseDTO(MetaDTO.getInstance(applicationProperties),
                coreInsuranceCourseTypeMapper.DTO_CoreInsuranceCourseType(
                        coreInsuranceCourseTypeRepository.findById(coreInsuranceCourseId).orElseThrow(() -> new NotFoundException("not exist"))));
    }
    @Override
    public BaseDTO findAll(int page, int size) {
        Pageable pageable = pageableUtility.createPageable(page, size, Sort.by(Sort.Order.asc("id")));
        Page<CoreInsuranceCourseType> coreInsuranceCourseTypes = coreInsuranceCourseTypeRepository.findAll(pageable);
        List<CoreInsuranceCourseTypeDTO> coreInsuranceCourseTypeDTOS = coreInsuranceCourseTypes.stream()
                .map(coreInsuranceCourseTypeMapper::DTO_CoreInsuranceCourseType)
                .collect(Collectors.toList());
        Page<CoreInsuranceCourseTypeDTO> coreInsuranceCourseTypeDTOPage = new PageImpl<>(coreInsuranceCourseTypeDTOS, pageable, coreInsuranceCourseTypes.getTotalElements());
        return new BaseDTO(MetaDTO.getInstance(applicationProperties),coreInsuranceCourseTypeDTOPage);

    }
    @Override
    public BaseDTO createCoreInsuranceCourseType(CoreInsuranceCourseTypeDTO coreInsuranceCourseType) {

        return new BaseDTO(MetaDTO.getInstance(applicationProperties
        ), coreInsuranceCourseTypeMapper.DTO_CoreInsuranceCourseType(
                coreInsuranceCourseTypeRepository.save(CoreInsuranceCourseType.builder()
                        .name(coreInsuranceCourseType.getName())
                        .coreName(coreInsuranceCourseType.isCoreName())
                        .insuranceCourseTypeList(coreInsuranceCourseType.getInsuranceCourseTypeList())
                        .build())));
    }
    @Override
    public BaseDTO updateCoreInsuranceCourseType(Long coreInsuranceCourseId, CoreInsuranceCourseTypeDTO coreInsuranceCourseType) {
        CoreInsuranceCourseType insuranceCourseType =
                coreInsuranceCourseTypeRepository.findById(coreInsuranceCourseId).orElseThrow(() -> new NotFoundException("not exist"));
        insuranceCourseType.setName(insuranceCourseType.getName());
        insuranceCourseType.setCoreName(coreInsuranceCourseType.isCoreName());
        insuranceCourseType.setInsuranceCourseTypeList(coreInsuranceCourseType.getInsuranceCourseTypeList());
        return new BaseDTO(MetaDTO.getInstance(applicationProperties),
                coreInsuranceCourseTypeMapper.DTO_CoreInsuranceCourseType(coreInsuranceCourseTypeRepository.save(insuranceCourseType)));
    }
    @Override
    public BaseDTO deleteCoreInsuranceCourseType(Long coreInsuranceCourseId) {
        CoreInsuranceCourseType insuranceCourseType =
                coreInsuranceCourseTypeRepository.findById(coreInsuranceCourseId).orElseThrow(() -> new NotFoundException("not exist"));
        insuranceCourseType.setDeleted(true);
        return new BaseDTO(MetaDTO.getInstance(applicationProperties),
                coreInsuranceCourseTypeMapper.DTO_CoreInsuranceCourseType(coreInsuranceCourseTypeRepository.save(insuranceCourseType)));
    }
}
