package com.haytech.demo_referralmanagement.service.intrface;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.dto.ReferralManagementDTO;
import com.haytech.demo_referralmanagement.model.enums.ReferrType;

import java.util.List;

public interface ReferralManagementService {
    BaseDTO getAll();

    BaseDTO filterReferralManagementQuery(Long personnelId, String insuranceNumber, String nationalCode, boolean processed, ReferrType referrType);
    BaseDTO filterReferralManagementCriteria(Long personnelId, String insuranceNumber, String nationalCode, boolean processed, ReferrType referrType);
}
