package com.haytech.demo_referralmanagement.service.intrface;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.entity.CoreInsuranceCourseType;

public interface CoreInsuranceCourseTypeService {
    BaseDTO findById(Long coreInsuranceCourseId);

    BaseDTO findAll();

    BaseDTO createCoreInsuranceCourseType(CoreInsuranceCourseType coreInsuranceCourseType);

    BaseDTO updateCoreInsuranceCourseType(Long coreInsuranceCourseId, CoreInsuranceCourseType coreInsuranceCourseType);

    BaseDTO deleteCoreInsuranceCourseType(Long coreInsuranceCourseId);
}
