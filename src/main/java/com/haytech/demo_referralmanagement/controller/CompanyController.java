package com.haytech.demo_referralmanagement.controller;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.entity.Company;
import com.haytech.demo_referralmanagement.model.entity.Course;
import com.haytech.demo_referralmanagement.service.intrface.CompanyCourseService;
import com.haytech.demo_referralmanagement.service.intrface.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api")
@RestController
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyCourseService companyCourseService;

    @PostMapping(value = "/v1/company")
    public ResponseEntity<?> createCompany(@RequestBody Company company) {
        BaseDTO baseDTO = companyService.createCompany(company);
        return new ResponseEntity<>(baseDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "v1/companies")
    public ResponseEntity<?> getAllCompanies() {

        BaseDTO baseDTO = companyService.getAllCompanies();
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/v1/company")
    public ResponseEntity<?> getCompany(@RequestParam Long companyId) {
        BaseDTO baseDTO = companyService.getCompany(companyId);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/v1/company")
    public ResponseEntity<?> updateCompany(@RequestParam Long companyId, @RequestBody Company company) {
        BaseDTO baseDTO = companyService.updateCompany(companyId, company);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/v1/company/{companyId}")
    public ResponseEntity<?> deleteCompany(@PathVariable Long companyId) {
        BaseDTO baseDTO = companyService.deleteCompany(companyId);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);

    }

    @PostMapping(value = "/v1/course_company")
    public ResponseEntity<?> addCourseToCompany(@RequestParam Long companyId, @RequestBody Course course) {
        BaseDTO baseDTO = companyCourseService.addCourseToCompany(companyId, course);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);

    }

    @GetMapping(value = "/v1/company", params = "/{companyId}/courses")
    public ResponseEntity<?> getCoursesByCompany(@PathVariable Long companyId) {
        BaseDTO baseDTO = companyCourseService.getCoursesByCompany(companyId);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/v1/company/{companyId}/courses/{courseId}")
    public ResponseEntity<?> removeCourseFromCompany(@PathVariable Long companyId, @PathVariable Long courseId) {
        BaseDTO baseDTO = companyCourseService.removeCourseFromCompany(companyId, courseId);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

}
