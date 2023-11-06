package com.haytech.demo_referralmanagement.utility;

import com.haytech.demo_referralmanagement.model.entity.FanavaranPolicy;
import com.haytech.demo_referralmanagement.repository.FanavaranPolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyApplicationRunner implements ApplicationRunner {

    private final FanavaranPolicyRepository repository;

    @Autowired
    public MyApplicationRunner(FanavaranPolicyRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<FanavaranPolicy> fanavaranPolicies = repository.findAll();
        List<FanavaranPolicy> fanavaranPolicyList= new ArrayList<>();
        if (fanavaranPolicies.stream().count() == 0) {
            fanavaranPolicyList = generateMockData();
            repository.saveAll(fanavaranPolicyList);
        }

    }

    public static List<FanavaranPolicy> generateMockData() {
        List<FanavaranPolicy> mockData = new ArrayList<>();

        for (int i = 1; i <= 50; i++) {
            FanavaranPolicy fanavaranPolicy = FanavaranPolicy.builder()
                    .policyId(123)
                    .insuranceNumber("ABC123" +i)
                    .beginDate(System.currentTimeMillis())
                    .endDate(System.currentTimeMillis())
                    .issueDate(System.currentTimeMillis())
                    .fkInsuranceCourseTypeId(456)
                    .gid("GID123"+i)
                    .personnelId("Person123"+i)
                    .completeInsuranceNumber("Complete123"+i)
                    .agencyName((byte) 1) // Assuming 'agencyName' is a byte field
                    .build();

            mockData.add(fanavaranPolicy);
        }

        return mockData;
    }
}