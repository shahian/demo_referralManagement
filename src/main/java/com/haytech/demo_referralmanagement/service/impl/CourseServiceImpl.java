package com.haytech.demo_referralmanagement.service.impl;

import com.haytech.demo_referralmanagement.model.entity.Company;
import com.haytech.demo_referralmanagement.model.entity.Course;
import com.haytech.demo_referralmanagement.repository.CourseRepository;
import com.haytech.demo_referralmanagement.service.intrface.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course updateCourse(Long courseId, Course updatedCourse) {
        Course existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        existingCourse.setName(updatedCourse.getName());

        return courseRepository.save(existingCourse);
    }

    @Override
    public void deleteCourse(Long courseId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
        if (course.getCompanies().stream().count() > 0) {
            throw new EntityExistsException("Course has Company");
        }
        courseRepository.deleteById(courseId);
    }

    @Override
    public Course getCourse(Long courseId) {
        return courseRepository.getById(courseId);
    }
}