package com.haytech.demo_referralmanagement.controller;


import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.dto.AgencyDTO;
import com.haytech.demo_referralmanagement.model.dto.FanavaranPolicyDTO;
import com.haytech.demo_referralmanagement.service.intrface.AgencyService;
import com.haytech.demo_referralmanagement.service.intrface.FanavaranPolicyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FanavaranPolicyController {

    private final FanavaranPolicyService fanavaranPolicyService;

    public FanavaranPolicyController(FanavaranPolicyService fanavaranPolicyService) {
        this.fanavaranPolicyService = fanavaranPolicyService;
    }

    @GetMapping(value = "/v1/fanavaran-policies")
    public ResponseEntity<?> getAllFanavaranPolicies() {
        BaseDTO baseDTO = fanavaranPolicyService.getAllFanavaranPolicies();
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/v1/fanavaran-policy/{id}")
    public ResponseEntity<?> getFanavaranPolicyById(@PathVariable Long id) {
        BaseDTO baseDTO = fanavaranPolicyService.getFanavaranPolicyById(id);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/v1/fanavaran-policy")
    public ResponseEntity<?> createFanavaranPolicy(@RequestBody FanavaranPolicyDTO fanavaranPolicyDTO) {
        BaseDTO baseDTO  = fanavaranPolicyService.createFanavaranPolicy(fanavaranPolicyDTO);
        return new ResponseEntity<>(baseDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/v1/fanavaran-policy/{id}")
    public ResponseEntity<?> updateFanavaranPolicy(@PathVariable Long id, @RequestBody FanavaranPolicyDTO fanavaranPolicyDTO) {
        BaseDTO baseDTO  = fanavaranPolicyService.updateFanavaranPolicy(id, fanavaranPolicyDTO);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/v1/fanavaran-policy/{id}")
    public ResponseEntity<?> deleteFanavaranPolicy(@PathVariable Long id) {
        BaseDTO baseDTO = fanavaranPolicyService.deleteFanavaranPolicy(id);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }
}