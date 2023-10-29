package com.haytech.demo_referralmanagement.controller;


import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.base.MetaDTO;
import com.haytech.demo_referralmanagement.model.enums.ReferrType;
import com.haytech.demo_referralmanagement.service.intrface.ReferralManagementService;
import com.haytech.demo_referralmanagement.utility.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class ReferralManagementController {
    @Autowired
    private ReferralManagementService referralManagementService;
    @Autowired
    private ApplicationProperties applicationProperties;


    @GetMapping(value = "/v1/ReferralManagements")
    public ResponseEntity<?> getAll() {
        BaseDTO baseDTO = new BaseDTO(MetaDTO.getInstance(applicationProperties), referralManagementService.getAll());
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @GetMapping("/v1/filter_query")
    public ResponseEntity<?> searchReferralManagementQuery(
            @RequestParam(value = "personnelId", required = false) Long personnelId,
            @RequestParam(value = "insuranceNumber", required = false) String insuranceNumber,
            @RequestParam(value = "nationalCode", required = false) String nationalCode,
            @RequestParam(value = "processed", required = false, defaultValue = "true") boolean processed,
            @RequestParam(value = "referrType", required = false) ReferrType referrType) {

        BaseDTO baseDTO = referralManagementService.filterReferralManagementQuery(
                personnelId, insuranceNumber, nationalCode, processed, referrType);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }
    @GetMapping("/v1/filter_criteria")
    public ResponseEntity<?> searchReferralManagementCriteria(
            @RequestParam(value = "personnelId", required = false) Long personnelId,
            @RequestParam(value = "insuranceNumber", required = false) String insuranceNumber,
            @RequestParam(value = "nationalCode", required = false) String nationalCode,
            @RequestParam(value = "processed", required = false, defaultValue = "true") boolean processed,
            @RequestParam(value = "referrType", required = false) ReferrType referrType) {

        BaseDTO baseDTO = referralManagementService.filterReferralManagementCriteria(
                personnelId, insuranceNumber, nationalCode, processed, referrType);

        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }
}
