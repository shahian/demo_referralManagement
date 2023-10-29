package com.haytech.demo_referralmanagement.service.intrface;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.entity.Course;
import com.haytech.demo_referralmanagement.model.enums.ReferrType;

import java.util.List;
import java.util.Set;


public interface CompanyCourseService {
    void addCourseToCompany(Long companyId, Course course);

    List<Course> getCoursesByCompany(Long companyId);

    void removeCourseFromCompany(Long companyId, Long courseId);
}