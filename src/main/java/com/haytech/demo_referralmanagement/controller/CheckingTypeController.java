package com.haytech.demo_referralmanagement.controller;


import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.dto.AgencyDTO;
import com.haytech.demo_referralmanagement.model.dto.CheckingTypeDTO;
import com.haytech.demo_referralmanagement.service.intrface.AgencyService;
import com.haytech.demo_referralmanagement.service.intrface.CheckingTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CheckingTypeController {

    private final CheckingTypeService checkingTypeService;

    public CheckingTypeController(CheckingTypeService checkingTypeService) {
        this.checkingTypeService = checkingTypeService;
    }

    @GetMapping(value = "/v1/checking-types")
    public ResponseEntity<?> getAllCheckingTypes() {
        BaseDTO baseDTO = checkingTypeService.getAllCheckingTypes();
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/v1/checking-types/{id}")
    public ResponseEntity<?> getCheckingTypeById(@PathVariable Long id) {
        BaseDTO baseDTO = checkingTypeService.getCheckingTypeById(id);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/v1/checking-type")
    public ResponseEntity<?> createCheckingType(@RequestBody CheckingTypeDTO checkingTypeDTO) {
        BaseDTO baseDTO = checkingTypeService.createCheckingType(checkingTypeDTO);
        return new ResponseEntity<>(baseDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/v1/checking-type/{id}")
    public ResponseEntity<?> updateCheckingType(@PathVariable Long id, @RequestBody CheckingTypeDTO checkingTypeDTO) {
        BaseDTO baseDTO = checkingTypeService.updateCheckingType(id, checkingTypeDTO);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/v1/checking-type/{id}")
    public ResponseEntity<?> deleteCheckingType(@PathVariable Long id) {
        BaseDTO baseDTO = checkingTypeService.deleteCheckingType(id);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }
}