package com.haytech.demo_referralmanagement.service.intrface;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.dto.CoreInsuranceCourseTypeDTO;

public interface CoreInsuranceCourseTypeService {
    BaseDTO findById(Long coreInsuranceCourseId);

    BaseDTO findAll(int page, int size);
    BaseDTO findAll();

    BaseDTO createCoreInsuranceCourseType(CoreInsuranceCourseTypeDTO coreInsuranceCourseType);

    BaseDTO updateCoreInsuranceCourseType(Long coreInsuranceCourseId, CoreInsuranceCourseTypeDTO coreInsuranceCourseType);

    BaseDTO deleteCoreInsuranceCourseType(Long coreInsuranceCourseId);
}
