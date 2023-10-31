package com.haytech.demo_referralmanagement.service.intrface;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.entity.Course;
import com.haytech.demo_referralmanagement.model.enums.ReferrType;

import java.util.List;

public interface CourseService {
    BaseDTO createCourse(Course course);


    BaseDTO getAllCourses();

    BaseDTO updateCourse(Long courseId, Course updatedCourse);

    BaseDTO deleteCourse(Long courseId);

    BaseDTO getCourse(Long courseId);
}