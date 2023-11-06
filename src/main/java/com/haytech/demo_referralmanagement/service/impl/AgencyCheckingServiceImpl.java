package com.haytech.demo_referralmanagement.service.impl;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.base.MetaDTO;
import com.haytech.demo_referralmanagement.model.dto.AgencyCheckingDTO;
import com.haytech.demo_referralmanagement.model.entity.AgencyChecking;
import com.haytech.demo_referralmanagement.model.entity.ReferralManagement;
import com.haytech.demo_referralmanagement.model.enums.ReferrType;
import com.haytech.demo_referralmanagement.model.mapper.AgencyCheckingMapper;
import com.haytech.demo_referralmanagement.repository.AgencyCheckingRepository;
import com.haytech.demo_referralmanagement.repository.specification.ReferralManagementSpecifications;
import com.haytech.demo_referralmanagement.service.intrface.AgencyCheckingService;
import com.haytech.demo_referralmanagement.utility.ApplicationProperties;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgencyCheckingServiceImpl implements AgencyCheckingService {
    private final ApplicationProperties applicationProperties;
    private final AgencyCheckingRepository agencyCheckingRepository;

    private final AgencyCheckingMapper agencyCheckingMapper;

    public AgencyCheckingServiceImpl(ApplicationProperties applicationProperties, AgencyCheckingRepository agencyCheckingRepository, AgencyCheckingMapper agencyCheckingMapper) {
        this.applicationProperties = applicationProperties;
        this.agencyCheckingRepository = agencyCheckingRepository;
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

}

