package com.haytech.demo_referralmanagement.serviceTest;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.dto.InsuranceCourseTypeDTO;
import com.haytech.demo_referralmanagement.model.entity.Company;
import com.haytech.demo_referralmanagement.model.entity.CoreInsuranceCourseType;
import com.haytech.demo_referralmanagement.model.entity.InsuranceCourseType;
import com.haytech.demo_referralmanagement.model.mapper.InsuranceCourseTypeMapper;
import com.haytech.demo_referralmanagement.repository.CompanyRepository;
import com.haytech.demo_referralmanagement.repository.CoreInsuranceCourseTypeRepository;
import com.haytech.demo_referralmanagement.repository.InsuranceCourseTypeRepository;
import com.haytech.demo_referralmanagement.service.impl.InsuranceCourseTypeServiceImpl;
import com.haytech.demo_referralmanagement.utility.ApplicationProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InsuranceCourseTypeServiceImplTest {

    @Mock
    private InsuranceCourseTypeRepository insuranceCourseTypeRepository;
    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private CoreInsuranceCourseTypeRepository coreInsuranceCourseTypeRepository;
    @Mock
    private ApplicationProperties applicationProperties;

    @Mock
    private InsuranceCourseTypeMapper insuranceCourseTypeMapper;

    @InjectMocks
    private InsuranceCourseTypeServiceImpl courseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCourse() {
        // Arrange
        Long companyId = 1L;
        Long coreIctId = 1L;
        Company company = new Company(); // Create a Company object for testing
        CoreInsuranceCourseType coreInsuranceCourseType = new CoreInsuranceCourseType(); // Create a Company object for testing
        InsuranceCourseType insuranceCourseType = new InsuranceCourseType(); // Create an InsuranceCourseType object for testing
        InsuranceCourseTypeDTO insuranceCourseTypeDTO = new InsuranceCourseTypeDTO(); // Create an expected DTO object

        // Mock behavior of your dependencies
        when(companyRepository.getById(companyId)).thenReturn(company);
        when(coreInsuranceCourseTypeRepository.getById(coreIctId)).thenReturn(coreInsuranceCourseType);
        when(insuranceCourseTypeRepository.save(insuranceCourseType)).thenReturn(insuranceCourseType);
        when(insuranceCourseTypeMapper.DTO_Course(insuranceCourseType)).thenReturn(insuranceCourseTypeDTO);

        // Act
        BaseDTO result = courseService.createInsuranceCourseType(companyId,coreIctId, insuranceCourseType);

        // Assert
        assertEquals(insuranceCourseTypeDTO, result.getObject());

        // Verify that the dependencies were called with the expected arguments
        verify(companyRepository).getById(companyId);
        verify(insuranceCourseTypeRepository).save(insuranceCourseType);
        verify(insuranceCourseTypeMapper).DTO_Course(insuranceCourseType);
    }
    @Test
    public void testGetAllInsuranceCourseType() {
        List<InsuranceCourseType> cours = Arrays.asList(new InsuranceCourseType(), new InsuranceCourseType()); // Create a list of companies for testing
        List<InsuranceCourseTypeDTO> insuranceCourseTypeDTOS = Arrays.asList(new InsuranceCourseTypeDTO(), new InsuranceCourseTypeDTO()); // Create a list of company DTOs for testing

        // Mock behavior of your dependencies
        when(insuranceCourseTypeRepository.findAll()).thenReturn(cours);
        when(insuranceCourseTypeMapper.DTO_LIST(cours)).thenReturn(insuranceCourseTypeDTOS);

        // Call the method you want to test
        BaseDTO result = courseService.getAllInsuranceCoursesType();

        // Verify the result
        assertEquals(insuranceCourseTypeDTOS, result.getObject());
    }

    @Test
    public void testUpdateInsuranceCourseType() {
        // Arrange
        Long courseId = 1L;
        InsuranceCourseType existingInsuranceCourseType = new InsuranceCourseType(); // Create an existing InsuranceCourseType object
        InsuranceCourseType updatedInsuranceCourseType = new InsuranceCourseType(); // Create an updated InsuranceCourseType object
        InsuranceCourseType savedInsuranceCourseType = new InsuranceCourseType(); // Create a saved InsuranceCourseType object
        InsuranceCourseTypeDTO insuranceCourseTypeDTO = new InsuranceCourseTypeDTO(); // Create an expected DTO object

        // Mock behavior of your dependencies
        when(insuranceCourseTypeRepository.findById(courseId)).thenReturn(Optional.of(existingInsuranceCourseType));
        when(insuranceCourseTypeRepository.save(existingInsuranceCourseType)).thenReturn(savedInsuranceCourseType);
        when(insuranceCourseTypeMapper.DTO_Course(savedInsuranceCourseType)).thenReturn(insuranceCourseTypeDTO);

        // Act
        BaseDTO result = courseService.updateInsuranceCourseType(courseId, updatedInsuranceCourseType);

        // Assert
        assertEquals(insuranceCourseTypeDTO, result.getObject());

        // Verify that the dependencies were called with the expected arguments
        verify(insuranceCourseTypeRepository).findById(courseId);
        verify(insuranceCourseTypeRepository, times(1)).save(existingInsuranceCourseType); // Ensure save is called only once
        verify(insuranceCourseTypeMapper).DTO_Course(savedInsuranceCourseType);
    }
    @Test
    public void testDeleteCourse_Success() {
        // Arrange
        Long courseId = 1L;
        InsuranceCourseType insuranceCourseType = new InsuranceCourseType();
        insuranceCourseType.setId(courseId);
        insuranceCourseType.setDeleted(false);

        when(insuranceCourseTypeRepository.findById(courseId)).thenReturn(Optional.of(insuranceCourseType));

        // Act
        BaseDTO result = courseService.deleteInsuranceCourseType(courseId);

        // Assert
        assertTrue(insuranceCourseType.isDeleted());
        assertNotNull(result);
        // Add more assertions as needed to verify the result.
    }
    @Test
    public void testDeleteCourse_NotFound() {
        // Arrange
        Long courseId = 1L;

        when(insuranceCourseTypeRepository.findById(courseId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> courseService.deleteInsuranceCourseType(courseId));
    }


}