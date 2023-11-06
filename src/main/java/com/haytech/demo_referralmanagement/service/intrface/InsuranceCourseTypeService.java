package com.haytech.demo_referralmanagement.service.intrface;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.dto.InsuranceCourseTypeDTO;
import com.haytech.demo_referralmanagement.model.entity.InsuranceCourseType;

public interface InsuranceCourseTypeService {
    BaseDTO createInsuranceCourseType(Long companyId,Long coreIctId, InsuranceCourseTypeDTO insuranceCourseType);
    BaseDTO getAllInsuranceCoursesType();

    BaseDTO getAllInsuranceCourseTypeByCompanyId(Long courseId);

    BaseDTO updateInsuranceCourseType(Long courseId, InsuranceCourseTypeDTO updatedInsuranceCourseType);

    BaseDTO deleteInsuranceCourseType(Long courseId);

    BaseDTO getInsuranceCourseType(Long courseId);

    BaseDTO getAllInsuranceCourseTypeByCoreIctId(Long coreIctId);

    BaseDTO getAllInsuranceCoursesTypeByCoreIdAndCompanyId(Long coreIctId, Long companyId);
}