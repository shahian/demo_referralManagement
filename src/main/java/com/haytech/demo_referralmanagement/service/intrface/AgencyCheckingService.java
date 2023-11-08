package com.haytech.demo_referralmanagement.service.intrface;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.dto.AgencyCheckingDTO;
import com.haytech.demo_referralmanagement.model.request.AgencyCheckingRequest;

public interface AgencyCheckingService {
    BaseDTO getAll(int page, int size);

    BaseDTO filterAgencyCheckingQuery(String personnelId, String insuranceNumber, String nationalCode, boolean isDone, Long checkingTypeId, int page, int size);
    BaseDTO filterAgencyCheckingCriteria(String personnelId, String insuranceNumber, String nationalCode, boolean isDone, Long checkingTypeId, int page, int size);

    BaseDTO getAgencyCheckingById(Long agencyCheckingId);

    BaseDTO createAgencyChecking(AgencyCheckingRequest agencyCheckingRequest);

    BaseDTO updateAgencyChecking(Long agencyCheckingId, AgencyCheckingDTO updatedAgencyChecking);

    BaseDTO deleteAgencyChecking(Long agencyCheckingId);
}
