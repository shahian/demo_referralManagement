package com.haytech.demo_referralmanagement.service.impl;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.base.MetaDTO;
import com.haytech.demo_referralmanagement.model.dto.AgencyDTO;
import com.haytech.demo_referralmanagement.model.dto.CheckingTypeDTO;
import com.haytech.demo_referralmanagement.model.entity.Agency;
import com.haytech.demo_referralmanagement.model.entity.CheckingType;
import com.haytech.demo_referralmanagement.model.mapper.AgencyMapper;
import com.haytech.demo_referralmanagement.model.mapper.CheckingTypeMapper;
import com.haytech.demo_referralmanagement.repository.AgencyRepository;
import com.haytech.demo_referralmanagement.repository.CheckingTypeRepository;
import com.haytech.demo_referralmanagement.service.intrface.AgencyService;
import com.haytech.demo_referralmanagement.service.intrface.CheckingTypeService;
import com.haytech.demo_referralmanagement.utility.ApplicationProperties;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CheckingTypeServiceImpl implements CheckingTypeService {

    private final CheckingTypeRepository checkingTypeRepository;
    private final ApplicationProperties applicationProperties;
    private final CheckingTypeMapper checkingTypeMapper;

    public CheckingTypeServiceImpl(CheckingTypeRepository checkingTypeRepository, ApplicationProperties applicationProperties, CheckingTypeMapper checkingTypeMapper) {
        this.checkingTypeRepository = checkingTypeRepository;
        this.applicationProperties = applicationProperties;
        this.checkingTypeMapper = checkingTypeMapper;
    }
    @Override
    public BaseDTO getAllCheckingTypes() {
        List<CheckingType> result = checkingTypeRepository.findAll();
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), checkingTypeMapper.DTO_LIST(result));
    }
    @Override
    public BaseDTO getCheckingTypeById(Long id) {
        return new BaseDTO(MetaDTO.getInstance(applicationProperties),
                checkingTypeMapper.DTO_CheckingType(checkingTypeRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("CheckingType Not Found..."))));
    }
    @Override
    public BaseDTO createCheckingType(CheckingTypeDTO checkingTypeDTO) {
        CheckingType checkingType = checkingTypeMapper.CheckingTypeDTO(checkingTypeDTO);
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), checkingTypeMapper.DTO_CheckingType(checkingTypeRepository.save(checkingType)));
    }
    @Override
    public BaseDTO updateCheckingType(Long id, CheckingTypeDTO checkingTypeDTO) {
        CheckingType existingCheckingType = checkingTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Checking Type not found"));
        existingCheckingType.setName(checkingTypeDTO.getName());
        checkingTypeRepository.save(existingCheckingType);
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), checkingTypeMapper.DTO_CheckingType(existingCheckingType));
    }
    @Override
    public BaseDTO deleteCheckingType(Long id) {
        CheckingType checkingType = checkingTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Checking Type not found"));
        checkingType.setDeleted(true);
        checkingTypeRepository.save(checkingType);
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), checkingType);
    }
}