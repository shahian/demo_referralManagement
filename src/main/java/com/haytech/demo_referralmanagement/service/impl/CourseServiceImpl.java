package com.haytech.demo_referralmanagement.service.impl;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.base.MetaDTO;
import com.haytech.demo_referralmanagement.model.entity.Course;
import com.haytech.demo_referralmanagement.model.mapper.CourseMapper;
import com.haytech.demo_referralmanagement.repository.CourseRepository;
import com.haytech.demo_referralmanagement.service.intrface.CourseService;
import com.haytech.demo_referralmanagement.utility.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    private final ApplicationProperties applicationProperties;
    private final CourseMapper courseMapper;

    public CourseServiceImpl(ApplicationProperties applicationProperties, CourseMapper courseMapper) {
        this.applicationProperties = applicationProperties;
        this.courseMapper = courseMapper;
    }

    @Override
    public BaseDTO createCourse(Course course) {
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), courseMapper.DTO_Course(courseRepository.save(course)));

    }

    @Override
    public BaseDTO getAllCourses() {
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), courseMapper.DTO_LIST(courseRepository.findAll()));

    }

    @Override
    public BaseDTO updateCourse(Long courseId, Course updatedCourse) {
        Course existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
        existingCourse.setName(updatedCourse.getName());
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), courseMapper.DTO_Course(courseRepository.save(existingCourse)));

    }

    @Override
    public BaseDTO deleteCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
        if (course.getCompanies().stream().count() > 0) {
            throw new EntityExistsException("Course has Company");
        }
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), courseMapper.DTO_Course(course));

    }

    @Override
    public BaseDTO getCourse(Long courseId) {
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), courseMapper.DTO_Course(courseRepository.getById(courseId)));
    }
}