package com.haytech.demo_referralmanagement.service.impl;

import com.haytech.demo_referralmanagement.model.entity.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceImplTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyServiceImpl companyService;

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
        Company updatedCompany = new Company();
        updatedCompany.setName("Updated Company");
        updatedCompany.setCode("UC");

        when(companyRepository.findById(anyLong())).thenReturn(Optional.of(company));
        when(companyRepository.save(any(Company.class))).thenReturn(updatedCompany);

        Company resultCompany = companyService.updateCompany(1L, updatedCompany);

        assertEquals(updatedCompany, resultCompany);
        verify(companyRepository, times(1)).findById(1L);
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
        when(companyRepository.findById(anyLong())).thenReturn(Optional.of(company));

        Company resultCompany = companyService.getCompany(1L);

        assertEquals(company, resultCompany);
        verify(companyRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAllCompaniesThenAllCompaniesReturned() {
        List<Company> companies = Arrays.asList(company);

        when(companyRepository.findAll()).thenReturn(companies);

        List<Company> resultCompanies = companyService.getAllCompanies();

        assertEquals(companies, resultCompanies);
        verify(companyRepository, times(1)).findAll();
    }
}
