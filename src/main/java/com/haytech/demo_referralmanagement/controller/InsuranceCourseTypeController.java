package com.haytech.demo_referralmanagement.controller;


import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.dto.InsuranceCourseTypeDTO;
import com.haytech.demo_referralmanagement.model.entity.InsuranceCourseType;
import com.haytech.demo_referralmanagement.service.intrface.InsuranceCourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class InsuranceCourseTypeController {
    @Autowired
    private InsuranceCourseTypeService insuranceCourseTypeService;

    @GetMapping(value = "/v1/insurance_courses_type")
    public  ResponseEntity<?> getAllCourses() {

        BaseDTO baseDTO =  insuranceCourseTypeService.getAllInsuranceCoursesType();
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/v1/insurance_courses_type_by_company_id")
    public  ResponseEntity<?> getAllInsuranceCoursesTypeByCompanyId(@RequestParam Long companyId) {

        BaseDTO baseDTO =  insuranceCourseTypeService.getAllInsuranceCourseTypeByCompanyId(companyId);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/v1/insurance_courses_type_by_core_ict_id")
    public  ResponseEntity<?> getAllInsuranceCoursesTypeByCoreIctId(@RequestParam Long coreIctId) {

        BaseDTO baseDTO =  insuranceCourseTypeService.getAllInsuranceCourseTypeByCoreIctId(coreIctId);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/v1/insurance_courses_type_by_core_id_and_company_id")
    public  ResponseEntity<?> getAllInsuranceCoursesTypeByCoreIdAndCompanyId(@RequestParam Long coreIctId ,@RequestParam Long companyId) {

        BaseDTO baseDTO =  insuranceCourseTypeService.getAllInsuranceCoursesTypeByCoreIdAndCompanyId(coreIctId,companyId);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/v1/insurance_course_type/{courseId}")
    public  ResponseEntity<?> getInsuranceCourseType(@PathVariable Long courseId) {

        BaseDTO baseDTO = insuranceCourseTypeService.getInsuranceCourseType(courseId);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }
    @PostMapping(value = "/v1/insurance_course_type")
    public ResponseEntity<?> createInsuranceCourseType(@RequestParam Long companyId,@RequestParam Long coreIctId,@RequestBody InsuranceCourseTypeDTO insuranceCourseType) {
        BaseDTO baseDTO =  insuranceCourseTypeService.createInsuranceCourseType(companyId,coreIctId,insuranceCourseType);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }
    @PutMapping(value = "/v1/insurance_course_type/{courseId}")
    public  ResponseEntity<?> updateInsuranceCourseType(@PathVariable Long courseId, @RequestBody InsuranceCourseTypeDTO insuranceCourseType) {
        BaseDTO baseDTO = insuranceCourseTypeService.updateInsuranceCourseType(courseId, insuranceCourseType);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/v1/insurance_course_type/{courseId}")
    public  ResponseEntity<?> deleteInsuranceCourseType(@PathVariable Long courseId) {

        BaseDTO baseDTO = insuranceCourseTypeService.deleteInsuranceCourseType(courseId);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);

    }
}