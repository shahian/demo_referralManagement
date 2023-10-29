package com.haytech.demo_referralmanagement.service;

import com.haytech.demo_referralmanagement.model.entity.Company;
import com.haytech.demo_referralmanagement.model.entity.Course;
import com.haytech.demo_referralmanagement.repository.CompanyRepository;
import com.haytech.demo_referralmanagement.repository.CourseRepository;
import com.haytech.demo_referralmanagement.service.impl.CompanyCourseServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

public class CompanyCourseServiceImplTest {
    @InjectMocks
    private CompanyCourseServiceImpl companyCourseService;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private CourseRepository courseRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddCourseToCompany() {
        Long companyId = 1L;
        Course course = new Course();
        course.setName("Test Course");
        course.setCode("COURSE123");

        Company company = new Company();
        company.setId(companyId);
        List<Course> companyCourses = new ArrayList<>();
        company.setCourses(companyCourses);

        // Mock the behavior of the company repository
        Mockito.when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));

        // Mock the behavior of the course repository
        Mockito.when(courseRepository.save(Mockito.any(Course.class))).thenReturn(course);

        companyCourseService.addCourseToCompany(companyId, course);

        // Verify that the company's courses set contains the added course
        Assertions.assertTrue(company.getCourses().contains(course));
    }

    @Test
    public void testGetCoursesByCompany() {
        Long companyId = 1L;
        Company company = new Company();
        company.setId(companyId);
        List<Course> companyCourses = new ArrayList<>();
        Course course1 = new Course();
        course1.setId(1L);
        companyCourses.add(course1);
        company.setCourses( companyCourses);

        // Mock the behavior of the company repository
        Mockito.when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));

        List<Course> courses = companyCourseService.getCoursesByCompany(companyId);

        // Verify that the returned set of courses matches the company's courses
        Assertions.assertEquals(companyCourses, courses);
    }

    @Test
    public void testRemoveCourseFromCompany() {
        Long companyId = 1L;
        Long courseId = 1L;
        Company company = new Company();
        company.setId(companyId);
        List<Course> companyCourses = new ArrayList<>();

        Course course1 = new Course();
        course1.setId(1L);
        companyCourses.add(course1);
        company.setCourses( companyCourses);

        Course courseToRemove = new Course();
        courseToRemove.setId(courseId);

        // Mock the behavior of the company and course repositories
        Mockito.when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));
        Mockito.when(courseRepository.findById(courseId)).thenReturn(Optional.of(courseToRemove));

        companyCourseService.removeCourseFromCompany(companyId, courseId);

        // Verify that the course is removed from the company's courses
        Assertions.assertFalse(company.getCourses().contains(courseToRemove));
    }
}