package com.haytech.demo_referralmanagement.controller;


import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.base.MetaDTO;
import com.haytech.demo_referralmanagement.model.dto.AgencyCheckingDTO;
import com.haytech.demo_referralmanagement.model.request.AgencyCheckingRequest;
import com.haytech.demo_referralmanagement.service.intrface.AgencyCheckingService;
import com.haytech.demo_referralmanagement.utility.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class AgencyCheckingController {
    @Autowired
    private AgencyCheckingService agencyCheckingService;
    @Autowired
    private ApplicationProperties applicationProperties;


    @GetMapping(value = "/v1/agency_checkings")
    public ResponseEntity<?> getAll() {
        BaseDTO baseDTO = new BaseDTO(MetaDTO.getInstance(applicationProperties), agencyCheckingService.getAll());
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/v1/agency_checking_filter_query")
    public ResponseEntity<?> searchAgencyCheckingQuery(
            @RequestParam(value = "personnelId", required = false) Long personnelId,
            @RequestParam(value = "insuranceNumber", required = false) String insuranceNumber,
            @RequestParam(value = "nationalCode", required = false) String nationalCode,
            @RequestParam(value = "isDone", required = false, defaultValue = "true") boolean isDone,
            @RequestParam(value = "checkingTypeName", required = false) String checkingTypeName) {

        BaseDTO baseDTO = agencyCheckingService.filterAgencyCheckingQuery(
                personnelId, insuranceNumber, nationalCode, isDone, checkingTypeName);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/v1/agency_checking_filter_criteria")
    public ResponseEntity<?> searchAgencyCheckingCriteria(
            @RequestParam(value = "personnelId", required = false) Long personnelId,
            @RequestParam(value = "insuranceNumber", required = false) String insuranceNumber,
            @RequestParam(value = "nationalCode", required = false) String nationalCode,
            @RequestParam(value = "isDone", required = false, defaultValue = "true") boolean isDone,
            @RequestParam(value = "checkingTypeName", required = false) String checkingTypeName) {

        BaseDTO baseDTO = agencyCheckingService.filterAgencyCheckingCriteria(
                personnelId, insuranceNumber, nationalCode, isDone, checkingTypeName);

        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/v1/agency_checking/{id}")
    public ResponseEntity<?> getAgencyCheckingById(@PathVariable Long id) {
        BaseDTO baseDTO = agencyCheckingService.getAgencyCheckingById(id);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/v1/agency_checking")
    public ResponseEntity<?> createAgencyChecking(@RequestBody AgencyCheckingRequest agencyCheckingRequest) {
        BaseDTO baseDTO  = agencyCheckingService.createAgencyChecking(agencyCheckingRequest);
        return new ResponseEntity<>(baseDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/v1/agency_checking/{id}")
    public ResponseEntity<?> updateAgencyChecking(@PathVariable Long id, @RequestBody AgencyCheckingDTO agencyCheckingDTO) {
        BaseDTO baseDTO  = agencyCheckingService.updateAgencyChecking(id, agencyCheckingDTO);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/v1/agency_checking/{id}")
    public ResponseEntity<?> deleteAgencyChecking(@PathVariable Long id) {
        BaseDTO baseDTO = agencyCheckingService.deleteAgencyChecking(id);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }
}
