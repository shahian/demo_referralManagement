package com.haytech.demo_referralmanagement.repository.specification;

import com.haytech.demo_referralmanagement.model.entity.AgencyChecking;
import com.haytech.demo_referralmanagement.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;


import com.haytech.demo_referralmanagement.model.enums.ReferrType;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.haytech.demo_referralmanagement.model.entity.ReferralManagement;

public class ReferralManagementSpecifications {
    public static Specification<AgencyChecking> findByCriteria(
            Long personnelId, String insuranceNumber, String nationalCode, Boolean isDone, String checkingTypeName) {
        return (Root<AgencyChecking> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Predicate predicate = builder.conjunction();

            if (personnelId != null) {
                predicate = builder.and(predicate, builder.equal(root.get("personnelId"), personnelId));
            }
            if (insuranceNumber != null) {
                predicate = builder.and(predicate, builder.equal(root.get("insuranceNumber"), insuranceNumber));
            }
            if (nationalCode != null) {
                predicate = builder.and(predicate, builder.equal(root.get("nationalCode"), nationalCode));
            }
            if (isDone != null) {
                predicate = builder.and(predicate, builder.equal(root.get("isDone"), isDone));
            }
            if (checkingTypeName != null) {
                predicate = builder.and(predicate, builder.equal(root.get("checkingTypeName"), checkingTypeName));
            }

            return predicate;
        };
    }
}