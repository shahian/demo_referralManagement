package com.haytech.demo_referralmanagement.controller;


import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.base.MetaDTO;
import com.haytech.demo_referralmanagement.model.dto.AgencyDTO;
import com.haytech.demo_referralmanagement.service.intrface.AgencyCheckingService;
import com.haytech.demo_referralmanagement.service.intrface.AgencyService;
import com.haytech.demo_referralmanagement.utility.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AgencyController {

    private final AgencyService agencyService;

    public AgencyController(AgencyService agencyService) {
        this.agencyService = agencyService;
    }

    @GetMapping(value = "/v1/agencies")
    public ResponseEntity<?> getAllAgencies() {
        BaseDTO baseDTO = agencyService.getAllAgencies();
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/v1/agencies/{id}")
    public ResponseEntity<?> getAgencyById(@PathVariable Long id) {
        BaseDTO baseDTO = agencyService.getAgencyById(id);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/v1/agency")
    public ResponseEntity<?> createAgency(@RequestBody AgencyDTO agencyDTO) {
        BaseDTO baseDTO  = agencyService.createAgency(agencyDTO);
        return new ResponseEntity<>(baseDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/v1/agency/{id}")
    public ResponseEntity<?> updateAgency(@PathVariable Long id, @RequestBody AgencyDTO agencyDTO) {
        BaseDTO baseDTO  = agencyService.updateAgency(id, agencyDTO);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/v1/agency/{id}")
    public ResponseEntity<?> deleteAgency(@PathVariable Long id) {
        BaseDTO baseDTO = agencyService.deleteAgency(id);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }
}