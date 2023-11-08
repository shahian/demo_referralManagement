package com.haytech.demo_referralmanagement.controller;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.dto.CompanyDTO;
import com.haytech.demo_referralmanagement.model.entity.Company;
import com.haytech.demo_referralmanagement.model.entity.InsuranceCourseType;
import com.haytech.demo_referralmanagement.service.intrface.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api")
@RestController
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping(value = "/v1/company")
    public ResponseEntity<?> createCompany(@RequestBody CompanyDTO company) {
        BaseDTO baseDTO = companyService.createCompany(company);
        return new ResponseEntity<>(baseDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "v1/companies")
    public ResponseEntity<?> getAllCompanies(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        BaseDTO baseDTO = companyService.getAllCompanies(page,size);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/v1/company")
    public ResponseEntity<?> getCompany(@RequestParam Long companyId) {
        BaseDTO baseDTO = companyService.getCompany(companyId);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/v1/company")
    public ResponseEntity<?> updateCompany(@RequestParam Long companyId, @RequestBody CompanyDTO company) {
        BaseDTO baseDTO = companyService.updateCompany(companyId, company);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/v1/company/{companyId}")
    public ResponseEntity<?> deleteCompany(@PathVariable Long companyId) {
        BaseDTO baseDTO = companyService.deleteCompany(companyId);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);

    }

}
