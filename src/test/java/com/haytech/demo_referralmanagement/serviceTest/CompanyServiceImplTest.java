package com.haytech.demo_referralmanagement.serviceTest;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.dto.CompanyDTO;
import com.haytech.demo_referralmanagement.model.entity.Company;
import com.haytech.demo_referralmanagement.model.entity.InsuranceCourseType;
import com.haytech.demo_referralmanagement.repository.CompanyRepository;
import com.haytech.demo_referralmanagement.model.mapper.CompanyMapper;
import com.haytech.demo_referralmanagement.utility.ApplicationProperties;
import com.haytech.demo_referralmanagement.service.impl.CompanyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import javax.persistence.EntityExistsException;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

public class CompanyServiceImplTest {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private ApplicationProperties applicationProperties;

    @Mock
    private CompanyMapper companyMapper;

    @InjectMocks
    private CompanyServiceImpl companyService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCompany() {
        Company newCompany = new Company(); // Create a Company object for testing
        CompanyDTO companyDTO = new CompanyDTO(); // Create a CompanyDTO object for testing

        // Mock behavior of your dependencies
        Mockito.when(companyRepository.save(newCompany)).thenReturn(newCompany);
        Mockito.when(companyMapper.DTO_Company(newCompany)).thenReturn(companyDTO);

        // Call the method you want to test
        BaseDTO result = companyService.createCompany(companyDTO);

        // Verify the result
        assertEquals(companyDTO, result.getObject());
    }
    @Test
    public void testGetAllCompanies() {
        List<Company> companies = Arrays.asList(new Company(), new Company()); // Create a list of companies for testing
        List<CompanyDTO> companyDTOS = Arrays.asList(new CompanyDTO(), new CompanyDTO()); // Create a list of company DTOs for testing

        // Mock behavior of your dependencies
        Mockito.when(companyRepository.findAll()).thenReturn(companies);
        Mockito.when(companyMapper.DTO_LIST(companies)).thenReturn(companyDTOS);

        // Call the method you want to test
        BaseDTO result = companyService.getAllCompanies();

        // Verify the result
        assertEquals(companyDTOS, result.getObject());
    }

    @Test
    public void testUpdateCompany() {
        Long companyId = 1L;
        Company existingCompany = new Company();
        CompanyDTO companyDTO = new CompanyDTO(); // Create a CompanyDTO object for testing
        Company updatedCompany = new Company();

        // Mock behavior of your dependencies
        Mockito.when(companyRepository.findById(companyId)).thenReturn(java.util.Optional.of(existingCompany));
        Mockito.when(companyMapper.DTO_Company(existingCompany)).thenReturn(companyDTO);

        // Call the method you want to test
        BaseDTO result = companyService.updateCompany(companyId, companyDTO);

        // Verify the result
        assertEquals(companyDTO, result.getObject());
    }
    @Test
    public void testDeleteCompany() {
        Long companyId = 1L;
        Company company = new Company();
        company.setInsuranceCourseTypeList(Arrays.asList(new InsuranceCourseType()));

        // Mock behavior of your dependencies
        Mockito.when(companyRepository.findById(companyId)).thenReturn(java.util.Optional.of(company));

        // Call the method you want to test and expect an exception
        assertThrows(EntityExistsException.class, () -> companyService.deleteCompany(companyId));

        // Verify that companyRepository.deleteById was not called
        Mockito.verify(companyRepository, times(0)).deleteById(companyId);
    }

    @Test
    public void testGetCompany() {
        Long companyId = 1L;
        Company company = new Company(); // Create a Company object for testing
        CompanyDTO  companyDTO = new CompanyDTO();
        // Mock behavior of your dependencies
        Mockito.when(companyRepository.getById(companyId)).thenReturn(company);
        Mockito.when(companyMapper.DTO_Company(company)).thenReturn(companyDTO);

        // Call the method you want to test
        BaseDTO result = companyService.getCompany(companyId);

        // Verify the result
        assertEquals(companyDTO, result.getObject());
    }
}