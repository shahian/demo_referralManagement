package com.haytech.demo_referralmanagement.service;

import com.haytech.demo_referralmanagement.model.entity.Company;
import com.haytech.demo_referralmanagement.model.entity.Course;
import com.haytech.demo_referralmanagement.repository.CourseRepository;
import com.haytech.demo_referralmanagement.service.impl.CourseServiceImpl;
import com.haytech.demo_referralmanagement.utility.ApplicationProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceImplTest {
    @Mock
    private CourseRepository courseRepository;
    @InjectMocks
    private CourseServiceImpl courseService;
    @Mock
    private ApplicationProperties applicationProperties;
    private Course course;

    @BeforeEach
    public void setup() {
        course = new Course();
        course.setId(1L);
        course.setName("New Course");
        course.setCode("ABC123");
    }

    @Test
    public void testCreateCourseWhenValidCourseThenCourseSaved() {
        when(courseRepository.save(any(Course.class))).thenReturn(course);
        Course savedCourse = courseService.createCourse(course);
        assertEquals(course, savedCourse);
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    public void testUpdateCourseWhenValidCourseAndIdThenCourseUpdated() {
        Course updatedCourse = new Course();
        updatedCourse.setName("Updated Course");
        updatedCourse.setCode("UC");
        updatedCourse.setId(1L);

        // Use doReturn().when() to stub the findById method
        doReturn(Optional.of(course)).when(courseRepository).findById(1L);

        // Use doReturn().when() to stub the save method
        doReturn(updatedCourse).when(courseRepository).save(eq(updatedCourse));

        Course resultCourse = courseService.updateCourse(1L, updatedCourse);

        assertNotNull(resultCourse);
        assertEquals("Updated Course", resultCourse.getName());
        assertEquals("UC", resultCourse.getCode());

        // Verify method invocations
        verify(courseRepository, times(1)).findById(1L);
        verify(courseRepository, times(1)).save(updatedCourse);


//        when(courseRepository.findById(anyLong())).thenReturn(Optional.of(course));
//        when(courseRepository.save(any(Course.class))).thenReturn(updatedCourse);
//
//        Course resultCourse = courseService.updateCourse(1L, updatedCourse);
//        assertEquals(updatedCourse, resultCourse);
//        verify(courseRepository, times(1)).findById(1L);
//        verify(courseRepository, times(1)).save(updatedCourse);
    }
    @Test
    public void updateTest(){
        Course updatedCourse = new Course();
        updatedCourse.setName("Updated Course");
        updatedCourse.setCode("UC");
        doReturn(Optional.of(course)).when(this.courseRepository).findById(isA(Long.class));
        doAnswer(AdditionalAnswers.returnsFirstArg()).when(this.courseRepository).saveAndFlush(isA(Course.class));

    }

    @Test
    public void testDeleteCourseWhenValidIdThenCompanyDeleted() {
        doNothing().when(courseRepository).deleteById(anyLong());
        courseService.deleteCourse(1L);
        verify(courseRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetCourseWhenValidIdThenCourseReturned(){
        long courseId=1L;
        when(courseRepository.getById(courseId)).thenReturn(course);
        Course resultCourse=courseService.getCourse(1L);
        assertEquals(course,resultCourse);
        verify(courseRepository,times(1)).getById(1L);
    }

    @Test
    public void testGetAllCompaniesThenAllCompaniesReturned() {
        List<Course> courses = Arrays.asList(course);

        when(courseRepository.findAll()).thenReturn(courses);
        when(courseRepository.findAll()).thenReturn(courses);

        List<Course> resultCompanies = courseService.getAllCourses();
        List<Course> resultCompanies1 = courseService.getAllCourses();

        assertEquals(courses, resultCompanies);
        verify(courseRepository, times(2)).findAll();
    }
}
