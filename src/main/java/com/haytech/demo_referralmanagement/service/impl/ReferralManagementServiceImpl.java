package com.haytech.demo_referralmanagement.service.impl;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.base.MetaDTO;
import com.haytech.demo_referralmanagement.model.dto.ReferralManagementDTO;
import com.haytech.demo_referralmanagement.model.entity.ReferralManagement;
import com.haytech.demo_referralmanagement.model.enums.ReferrType;
import com.haytech.demo_referralmanagement.model.mapper.ReferralManagementMapper;
import com.haytech.demo_referralmanagement.repository.ReferralManagementRepository;
import com.haytech.demo_referralmanagement.repository.specification.ReferralManagementSpecifications;
import com.haytech.demo_referralmanagement.service.intrface.ReferralManagementService;
import com.haytech.demo_referralmanagement.utility.ApplicationProperties;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferralManagementServiceImpl implements ReferralManagementService {
    private final ApplicationProperties applicationProperties;
    private final ReferralManagementRepository referralManagementRepository;

    private final ReferralManagementMapper referralManagementMapper;

    public ReferralManagementServiceImpl(ApplicationProperties applicationProperties, ReferralManagementRepository referralManagementRepository, ReferralManagementMapper referralManagementMapper) {
        this.applicationProperties = applicationProperties;
        this.referralManagementRepository = referralManagementRepository;
        this.referralManagementMapper = referralManagementMapper;
    }

    @Override
    public BaseDTO getAll() {
        List<ReferralManagementDTO> referralManagementDTOS = referralManagementMapper.DTO_LIST(referralManagementRepository.findAllByCriteria());
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), referralManagementDTOS);
    }

    public BaseDTO filterReferralManagementQuery(
            Long personnelId,
            String insuranceNumber,
            String nationalCode,
            boolean processed,
            ReferrType referrType) {
        List<ReferralManagement> filteredData = filterData(personnelId, insuranceNumber, nationalCode, processed, referrType);
        List<ReferralManagementDTO> result = referralManagementMapper.DTO_LIST(filteredData);
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), result);

    }

    public BaseDTO filterReferralManagementCriteria(
            Long personnelId,
            String insuranceNumber,
            String nationalCode,
            boolean processed,
            ReferrType referrType) {

        Specification<ReferralManagement> spec = ReferralManagementSpecifications.findByCriteria(
                personnelId, insuranceNumber, nationalCode, processed, referrType);

        // Use the specification to query the repository
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), referralManagementMapper.DTO_LIST(referralManagementRepository.findAll(spec)));
    }

    private List<ReferralManagement> filterData(
            Long personnelId,
            String insuranceNumber,
            String nationalCode,
            boolean processed,
            ReferrType referrType) {
        return referralManagementRepository.findByQuery(personnelId, insuranceNumber, nationalCode, processed, referrType);
    }

}

