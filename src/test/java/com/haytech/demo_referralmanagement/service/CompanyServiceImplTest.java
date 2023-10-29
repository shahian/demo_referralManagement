package com.haytech.demo_referralmanagement.service;

import com.haytech.demo_referralmanagement.model.entity.Company;
import com.haytech.demo_referralmanagement.repository.CompanyRepository;
import com.haytech.demo_referralmanagement.service.impl.CompanyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceImplTest {


    @Mock
    private CompanyRepository companyRepository ;
    @InjectMocks
    private CompanyServiceImpl companyService ;


    private Company company;

    @BeforeEach
    public void setUp() {

        company = new Company();
        company.setId(1L);
        company.setName("Test Company");
        company.setCode("TC");
    }

    @Test
    public void testCreateCompanyWhenValidCompanyThenCompanySaved() {
        when(companyRepository.save(any(Company.class))).thenReturn(company);

        Company savedCompany = companyService.createCompany(company);

        assertEquals(company, savedCompany);
        verify(companyRepository, times(1)).save(company);
    }

    @Test
    public void testUpdateCompanyWhenValidCompanyAndIdThenCompanyUpdated() {
        // Create a sample company to update
        Company updatedCompany = new Company();
        updatedCompany.setId(1L);
        updatedCompany.setName("Updated Company");
        updatedCompany.setCode("UC");

        // Mock the behavior of companyRepository.findById to return a Company when called with 1L
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));

        // Mock the behavior of companyRepository.save to return the updatedCompany
        when(companyRepository.save(any(Company.class))).thenReturn(updatedCompany);

        // Call the service method to update the company
        Company resultCompany = companyService.updateCompany(1L, updatedCompany);

        // Perform assertions
        assertEquals(updatedCompany, resultCompany);

        // Verify that companyRepository.findById was called once with the argument 1L
        verify(companyRepository, times(1)).findById(1L);

        // Verify that companyRepository.save was called once with the updatedCompany
        verify(companyRepository, times(1)).save(updatedCompany);
    }

    @Test
    public void testDeleteCompanyWhenValidIdThenCompanyDeleted() {
        doNothing().when(companyRepository).deleteById(anyLong());

        companyService.deleteCompany(1L);

        verify(companyRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetCompanyWhenValidIdThenCompanyReturned() {
        long companyId=1L;
        when(companyRepository.getById(companyId)).thenReturn(company);

        Company resultCompany = companyService.getCompany(1L);

        assertEquals(company, resultCompany);
        verify(companyRepository, times(1)).getById(1L);
    }

    @Test
    public void testGetAllCompaniesThenAllCompaniesReturned() {
        List<Company> companies = Arrays.asList(company);

        when(companyRepository.findAll()).thenReturn(companies);
        when(companyRepository.findAll()).thenReturn(companies);

        List<Company> resultCompanies = companyService.getAllCompanies();
        List<Company> resultCompanies1 = companyService.getAllCompanies();

        assertEquals(companies, resultCompanies);
        verify(companyRepository, times(2)).findAll();
    }
}
