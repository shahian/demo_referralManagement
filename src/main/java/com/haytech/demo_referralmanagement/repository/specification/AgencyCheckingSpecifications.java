package com.haytech.demo_referralmanagement.repository.specification;

import com.haytech.demo_referralmanagement.model.entity.AgencyChecking;


import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AgencyCheckingSpecifications {
    public static Specification<AgencyChecking> findByCriteria(
            String personnelId, String insuranceNumber, String nationalCode, Boolean isDone, Long checkingTypeId) {
        return (Root<AgencyChecking> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Predicate predicate = builder.conjunction();

            if (personnelId != null) {
                predicate = builder.and(predicate, builder.equal(root.get("fanavaranPolicy").get("personnelId"), personnelId));

            }
            if (insuranceNumber != null) {
                predicate = builder.and(predicate, builder.equal(root.get("fanavaranPolicy").get("insuranceNumber"), insuranceNumber));
            }
            if (nationalCode != null) {
                predicate = builder.and(predicate, builder.equal(root.get("fanavaranPolicy").get("nationalCode"), nationalCode));
            }
            if (isDone != null) {
                predicate = builder.and(predicate, builder.equal(root.get("isDone"), isDone));
            }
            if (checkingTypeId != null) {
                predicate = builder.and(predicate, builder.equal(root.get("checkingType").get("id"), checkingTypeId));
            }

            return predicate;
        };
    }
}