package com.haytech.demo_referralmanagement.serviceTest;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.dto.CompanyDTO;
import com.haytech.demo_referralmanagement.model.dto.CourseDTO;
import com.haytech.demo_referralmanagement.model.entity.Company;
import com.haytech.demo_referralmanagement.model.entity.Course;
import com.haytech.demo_referralmanagement.model.mapper.CourseMapper;
import com.haytech.demo_referralmanagement.repository.CourseRepository;
import com.haytech.demo_referralmanagement.service.impl.CourseServiceImpl;
import com.haytech.demo_referralmanagement.utility.ApplicationProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityExistsException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

public class CourseServiceImplTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private ApplicationProperties applicationProperties;

    @Mock
    private CourseMapper courseMapper;

    @InjectMocks
    private CourseServiceImpl courseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCompany() {
        Course  newCourse= new Course(); // Create a Company object for testing
        CourseDTO courseDTO = new CourseDTO(); // Create a CompanyDTO object for testing

        // Mock behavior of your dependencies
        Mockito.when(courseRepository.save(newCourse)).thenReturn(newCourse);
        Mockito.when(courseMapper.DTO_Course(newCourse)).thenReturn(courseDTO);

        // Call the method you want to test
        BaseDTO result = courseService.createCourse(newCourse);

        // Verify the result
        assertEquals(courseDTO, result.getObject());
    }
    @Test
    public void testGetAllCompanies() {
        List<Course> courses = Arrays.asList(new Course(), new Course()); // Create a list of companies for testing
        List<CourseDTO> courseDTOS = Arrays.asList(new CourseDTO(), new CourseDTO()); // Create a list of company DTOs for testing

        // Mock behavior of your dependencies
        Mockito.when(courseRepository.findAll()).thenReturn(courses);
        Mockito.when(courseMapper.DTO_LIST(courses)).thenReturn(courseDTOS);

        // Call the method you want to test
        BaseDTO result = courseService.getAllCourses();

        // Verify the result
        assertEquals(courseDTOS, result.getObject());
    }

    @Test
    public void testUpdateCompany() {
        Long companyId = 1L;
        Course existingCourse = new Course();
        CourseDTO courseDTO = new CourseDTO(); // Create a CompanyDTO object for testing
        Course updatedCompany = new Course();

        // Mock behavior of your dependencies
        Mockito.when(courseRepository.findById(companyId)).thenReturn(java.util.Optional.of(existingCourse));
        Mockito.when(courseMapper.DTO_Course(existingCourse)).thenReturn(courseDTO);

        // Call the method you want to test
        BaseDTO result = courseService.updateCourse(companyId, updatedCompany);

        // Verify the result
        assertEquals(courseDTO, result.getObject());
    }
    @Test
    public void testDeleteCompany() {
        Long companyId = 1L;
        Course course = new Course();
        course.setCompanies(Arrays.asList(new Company()));

        // Mock behavior of your dependencies
        Mockito.when(courseRepository.findById(companyId)).thenReturn(java.util.Optional.of(course));

        // Call the method you want to test and expect an exception
        assertThrows(EntityExistsException.class, () -> courseService.deleteCourse(companyId));

        // Verify that companyRepository.deleteById was not called
        Mockito.verify(courseRepository, times(0)).deleteById(companyId);
    }

    @Test
    public void testGetCompany() {
        Long courseId = 1L;
        Course course = new Course(); // Create a Company object for testing
        CourseDTO  courseDTO= new CourseDTO();
        // Mock behavior of your dependencies
        Mockito.when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        Mockito.when(courseMapper.DTO_Course(course)).thenReturn(courseDTO);

        // Call the method you want to test
        BaseDTO result = courseService.getCourse(courseId);

        // Verify the result
        assertEquals(courseDTO, result.getObject());
    }
}