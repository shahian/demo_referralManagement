package com.haytech.demo_referralmanagement.controller;

import com.haytech.demo_referralmanagement.model.entity.Course;
import com.haytech.demo_referralmanagement.service.intrface.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;


public class CourseControllerTest {
    @InjectMocks
    private CourseController courseController;

    @Mock
    private CourseService courseService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
    }

    @Test
    public void testCreateCourse() throws Exception {
        Course course = new Course();
        course.setName("Test Course");
        course.setCode("TEST123");

        Mockito.when(courseService.createCourse(Mockito.any(Course.class))).thenReturn(course);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/course")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Test Course\", \"code\": \"TEST123\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetAllCourses() throws Exception {
        List<Course> courses = new ArrayList<>();
        Course course1 = new Course();
        course1.setName("Course 1");
        course1.setCode("CODE1");

        Course course2 = new Course();
        course2.setName("Course 2");
        course2.setCode("CODE2");

        courses.add(course1);
        courses.add(course2);

        Mockito.when(courseService.getAllCourses()).thenReturn(courses);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/courses"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Course 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("CODE1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Course 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].code").value("CODE2"));
    }

    @Test
    public void testGetCourse() throws Exception {
        Course course = new Course();
        course.setName("Test Course");
        course.setCode("TEST123");

        Mockito.when(courseService.getCourse(Mockito.anyLong())).thenReturn(course);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/course/{courseId}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test Course"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("TEST123"));
    }

    @Test
    public void testUpdateCourse() throws Exception {
        Course course = new Course();
        course.setName("Test Course");
        course.setCode("TEST123");

        Mockito.when(courseService.updateCourse(Mockito.anyLong(), Mockito.any(Course.class))).thenReturn(course);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/course/{courseId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Updated Course\", \"code\": \"NEWCODE\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated Course"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("NEWCODE"));
    }

    @Test
    public void testDeleteCourse() throws Exception {
        Mockito.doNothing().when(courseService).deleteCourse(Mockito.anyLong());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/course/{courseId}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}