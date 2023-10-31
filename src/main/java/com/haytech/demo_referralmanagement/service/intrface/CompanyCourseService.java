package com.haytech.demo_referralmanagement.service.intrface;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.entity.Course;
import com.haytech.demo_referralmanagement.model.enums.ReferrType;

import java.util.List;
import java.util.Set;


public interface CompanyCourseService {
    BaseDTO addCourseToCompany(Long companyId, Course course);

    BaseDTO getCoursesByCompany(Long companyId);

    BaseDTO removeCourseFromCompany(Long companyId, Long courseId);
}