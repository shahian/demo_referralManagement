package com.haytech.demo_referralmanagement.controller;

import com.haytech.demo_referralmanagement.model.entity.Company;
import com.haytech.demo_referralmanagement.model.entity.Course;
import com.haytech.demo_referralmanagement.service.intrface.CompanyCourseService;
import com.haytech.demo_referralmanagement.service.intrface.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyCourseService companyCourseService;

    @PostMapping(value = "/v1/company")
    public Company createCompany(@RequestBody Company company) {
        return companyService.createCompany(company);
    }

    @GetMapping(value ="v1/companies")
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping(value = "/v1/company")
    public Company getCompany(@RequestParam Long companyId) {
        return companyService.getCompany(companyId);
    }

    @PutMapping(value = "/v1/company",params ="/{companyId}")
    public Company updateCompany(@PathVariable Long companyId, @RequestBody Company company) {
        return companyService.updateCompany(companyId, company);
    }

    @DeleteMapping(value = "/v1/company",params ="/{companyId}")
    public void deleteCompany(@PathVariable Long companyId) {
        companyService.deleteCompany(companyId);
    }

    @PostMapping(value = "/v1/course_company")
    public void addCourseToCompany(@RequestParam Long companyId, @RequestBody Course course) {
        companyCourseService.addCourseToCompany(companyId, course);
    }

    @GetMapping(value = "/v1/company",params ="/{companyId}/courses")
    public List<Course> getCoursesByCompany(@PathVariable Long companyId) {
        return companyCourseService.getCoursesByCompany(companyId);
    }

    @DeleteMapping(value = "/v1/company",params ="/{companyId}/courses/{courseId}")
    public void removeCourseFromCompany(@PathVariable Long companyId, @PathVariable Long courseId) {
        companyCourseService.removeCourseFromCompany(companyId, courseId);
    }

}
