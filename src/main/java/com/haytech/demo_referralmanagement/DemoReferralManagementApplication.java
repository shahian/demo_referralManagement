package com.haytech.demo_referralmanagement;

import com.haytech.demo_referralmanagement.repository.*;
import com.haytech.demo_referralmanagement.utility.MyApplicationRunner;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoReferralManagementApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoReferralManagementApplication.class, args);
    }
    @Bean
    public ApplicationRunner applicationRunner(FanavaranPolicyRepository fanavaranPolicyRepository, AgencyRepository agencyRepository, CheckingTypeRepository checkingTypeRepository, AgencyCheckingRepository agencyCheckingRepository,
                                               CoreInsuranceCourseTypeRepository coreInsuranceCourseTypeRepository) {
        return new MyApplicationRunner(fanavaranPolicyRepository,agencyRepository,checkingTypeRepository,agencyCheckingRepository,coreInsuranceCourseTypeRepository);
    }
}
