package com.haytech.demo_referralmanagement.service.impl;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.base.MetaDTO;
import com.haytech.demo_referralmanagement.model.dto.AgencyCheckingDTO;
import com.haytech.demo_referralmanagement.model.entity.*;
import com.haytech.demo_referralmanagement.model.mapper.AgencyCheckingMapper;
import com.haytech.demo_referralmanagement.model.request.AgencyCheckingRequest;
import com.haytech.demo_referralmanagement.repository.AgencyCheckingRepository;
import com.haytech.demo_referralmanagement.repository.AgencyRepository;
import com.haytech.demo_referralmanagement.repository.CheckingTypeRepository;
import com.haytech.demo_referralmanagement.repository.FanavaranPolicyRepository;
import com.haytech.demo_referralmanagement.repository.specification.ReferralManagementSpecifications;
import com.haytech.demo_referralmanagement.service.intrface.AgencyCheckingService;
import com.haytech.demo_referralmanagement.utility.ApplicationProperties;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class AgencyCheckingServiceImpl implements AgencyCheckingService {
    private final ApplicationProperties applicationProperties;
    private final AgencyCheckingRepository agencyCheckingRepository;
    private final FanavaranPolicyRepository fanavaranPolicyRepository;
    private final AgencyRepository agencyRepository;
    private final CheckingTypeRepository checkingTypeRepository;

    private final AgencyCheckingMapper agencyCheckingMapper;

    public AgencyCheckingServiceImpl(ApplicationProperties applicationProperties, AgencyCheckingRepository agencyCheckingRepository, FanavaranPolicyRepository fanavaranPolicyRepository, AgencyRepository agencyRepository, CheckingTypeRepository checkingTypeRepository, AgencyCheckingMapper agencyCheckingMapper) {
        this.applicationProperties = applicationProperties;
        this.agencyCheckingRepository = agencyCheckingRepository;
        this.fanavaranPolicyRepository = fanavaranPolicyRepository;
        this.agencyRepository = agencyRepository;
        this.checkingTypeRepository = checkingTypeRepository;
        this.agencyCheckingMapper = agencyCheckingMapper;
    }

    @Override
    public BaseDTO getAll() {
        List<AgencyCheckingDTO> agencyCheckingDTOS = agencyCheckingMapper.DTO_LIST(agencyCheckingRepository.findAllByCriteria());
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), agencyCheckingDTOS);
    }

    public BaseDTO filterAgencyCheckingQuery(
            Long personnelId,
            String insuranceNumber,
            String nationalCode,
            boolean isDone,
            String checkingTypeName) {
        List<AgencyChecking> filteredData = filterData(personnelId, insuranceNumber, nationalCode, isDone, checkingTypeName);
        List<AgencyCheckingDTO> result = agencyCheckingMapper.DTO_LIST(filteredData);
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), result);

    }

    public BaseDTO filterAgencyCheckingCriteria(
            Long personnelId,
            String insuranceNumber,
            String nationalCode,
            boolean isDone,
            String checkingTypeName) {

        Specification<AgencyChecking> spec = ReferralManagementSpecifications.findByCriteria(
                personnelId, insuranceNumber, nationalCode, isDone, checkingTypeName);
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), agencyCheckingMapper.DTO_LIST(agencyCheckingRepository.findAll(spec)));
    }

    private List<AgencyChecking> filterData(
            Long personnelId,
            String insuranceNumber,
            String nationalCode,
            boolean isDone,
            String checkingTypeName) {
        return agencyCheckingRepository.findByQuery(personnelId, insuranceNumber, nationalCode, isDone, checkingTypeName);
    }

    @Override
    public BaseDTO getAgencyCheckingById(Long agencyCheckingId) {
        return new BaseDTO(MetaDTO.getInstance(applicationProperties),
                agencyCheckingMapper.DTO_AgencyChecking(
                        agencyCheckingRepository.findById(agencyCheckingId).orElseThrow(() -> new EntityNotFoundException("AgencyChecking Not Fund"))));
    }

    @Override
    public BaseDTO createAgencyChecking(AgencyCheckingRequest agencyCheckingRequest) {
        Agency agency = agencyRepository.findById(agencyCheckingRequest.getAgencyId())
                .orElseThrow(() -> new EntityNotFoundException("Agency not found"));
        CheckingType checkingType = checkingTypeRepository.findById(agencyCheckingRequest.getCheckingTypeId())
                .orElseThrow(() -> new EntityNotFoundException("Checking Type not found"));
        FanavaranPolicy fanavaranPolicy = fanavaranPolicyRepository.findById(agencyCheckingRequest.getFanavaranPolicyId())
                .orElseThrow(() -> new EntityNotFoundException("FanavaranPolicy not found"));
        AgencyChecking newAgencyChecking = AgencyChecking.builder()
                .fanavaranPolicy(fanavaranPolicy)
                .checkingType(checkingType)
                .agency(agency)
                .isDone(agencyCheckingRequest.isDone())
                .isUnwilling(agencyCheckingRequest.isUnwilling())
                .build();
        AgencyChecking savedAgencyChecking = agencyCheckingRepository.save(newAgencyChecking);
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), agencyCheckingMapper.DTO_AgencyChecking(savedAgencyChecking));
    }

    @Override
    public BaseDTO updateAgencyChecking(Long agencyCheckingId, AgencyCheckingDTO updatedAgencyChecking) {
        AgencyChecking existingAgencyChecking = agencyCheckingRepository.findById(agencyCheckingId)
                .orElseThrow(() -> new EntityNotFoundException("AgencyChecking not found"));
        existingAgencyChecking.setDone(updatedAgencyChecking.isDone());
        existingAgencyChecking.setUnwilling(updatedAgencyChecking.isUnwilling());
        agencyCheckingRepository.save(existingAgencyChecking);
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), agencyCheckingMapper.DTO_AgencyChecking(existingAgencyChecking));
    }

    @Override
    public BaseDTO deleteAgencyChecking(Long agencyCheckingId) {
        AgencyChecking agencyChecking = agencyCheckingRepository.findById(agencyCheckingId)
                .orElseThrow(() -> new EntityNotFoundException("AgencyChecking not found"));
        agencyChecking.setDeleted(true);
        agencyCheckingRepository.save(agencyChecking);
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), agencyChecking);
    }
}

