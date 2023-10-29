package com.haytech.demo_referralmanagement.utility;

import com.haytech.demo_referralmanagement.model.entity.ReferralManagement;
import com.haytech.demo_referralmanagement.model.enums.ReferrType;
import com.haytech.demo_referralmanagement.repository.ReferralManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyApplicationRunner implements ApplicationRunner {

    private final ReferralManagementRepository repository;

    @Autowired
    public MyApplicationRunner(ReferralManagementRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<ReferralManagement> referralManagements = repository.findAll();
        List<ReferralManagement> referralManagementsList = new ArrayList<>();
        if (referralManagements.stream().count() == 0) {
            referralManagementsList = generateMockData();
            repository.saveAll(referralManagementsList);
        }

    }

    public static List<ReferralManagement> generateMockData() {
        List<ReferralManagement> mockData = new ArrayList<>();

        for (int i = 1; i <= 50; i++) {
            ReferralManagement data = ReferralManagement.builder()
                    .id(i)
                    .personnelId(1000 + i)
                    .endDate(System.currentTimeMillis())
                    .startDate(System.currentTimeMillis() - 86400000)
                    .issueDate(System.currentTimeMillis() - 172800000)
                    .nationalCode("1234567890")
                    .insuranceNumber("ABC12345")
                    .creationDate(System.currentTimeMillis() - 86400000)
                    .processed(true)
                    .referrType(ReferrType.COMPLETE_COVERAGE)
                    .build();

            mockData.add(data);
        }

        return mockData;
    }
}