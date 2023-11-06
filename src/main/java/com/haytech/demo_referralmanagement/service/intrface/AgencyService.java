package com.haytech.demo_referralmanagement.service.intrface;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.dto.AgencyDTO;

public interface AgencyService {
    BaseDTO getAllAgencies();

    BaseDTO getAgencyById(Long id);

    BaseDTO createAgency(AgencyDTO agencyDTO);

    BaseDTO updateAgency(Long id, AgencyDTO agencyDTO);

    BaseDTO deleteAgency(Long id);
}
