package com.haytech.demo_referralmanagement.utility;

import com.haytech.demo_referralmanagement.model.entity.Agency;
import com.haytech.demo_referralmanagement.model.entity.AgencyChecking;
import com.haytech.demo_referralmanagement.model.entity.CheckingType;
import com.haytech.demo_referralmanagement.model.entity.FanavaranPolicy;
import com.haytech.demo_referralmanagement.repository.AgencyCheckingRepository;
import com.haytech.demo_referralmanagement.repository.AgencyRepository;
import com.haytech.demo_referralmanagement.repository.CheckingTypeRepository;
import com.haytech.demo_referralmanagement.repository.FanavaranPolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyApplicationRunner implements ApplicationRunner {

    private final FanavaranPolicyRepository fanavaranPolicyRepository;
    private final AgencyRepository agencyRepository;
    private final CheckingTypeRepository checkingTypeRepository;
    private final AgencyCheckingRepository agencyCheckingRepository;

    @Autowired
    public MyApplicationRunner(FanavaranPolicyRepository fanavaranPolicyRepository, AgencyRepository agencyRepository, CheckingTypeRepository checkingTypeRepository, AgencyCheckingRepository agencyCheckingRepository) {
        this.fanavaranPolicyRepository = fanavaranPolicyRepository;
        this.agencyRepository = agencyRepository;
        this.checkingTypeRepository = checkingTypeRepository;
        this.agencyCheckingRepository = agencyCheckingRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<FanavaranPolicy> fanavaranPolicies = fanavaranPolicyRepository.findAll();
        List<FanavaranPolicy> fanavaranPolicyList = new ArrayList<>();
        List<AgencyChecking> agencyCheckings = agencyCheckingRepository.findAll();
        List<AgencyChecking> agencyCheckingList = new ArrayList<>();
        List<Agency> agencies = agencyRepository.findAll();
        List<Agency> agencyList = new ArrayList<>();
        List<CheckingType> checkingTypes = checkingTypeRepository.findAll();
        List<CheckingType> checkingTypeList = new ArrayList<>();
        if (fanavaranPolicies.stream().count() == 0) {
            fanavaranPolicyList = generateMockData();
            fanavaranPolicyRepository.saveAll(fanavaranPolicyList);
        }
        if (checkingTypes.size()==0){
            checkingTypeList=generateCheckingTypeMockData();
            checkingTypeRepository.saveAll(checkingTypeList);
        }
        if (agencies.size()==0){
            agencyList=generateAgencyMockData();
            agencyRepository.saveAll(agencyList);
        }
        if (agencyCheckings.size() == 0) {
            agencyCheckingList = generateAgencyCheckingMockData(fanavaranPolicies, agencies, checkingTypes);
            agencyCheckingRepository.saveAll(agencyCheckingList);
        }
    }

    private List<Agency> generateAgencyMockData() {
        List<Agency> mockData = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            Agency  agency = Agency.builder()
                    .name("company"+i)
                    .build();
            mockData.add(agency);
        }
        return mockData;
    }

    private List<CheckingType> generateCheckingTypeMockData() {
        List<CheckingType>checkingTypes=new ArrayList<>();
        CheckingType checkingType1=CheckingType.builder()
                .name("اتمام دوره").build();
        checkingTypes.add(checkingType1);
        CheckingType checkingType2=CheckingType.builder()
                .name("اتمام پوشش").build();
        checkingTypes.add(checkingType2);

        CheckingType checkingType3=CheckingType.builder()
                .name("درج اشتباه کد پرسنلی").build();
        checkingTypes.add(checkingType3);
        CheckingType checkingType4=CheckingType.builder()
                .name("بازخرید").build();
        checkingTypes.add(checkingType4);
        CheckingType checkingType5=CheckingType.builder()
                .name("فوت").build();
        checkingTypes.add(checkingType5);
        CheckingType checkingType6=CheckingType.builder()
                .name("خطا در نوع پرداخت حق بیمه").build();
        checkingTypes.add(checkingType6);
        CheckingType checkingType7=CheckingType.builder()
                .name("مبلغ غیر مجاز اقساط بیمه نامه").build();
        checkingTypes.add(checkingType7);


        return checkingTypes;
    }

    private List<AgencyChecking> generateAgencyCheckingMockData(List<FanavaranPolicy> fanavaranPolicies, List<Agency> agencies, List<CheckingType> checkingTypes) {
        List<AgencyChecking> mockData = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            AgencyChecking agencyChecking = AgencyChecking.builder()
                    .agency(agencies.get(1))
                    .checkingType(checkingTypes.get(1))
                    .fanavaranPolicy(fanavaranPolicies.get(1))
                    .isDone(true)
                    .isUnwilling(true)
                    .build();
            mockData.add(agencyChecking);
        }
        return mockData;

    }

    public static List<FanavaranPolicy> generateMockData() {
        List<FanavaranPolicy> mockData = new ArrayList<>();

        for (int i = 1; i <= 50; i++) {
            FanavaranPolicy fanavaranPolicy = FanavaranPolicy.builder()
                    .policyId(123L + i)
                    .insuranceNumber("ABC123" + i)
                    .beginDate(System.currentTimeMillis())
                    .endDate(System.currentTimeMillis())
                    .issueDate(System.currentTimeMillis())
                    .fkInsuranceCourseTypeId(456)
                    .gid("GID123" + i)
                    .personnelId("Person123" + i)
                    .completeInsuranceNumber("Complete123" + i)
                    .agencyName((byte) 1) // Assuming 'agencyName' is a byte field
                    .build();

            mockData.add(fanavaranPolicy);
        }

        return mockData;
    }
}