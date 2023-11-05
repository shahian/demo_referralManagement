package com.haytech.demo_referralmanagement.controller;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.entity.CoreInsuranceCourseType;
import com.haytech.demo_referralmanagement.service.intrface.CoreInsuranceCourseTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CoreInsuranceCourseController {

    private final CoreInsuranceCourseTypeService coreInsuranceCourseTypeService;

    public CoreInsuranceCourseController(CoreInsuranceCourseTypeService coreInsuranceCourseTypeService) {
        this.coreInsuranceCourseTypeService = coreInsuranceCourseTypeService;
    }

    @GetMapping(value = "/v1/core_insurance_course")
    public ResponseEntity<?> getCoreInsuranceCourseById(@RequestParam Long coreInsuranceCourseId){
        BaseDTO baseDTO= coreInsuranceCourseTypeService.findById(coreInsuranceCourseId);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/v1/core_insurance_courses")
    public ResponseEntity<?> getCoreInsuranceCourses(){
        BaseDTO baseDTO= coreInsuranceCourseTypeService.findAll();
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }
    @PostMapping(value = "/v1/core_insurance_course")
    public ResponseEntity<?> createCoreInsuranceCourseType(@RequestBody CoreInsuranceCourseType coreInsuranceCourseType){
        BaseDTO baseDTO= coreInsuranceCourseTypeService.createCoreInsuranceCourseType(coreInsuranceCourseType);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }
    @PutMapping(value = "/v1/core_insurance_course")
    public ResponseEntity<?> updateCoreInsuranceCourseType(@RequestParam Long coreInsuranceCourseId, @RequestBody CoreInsuranceCourseType coreInsuranceCourseType){
        BaseDTO baseDTO= coreInsuranceCourseTypeService.updateCoreInsuranceCourseType(coreInsuranceCourseId,coreInsuranceCourseType);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }
    @DeleteMapping(value = "/v1/core_insurance_course")
    public ResponseEntity<?> deleteCoreInsuranceCourseType(@RequestParam Long coreInsuranceCourseId){
        BaseDTO baseDTO= coreInsuranceCourseTypeService.deleteCoreInsuranceCourseType(coreInsuranceCourseId );
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }
}
