package com.haytech.demo_referralmanagement.repository;

import com.haytech.demo_referralmanagement.model.entity.InsuranceCourseType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface InsuranceCourseTypeRepository extends JpaRepository<InsuranceCourseType, Long> {
    InsuranceCourseType getById(Long courseId);

    List<InsuranceCourseType> findAllByCompany_Id(Long companyId);
    List<InsuranceCourseType> findAllByCoreInsuranceCourseType_Id(Long courseId);
    List<InsuranceCourseType> findAllByCoreInsuranceCourseType_IdAndAndCompany_Id(Long courseId,Long companyId);
}
