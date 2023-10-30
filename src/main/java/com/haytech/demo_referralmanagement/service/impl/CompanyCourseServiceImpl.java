package com.haytech.demo_referralmanagement.service.impl;

import com.haytech.demo_referralmanagement.model.entity.Company;
import com.haytech.demo_referralmanagement.model.entity.Course;
import com.haytech.demo_referralmanagement.repository.CompanyRepository;
import com.haytech.demo_referralmanagement.repository.CourseRepository;
import com.haytech.demo_referralmanagement.service.intrface.CompanyCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;

@Service
public class CompanyCourseServiceImpl implements CompanyCourseService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void addCourseToCompany(Long companyId, Course course) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
        Course newCourse =courseRepository.save(course);
        company.getCourses().add(newCourse);
        companyRepository.save(company);

    }

    @Override
    public List<Course> getCoursesByCompany(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
        return  company.getCourses();
    }

    @Override
    public void removeCourseFromCompany(Long companyId, Long courseId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        if (company.getCourses().contains(course) && course.getCompanies().contains(company)) {
            company.getCourses().remove(course);
            course.getCompanies().remove(company);
            companyRepository.save(company);
            courseRepository.save(course);
        }
    }
}