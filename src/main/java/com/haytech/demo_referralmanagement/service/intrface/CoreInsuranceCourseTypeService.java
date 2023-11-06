package com.haytech.demo_referralmanagement.service.intrface;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.dto.CoreInsuranceCourseTypeDTO;
import com.haytech.demo_referralmanagement.model.entity.CoreInsuranceCourseType;

public interface CoreInsuranceCourseTypeService {
    BaseDTO findById(Long coreInsuranceCourseId);

    BaseDTO findAll();

    BaseDTO createCoreInsuranceCourseType(CoreInsuranceCourseTypeDTO coreInsuranceCourseType);

    BaseDTO updateCoreInsuranceCourseType(Long coreInsuranceCourseId, CoreInsuranceCourseTypeDTO coreInsuranceCourseType);

    BaseDTO deleteCoreInsuranceCourseType(Long coreInsuranceCourseId);
}
