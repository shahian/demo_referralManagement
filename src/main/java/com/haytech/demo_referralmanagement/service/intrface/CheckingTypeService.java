package com.haytech.demo_referralmanagement.service.intrface;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.dto.AgencyDTO;
import com.haytech.demo_referralmanagement.model.dto.CheckingTypeDTO;

public interface CheckingTypeService {
    BaseDTO getAllCheckingTypes();

    BaseDTO getCheckingTypeById(Long id);

    BaseDTO createCheckingType(CheckingTypeDTO checkingTypeDTO);

    BaseDTO updateCheckingType(Long id, CheckingTypeDTO checkingTypeDTO);

    BaseDTO deleteCheckingType(Long id);
}
