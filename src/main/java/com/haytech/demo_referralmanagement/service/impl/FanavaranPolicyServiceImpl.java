package com.haytech.demo_referralmanagement.service.impl;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.base.MetaDTO;
import com.haytech.demo_referralmanagement.model.dto.FanavaranPolicyDTO;
import com.haytech.demo_referralmanagement.model.entity.FanavaranPolicy;
import com.haytech.demo_referralmanagement.model.mapper.FanavaranPolicyMapper;
import com.haytech.demo_referralmanagement.repository.FanavaranPolicyRepository;
import com.haytech.demo_referralmanagement.service.intrface.FanavaranPolicyService;
import com.haytech.demo_referralmanagement.utility.ApplicationProperties;
import com.haytech.demo_referralmanagement.utility.PageableUtility;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FanavaranPolicyServiceImpl implements FanavaranPolicyService {
    private final FanavaranPolicyRepository fanavaranPolicyRepository;
    private final ApplicationProperties applicationProperties;
    private final FanavaranPolicyMapper fanavaranPolicyMapper;
    private final PageableUtility pageableUtility;


    public FanavaranPolicyServiceImpl(FanavaranPolicyRepository fanavaranPolicyRepository, ApplicationProperties applicationProperties, FanavaranPolicyMapper fanavaranPolicyMapper, PageableUtility pageableUtility) {
        this.fanavaranPolicyRepository = fanavaranPolicyRepository;
        this.applicationProperties = applicationProperties;
        this.fanavaranPolicyMapper = fanavaranPolicyMapper;
        this.pageableUtility = pageableUtility;
    }

    @Override
    public BaseDTO getFanavaranPolicyById(Long fanavaranPolicyId) {
        FanavaranPolicy fanavaranPolicy = fanavaranPolicyRepository.findById(fanavaranPolicyId).orElseThrow(() -> new EntityNotFoundException("Not Found"));
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), fanavaranPolicyMapper.DTO_FanavaranPolicyDTO(fanavaranPolicy));
    }

    @Override
    public BaseDTO getAllFanavaranPolicies(int page, int size) {
        Pageable pageable = pageableUtility.createPageable(page, size, Sort.by(Sort.Order.asc("id")));
        Page<FanavaranPolicy> fanavaranPolicies = fanavaranPolicyRepository.findAll(pageable);
        List<FanavaranPolicyDTO> fanavaranPolicyDTOS = fanavaranPolicies.stream()
                .map(fanavaranPolicyMapper::DTO_FanavaranPolicyDTO)
                .collect(Collectors.toList());
        Page<FanavaranPolicyDTO> fanavaranPolicyDTOPage = new PageImpl<>(fanavaranPolicyDTOS, pageable, fanavaranPolicies.getTotalElements());
        return new BaseDTO(MetaDTO.getInstance(applicationProperties),fanavaranPolicyDTOPage);
    }
    @Override
    public BaseDTO createFanavaranPolicy(FanavaranPolicyDTO fanavaranPolicyDTO) {
        FanavaranPolicy fanavaranPolicy = fanavaranPolicyMapper.FanavaranPolicy_DTO(fanavaranPolicyDTO);
        fanavaranPolicy = fanavaranPolicyRepository.save(fanavaranPolicy);
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), fanavaranPolicyMapper.DTO_FanavaranPolicyDTO(fanavaranPolicy));
    }
    @Override
    public BaseDTO updateFanavaranPolicy(Long fanavaranPolicyId, FanavaranPolicyDTO updatedFanavaranPolicy) {
        FanavaranPolicy existingFanavaranPolicy = fanavaranPolicyRepository.findById(fanavaranPolicyId)
                .orElseThrow(() -> new EntityNotFoundException("FanavaranPolicy not found"));
        existingFanavaranPolicy.setPolicyId(updatedFanavaranPolicy.getPolicyId());
        existingFanavaranPolicy.setInsuranceNumber(updatedFanavaranPolicy.getInsuranceNumber());
        existingFanavaranPolicy.setBeginDate(updatedFanavaranPolicy.getBeginDate());
        existingFanavaranPolicy.setEndDate(updatedFanavaranPolicy.getEndDate());
        existingFanavaranPolicy.setIssueDate(updatedFanavaranPolicy.getIssueDate());
        existingFanavaranPolicy.setFkInsuranceCourseTypeId(updatedFanavaranPolicy.getFkInsuranceCourseTypeId());
        existingFanavaranPolicy.setGid(updatedFanavaranPolicy.getGid());
        existingFanavaranPolicy.setPersonnelId(updatedFanavaranPolicy.getPersonnelId());
        existingFanavaranPolicy.setCompleteInsuranceNumber(updatedFanavaranPolicy.getCompleteInsuranceNumber());
        existingFanavaranPolicy.setAgencyName(updatedFanavaranPolicy.getAgencyName());
        existingFanavaranPolicy.setNationalCode(updatedFanavaranPolicy.getNationalCode());
        existingFanavaranPolicy.setAgencyCheckings(updatedFanavaranPolicy.getAgencyCheckings());
        fanavaranPolicyRepository.save(existingFanavaranPolicy);
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), fanavaranPolicyMapper.DTO_FanavaranPolicyDTO(existingFanavaranPolicy));
    }

    @Override
    public BaseDTO deleteFanavaranPolicy(Long fanavaranPolicyId) {
        FanavaranPolicy fanavaranPolicy = fanavaranPolicyRepository.findById(fanavaranPolicyId)
                .orElseThrow(() -> new EntityNotFoundException("FanavaranPolicy not found"));
        fanavaranPolicy.setDeleted(true);
        fanavaranPolicyRepository.save(fanavaranPolicy);
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), fanavaranPolicy);
    }
}