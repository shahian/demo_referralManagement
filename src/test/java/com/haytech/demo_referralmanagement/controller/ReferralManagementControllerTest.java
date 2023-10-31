package com.haytech.demo_referralmanagement.controller;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.base.MetaDTO;
import com.haytech.demo_referralmanagement.model.entity.ReferralManagement;
import com.haytech.demo_referralmanagement.model.enums.ReferrType;
import com.haytech.demo_referralmanagement.service.intrface.ReferralManagementService;
import com.haytech.demo_referralmanagement.utility.ApplicationProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class ReferralManagementControllerTest {
    @InjectMocks
    private ReferralManagementController referralManagementController;

    @Mock
    private ReferralManagementService referralManagementService;

    @Mock
    private ApplicationProperties applicationProperties;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllReferralManagements() {
        List<ReferralManagement> referralManagements = new ArrayList<>();
        referralManagements.add(new ReferralManagement());
        referralManagements.add(new ReferralManagement());

        // Mock the behavior of the service to return the list of referral managements
        Mockito.when(referralManagementService.getAll()).thenReturn((BaseDTO) referralManagements);

        // Mock the behavior of the applicationProperties
        Mockito.when(applicationProperties.getProperty("your_property")).thenReturn("your_value");

        ResponseEntity<?> response = referralManagementController.getAll();

        // Assert that the response status is HttpStatus.OK
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        // You can also perform more specific assertions on the response content if needed.
    }

    @Test
    public void testSearchReferralManagement() {
        // Define query parameters (you can customize these)
        Long personnelId = 1L;
        String insuranceNumber = "12345";
        String nationalCode = "ABC123";
        boolean processed = true;
        ReferrType referrType = ReferrType.COMPLETE_COVERAGE;

        // Mock the behavior of the service to return a BaseDTO
        BaseDTO expectedBaseDTO = new BaseDTO(MetaDTO.getInstance(applicationProperties), new ArrayList<>());
        Mockito.when(referralManagementService.filterReferralManagementCriteria(personnelId, insuranceNumber, nationalCode, processed, referrType))
                .thenReturn(expectedBaseDTO);

        // Mock the behavior of the applicationProperties
        Mockito.when(applicationProperties.getProperty("your_property")).thenReturn("your_value");

        ResponseEntity<?> response = referralManagementController.searchReferralManagementCriteria(
                personnelId, insuranceNumber, nationalCode, processed, referrType);

        // Assert that the response status is HttpStatus.OK
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        // You can also perform more specific assertions on the response content if needed.
    }
}