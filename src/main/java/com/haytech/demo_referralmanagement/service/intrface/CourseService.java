package com.haytech.demo_referralmanagement.service.intrface;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.entity.Course;
import com.haytech.demo_referralmanagement.model.enums.ReferrType;

import java.util.List;

public interface CourseService {
    Course createCourse(Course course);


    List<Course> getAllCourses();

    Course updateCourse(Long courseId, Course updatedCourse);

    void deleteCourse(Long courseId);

    Course getCourse(Long courseId);
}