package com.haytech.demo_referralmanagement.service;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.base.MetaDTO;
import com.haytech.demo_referralmanagement.model.dto.ReferralManagementDTO;
import com.haytech.demo_referralmanagement.model.entity.ReferralManagement;
import com.haytech.demo_referralmanagement.model.enums.ReferrType;
import com.haytech.demo_referralmanagement.model.mapper.ReferralManagementMapper;
import com.haytech.demo_referralmanagement.repository.ReferralManagementRepository;
import com.haytech.demo_referralmanagement.service.impl.ReferralManagementServiceImpl;
import com.haytech.demo_referralmanagement.utility.ApplicationProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class ReferralManagementServiceImplTest {
    @InjectMocks
    private ReferralManagementServiceImpl referralManagementService;

    @Mock
    private ReferralManagementRepository referralManagementRepository;

    @Mock
    private ReferralManagementMapper referralManagementMapper;

    @Mock
    private ApplicationProperties applicationProperties;

    List<ReferralManagement> referralManagementList = new ArrayList<>();
    List<ReferralManagementDTO> expectedDTOList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize the mocks
        ReferralManagement referral1 = new ReferralManagement();
        referral1.setId(1);
        referral1.setPersonnelId(1001);
        referral1.setProcessed(true);
        referral1.setStartDate(1635200000);
        referral1.setEndDate(1636200000);
        referral1.setIssueDate(1637200000);
        referral1.setNationalCode("1234567890");
        referral1.setInsuranceNumber("ABC123");
        referral1.setReferrType(ReferrType.COMPLETION_OF_THE_COURSE); // Replace with the appropriate enum value
        // Set other properties as needed...

        ReferralManagement referral2 = new ReferralManagement();
        referral2.setId(2);
        referral2.setPersonnelId(1002);
        referral2.setProcessed(false);
        referral2.setStartDate(1635200000);
        referral2.setEndDate(1636200000);
        referral2.setIssueDate(1637200000);
        referral2.setNationalCode("9876543210");
        referral2.setInsuranceNumber("XYZ456");
        referral2.setReferrType(ReferrType.COMPLETE_COVERAGE); // Replace with the appropriate enum value
        // Set other properties as needed...

        referralManagementList.add(referral1);
        referralManagementList.add(referral2);
        ReferralManagementDTO dto1 = new ReferralManagementDTO();
        dto1.setId(1);
        dto1.setPersonnelId(1001);
        dto1.setProcessed(true);
        dto1.setStartDate(1635200000);
        dto1.setEndDate(1636200000);
        dto1.setIssueDate(1637200000);
        dto1.setNationalCode("1234567890");
        dto1.setInsuranceNumber("ABC123");
        dto1.setReferrType(ReferrType.COMPLETION_OF_THE_COURSE); // Replace with the appropriate enum value
        // Set other properties as needed...

        ReferralManagementDTO dto2 = new ReferralManagementDTO();
        dto2.setId(2);
        dto2.setPersonnelId(1002);
        dto2.setProcessed(false);
        dto2.setStartDate(1635200000);
        dto2.setEndDate(1636200000);
        dto2.setIssueDate(1637200000);
        dto2.setNationalCode("9876543210");
        dto2.setInsuranceNumber("XYZ456");
        dto2.setReferrType(ReferrType.COMPLETE_COVERAGE); // Replace with the appropriate enum value
        // Set other properties as needed...

        expectedDTOList.add(dto1);
        expectedDTOList.add(dto2);
    }

    @Test
    public void testGetAll() {

        // Mock the behavior of your repository, mapper, and applicationProperties
        Mockito.when(referralManagementRepository.findAll()).thenReturn(referralManagementList);
        Mockito.lenient().when(referralManagementMapper.DTO_LIST(referralManagementList)).thenReturn(expectedDTOList);
        Mockito.when(applicationProperties.getCode("application.message.success1.code")).thenReturn(200);
        Mockito.when(applicationProperties.getProperty("application.message.success1.text")).thenReturn("Success");

        // Call the service method
        BaseDTO<List<ReferralManagementDTO>> result = referralManagementService.getAll();

        // Assert and validate the result
        assertNotNull(result);

        // Check if referralManagementList and expectedDTOList are non-empty
        assertFalse(referralManagementList.isEmpty());
        assertFalse(expectedDTOList.isEmpty());

        // Assert other details as needed (e.g., size, code, message)
        assertEquals(expectedDTOList.size(), result.getObject().size());
        MetaDTO meta = result.getMeta();
        assertNotNull(meta);
        assertEquals(200, meta.getCode());
        assertEquals("Success", meta.getMessage());
    }

    @Test
    public void testFilterReferralManagement() {
        // Mock data
        Long personnelId = 1L;
        String insuranceNumber = "12345";
        String nationalCode = "ABC123";
        boolean processed = true;
        ReferrType referrType = ReferrType.COMPLETE_COVERAGE;

        // Create a list of ReferralManagement objects (you can customize this list)
        List<ReferralManagement> mockData = new ArrayList<>();
        // Populate the mockData list with sample ReferralManagement objects

        // Mock the behavior of the repository and mapper
        Mockito.when(referralManagementRepository.findByQuery(personnelId, insuranceNumber, nationalCode, processed, referrType))
                .thenReturn(mockData);

        List<ReferralManagementDTO> expectedDTOs = new ArrayList<>();
        // Populate the expectedDTOs list with the expected DTO objects

        Mockito.when(referralManagementMapper.DTO_LIST(mockData))
                .thenReturn(expectedDTOs);

        // Call the service method
        BaseDTO result = referralManagementService.filterReferralManagementCriteria(personnelId, insuranceNumber, nationalCode, processed, referrType);

        // Perform assertions
        // You may need to override the equals method in BaseDTO for this comparison
        assertEquals(expectedDTOs, result);

    }
}
