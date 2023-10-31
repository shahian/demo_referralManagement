package com.haytech.demo_referralmanagement.service.impl;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.base.MetaDTO;
import com.haytech.demo_referralmanagement.model.entity.Company;
import com.haytech.demo_referralmanagement.model.entity.Course;
import com.haytech.demo_referralmanagement.repository.CompanyRepository;
import com.haytech.demo_referralmanagement.repository.CourseRepository;
import com.haytech.demo_referralmanagement.service.intrface.CompanyCourseService;
import com.haytech.demo_referralmanagement.utility.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class CompanyCourseServiceImpl implements CompanyCourseService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    ApplicationProperties applicationProperties;

    @Override
    public BaseDTO addCourseToCompany(Long companyId, Course course) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
        Course newCourse = courseRepository.save(course);
        company.getCourses().add(newCourse);
        Company saveCompany = companyRepository.save(company);
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), saveCompany);
    }

    @Override
    public BaseDTO getCoursesByCompany(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), company);
    }

    @Transactional
    @Override
    public BaseDTO removeCourseFromCompany(Long companyId, Long courseId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
        if (company.getCourses().contains(course)) {
            company.getCourses().remove(course);
            companyRepository.save(company);
            courseRepository.delete(course);
        }
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), "removeCourseFromCompany was success");
    }
}
//        if (company.getCourses().contains(course) && course.getCompanies().contains(company)) {
//            company.getCourses().remove(course);
//            course.getCompanies().remove(company);
//
//            companyRepository.save(company);
//            courseRepository.save(course);
//            companyRepository.delete(company);
//            courseRepository.delete(course);
//        }
// }
