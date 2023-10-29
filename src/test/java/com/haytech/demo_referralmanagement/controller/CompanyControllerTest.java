package com.haytech.demo_referralmanagement.controller;

import com.haytech.demo_referralmanagement.model.entity.Company;
import com.haytech.demo_referralmanagement.model.entity.Course;
import com.haytech.demo_referralmanagement.service.intrface.CompanyCourseService;
import com.haytech.demo_referralmanagement.service.intrface.CompanyService;
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

import java.util.*;

public class CompanyControllerTest {
    @InjectMocks
    private CompanyController companyController;

    @Mock
    private CompanyService companyService;

    @Mock
    private CompanyCourseService companyCourseService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(companyController).build();
    }

    @Test
    public void testCreateCompany() throws Exception {
        Company company = new Company();
        company.setName("Test Company");
        company.setCode("COMP123");

        Mockito.when(companyService.createCompany(Mockito.any(Company.class))).thenReturn(company);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Test Company\", \"code\": \"COMP123\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetAllCompanies() throws Exception {
        List<Company> companies = new ArrayList<>();
        Company company1 = new Company();
        company1.setName("Company 1");
        company1.setCode("CODE1");

        Company company2 = new Company();
        company2.setName("Company 2");
        company2.setCode("CODE2");

        companies.add(company1);
        companies.add(company2);

        Mockito.when(companyService.getAllCompanies()).thenReturn(companies);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/companies"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Company 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("CODE1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Company 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].code").value("CODE2"));
    }

    @Test
    public void testGetCompany() throws Exception {
        Company company = new Company();
        company.setName("Test Company");
        company.setCode("COMP123");

        Mockito.when(companyService.getCompany(Mockito.anyLong())).thenReturn(company);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/company")
                        .param("companyId", "1")) // Set the companyId as a request parameter
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test Company"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("COMP123"));

    }

    @Test
    public void testUpdateCompany() throws Exception {
        Company company = new Company();
        company.setName("Test Company");
        company.setCode("COMP123");

        Mockito.when(companyService.updateCompany(Mockito.anyLong(), Mockito.any(Company.class))).thenReturn(company);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/company/{companyId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Updated Company\", \"code\": \"NEWCODE\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated Company"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("NEWCODE"));
    }

    @Test
    public void testDeleteCompany() throws Exception {
        Mockito.doNothing().when(companyService).deleteCompany(Mockito.anyLong());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/company/{companyId}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAddCourseToCompany() throws Exception {
        Course course = new Course();
        course.setName("Test Course");
        course.setCode("TEST123");

        Mockito.doNothing().when(companyCourseService).addCourseToCompany(Mockito.anyLong(), Mockito.any(Course.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/course_company/{companyId}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Test Course\", \"code\": \"TEST123\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetCoursesByCompany() throws Exception {
        List<Course> courses = new ArrayList<>();
        Course course1 = new Course();
        course1.setName("Course 1");
        course1.setCode("CODE1");

        Course course2 = new Course();
        course2.setName("Course 2");
        course2.setCode("CODE2");

        courses.add(course1);
        courses.add(course2);

        Mockito.when(companyCourseService.getCoursesByCompany(Mockito.anyLong())).thenReturn(courses);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/company/{companyId}/courses", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Course 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("CODE1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Course 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].code").value("CODE2"));
    }

    @Test
    public void testRemoveCourseFromCompany() throws Exception {
        Mockito.doNothing().when(companyCourseService).removeCourseFromCompany(Mockito.anyLong(), Mockito.anyLong());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/company/{companyId}/courses/{courseId}", 1, 2))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}