package com.haytech.demo_referralmanagement.service.intrface;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.entity.InsuranceCourseType;

public interface InsuranceCourseTypeService {
    BaseDTO createInsuranceCourseType(Long companyId, InsuranceCourseType insuranceCourseType);


    BaseDTO getAllInsuranceCoursesType();

    BaseDTO getAllInsuranceCourseTypeByCompanyId(Long courseId);

    BaseDTO updateInsuranceCourseType(Long courseId, InsuranceCourseType updatedInsuranceCourseType);

    BaseDTO deleteInsuranceCourseType(Long courseId);

    BaseDTO getInsuranceCourseType(Long courseId);
}