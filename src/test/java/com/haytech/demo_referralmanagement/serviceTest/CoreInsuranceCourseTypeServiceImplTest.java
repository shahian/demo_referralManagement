package com.haytech.demo_referralmanagement.serviceTest;


import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.base.MetaDTO;
import com.haytech.demo_referralmanagement.model.dto.CoreInsuranceCourseTypeDTO;
import com.haytech.demo_referralmanagement.model.entity.CoreInsuranceCourseType;
import com.haytech.demo_referralmanagement.model.mapper.CoreInsuranceCourseTypeMapper;
import com.haytech.demo_referralmanagement.repository.CoreInsuranceCourseTypeRepository;
import com.haytech.demo_referralmanagement.service.impl.CoreInsuranceCourseTypeServiceImpl;
import com.haytech.demo_referralmanagement.utility.ApplicationProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CoreInsuranceCourseTypeServiceImplTest {

    @InjectMocks
    private CoreInsuranceCourseTypeServiceImpl coreInsuranceCourseTypeService;

    @Mock
    private ApplicationProperties applicationProperties;

    @Mock
    private CoreInsuranceCourseTypeMapper coreInsuranceCourseTypeMapper;

    @Mock
    private CoreInsuranceCourseTypeRepository coreInsuranceCourseTypeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        // Create a CoreInsuranceCourseType object, expected BaseDTO, and configure mocks
        CoreInsuranceCourseType coreInsuranceCourseType = new CoreInsuranceCourseType();
        coreInsuranceCourseType.setId(1L);
        CoreInsuranceCourseTypeDTO expectedBaseDTO = new CoreInsuranceCourseTypeDTO();

        when(coreInsuranceCourseTypeRepository.findById(1L)).thenReturn(java.util.Optional.of(coreInsuranceCourseType));
        when(coreInsuranceCourseTypeMapper.DTO_CoreInsuranceCourseType(coreInsuranceCourseType)).thenReturn(expectedBaseDTO);

        // Call the service method
        BaseDTO result = coreInsuranceCourseTypeService.findById(1L);

        // Verify the result
        assertNotNull(result);
        assertEquals(expectedBaseDTO, result);
    }

    @Test
    public void testFindById_ThrowsNotFoundException() {
        // Configure the mock to throw a NotFoundException
        when(coreInsuranceCourseTypeRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        // Call the service method and expect an exception
        assertThrows(NotFoundException.class, () -> coreInsuranceCourseTypeService.findById(1L));
    }

    @Test
    public void testFindAll() {
        // Create a list of CoreInsuranceCourseType objects and an expected BaseDTO
        CoreInsuranceCourseType coreInsuranceCourseType1 = new CoreInsuranceCourseType();
        CoreInsuranceCourseType coreInsuranceCourseType2 = new CoreInsuranceCourseType();
        coreInsuranceCourseType1.setId(1L);
        coreInsuranceCourseType2.setId(2L);
        List<CoreInsuranceCourseType> coreInsuranceCourseTypes = Arrays.asList(coreInsuranceCourseType1, coreInsuranceCourseType2);
        List<CoreInsuranceCourseTypeDTO> expectedBaseDTO = new ArrayList<>();

        when(coreInsuranceCourseTypeRepository.findAll()).thenReturn(coreInsuranceCourseTypes);
        when(coreInsuranceCourseTypeMapper.DTO_LIST(coreInsuranceCourseTypes)).thenReturn(expectedBaseDTO);

        // Call the service method
        BaseDTO result = coreInsuranceCourseTypeService.findAll();

        // Verify the result
        assertNotNull(result);
        assertEquals(expectedBaseDTO, result);
    }

    @Test
    public void testCreateCoreInsuranceCourseType() {
        // Create a CoreInsuranceCourseType object, expected BaseDTO, and configure mocks
        CoreInsuranceCourseType coreInsuranceCourseType = new CoreInsuranceCourseType();
        CoreInsuranceCourseTypeDTO expectedBaseDTO = new CoreInsuranceCourseTypeDTO();
        Mockito.when(coreInsuranceCourseTypeRepository.save(coreInsuranceCourseType)).thenReturn(coreInsuranceCourseType);
        Mockito.when(coreInsuranceCourseTypeMapper.DTO_CoreInsuranceCourseType(coreInsuranceCourseType)).thenReturn(expectedBaseDTO);
        // Call the service method
        BaseDTO result = coreInsuranceCourseTypeService.createCoreInsuranceCourseType(expectedBaseDTO);
        // Verify the result
        //assertNotNull(result.getObject());
        assertEquals(expectedBaseDTO, result.getObject());
    }

    @Test
    public void testUpdateCoreInsuranceCourseType() {
        // Create a CoreInsuranceCourseType object, expected BaseDTO, and configure mocks
        CoreInsuranceCourseType coreInsuranceCourseType = new CoreInsuranceCourseType();
        coreInsuranceCourseType.setId(1L);
        coreInsuranceCourseType.setName("Test Course");
        CoreInsuranceCourseTypeDTO expectedBaseDTO = new CoreInsuranceCourseTypeDTO();


        when(coreInsuranceCourseTypeRepository.findById(1L)).thenReturn(java.util.Optional.of(coreInsuranceCourseType));
        when(coreInsuranceCourseTypeRepository.save(coreInsuranceCourseType)).thenReturn(coreInsuranceCourseType);
        when(coreInsuranceCourseTypeMapper.DTO_CoreInsuranceCourseType(coreInsuranceCourseType)).thenReturn(expectedBaseDTO);

        // Call the service method
        BaseDTO result = coreInsuranceCourseTypeService.updateCoreInsuranceCourseType(1L, expectedBaseDTO);

        // Verify the result
        assertNotNull(result);
        assertEquals(expectedBaseDTO, result);
    }

    @Test
    public void testUpdateCoreInsuranceCourseType_ThrowsNotFoundException() {
        // Configure the mock to throw a NotFoundException
        when(coreInsuranceCourseTypeRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        // Call the service method and expect an exception
        assertThrows(NotFoundException.class, () -> coreInsuranceCourseTypeService.updateCoreInsuranceCourseType(1L, new CoreInsuranceCourseTypeDTO()));
    }

    @Test
    public void testDeleteCoreInsuranceCourseType() {
        // Create a CoreInsuranceCourseType object and expected BaseDTO, and configure mocks
        CoreInsuranceCourseType coreInsuranceCourseType = new CoreInsuranceCourseType();
        coreInsuranceCourseType.setId(1L);

        CoreInsuranceCourseTypeDTO expectedBaseDTO = new CoreInsuranceCourseTypeDTO();
        when(coreInsuranceCourseTypeRepository.findById(1L)).thenReturn(java.util.Optional.of(coreInsuranceCourseType));
        when(coreInsuranceCourseTypeRepository.save(coreInsuranceCourseType)).thenReturn(coreInsuranceCourseType);
        when(coreInsuranceCourseTypeMapper.DTO_CoreInsuranceCourseType(coreInsuranceCourseType)).thenReturn(expectedBaseDTO);

        // Call the service method
        BaseDTO result = coreInsuranceCourseTypeService.deleteCoreInsuranceCourseType(1L);

        // Verify the result
        assertNotNull(result);
        assertEquals(expectedBaseDTO, result);
    }

    @Test
    public void testDeleteCoreInsuranceCourseType_ThrowsNotFoundException() {
        // Configure the mock to throw a NotFoundException
        when(coreInsuranceCourseTypeRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        // Call the service method and expect an exception
        assertThrows(NotFoundException.class, () -> coreInsuranceCourseTypeService.deleteCoreInsuranceCourseType(1L));
    }
}