package com.haytech.demo_referralmanagement.controllerTest;
import com.haytech.demo_referralmanagement.controller.CoreInsuranceCourseController;
import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.entity.CoreInsuranceCourseType;
import com.haytech.demo_referralmanagement.service.intrface.CoreInsuranceCourseTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CoreInsuranceCourseControllerTest {

    @InjectMocks
    private CoreInsuranceCourseController coreInsuranceCourseController;

    @Mock
    private CoreInsuranceCourseTypeService coreInsuranceCourseTypeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCoreInsuranceCourseById() {
        // Create a CoreInsuranceCourseType object and expected BaseDTO
        CoreInsuranceCourseType coreInsuranceCourseType = new CoreInsuranceCourseType();
        coreInsuranceCourseType.setId(1L);
        BaseDTO expectedBaseDTO = new BaseDTO();

        // Mock the service method
        when(coreInsuranceCourseTypeService.findById(1L)).thenReturn(expectedBaseDTO);

        // Call the controller method
        ResponseEntity<?> response = coreInsuranceCourseController.getCoreInsuranceCourseById(1L);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedBaseDTO, response.getBody());
    }

    // Similar tests for other controller methods: getCoreInsuranceCourses, createCoreInsuranceCourseType, updateCoreInsuranceCourseType, and deleteCoreInsuranceCourseType

    @Test
    public void testGetCoreInsuranceCourses() {
        // Create your test for the getCoreInsuranceCourses method here
    }

    @Test
    public void testCreateCoreInsuranceCourseType() {
        // Create your test for the createCoreInsuranceCourseType method here
    }

    @Test
    public void testUpdateCoreInsuranceCourseType() {
        // Create your test for the updateCoreInsuranceCourseType method here
    }

    @Test
    public void testDeleteCoreInsuranceCourseType() {
        // Create your test for the deleteCoreInsuranceCourseType method here
    }
}