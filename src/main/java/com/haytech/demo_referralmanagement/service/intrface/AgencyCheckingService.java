package com.haytech.demo_referralmanagement.service.intrface;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.dto.AgencyCheckingDTO;
import com.haytech.demo_referralmanagement.model.enums.ReferrType;
import com.haytech.demo_referralmanagement.model.request.AgencyCheckingRequest;

public interface AgencyCheckingService {
    BaseDTO getAll();

    BaseDTO filterAgencyCheckingQuery(Long personnelId, String insuranceNumber, String nationalCode, boolean isDone, String checkingTypeName);
    BaseDTO filterAgencyCheckingCriteria(Long personnelId, String insuranceNumber, String nationalCode, boolean isDone, String checkingTypeName);

    BaseDTO getAgencyCheckingById(Long agencyCheckingId);

    BaseDTO createAgencyChecking(AgencyCheckingRequest agencyCheckingRequest);

    BaseDTO updateAgencyChecking(Long agencyCheckingId, AgencyCheckingDTO updatedAgencyChecking);

    BaseDTO deleteAgencyChecking(Long agencyCheckingId);
}
