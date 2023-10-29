package com.haytech.demo_referralmanagement.repository;

import com.haytech.demo_referralmanagement.model.entity.ReferralManagement;
import com.haytech.demo_referralmanagement.model.enums.ReferrType;
import com.haytech.demo_referralmanagement.repository.base.BaseJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
@Repository
public interface ReferralManagementRepository extends JpaRepository<ReferralManagement,Long> ,JpaSpecificationExecutor<ReferralManagement>{

    @Query("SELECT rm FROM ReferralManagement rm " +
            "WHERE (:personnelId IS NULL OR rm.personnelId = :personnelId) " +
            "AND (:insuranceNumber IS NULL OR rm.insuranceNumber = :insuranceNumber) " +
            "AND (:nationalCode IS NULL OR rm.nationalCode = :nationalCode) " +
            "AND (:processed IS NULL OR rm.processed = :processed) " +
            "AND (:referrType IS NULL OR rm.referrType = :referrType)")
    List<ReferralManagement> findByQuery(
            Long personnelId, String insuranceNumber, String nationalCode, Boolean processed, ReferrType referrType);

  default List<ReferralManagement> findByFilter(
            Long personnelId, String insuranceNumber, String nationalCode, Boolean processed, ReferrType referrType) {
        return findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            likeFilter(root,predicates,criteriaBuilder,personnelId.toString(),"personnelId");
            likeFilter(root,predicates,criteriaBuilder,insuranceNumber,"insuranceNumber");
            likeFilter(root,predicates,criteriaBuilder,nationalCode,"nationalCode");
            likeFilter(root,predicates,criteriaBuilder,processed.toString(),"processed");
            likeFilter(root,predicates,criteriaBuilder,referrType!=null?referrType.toString():"","referrType");
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }

    default void likeFilter(Root<ReferralManagement> root, List<Predicate> predicates, CriteriaBuilder criteriaBuilder, String input, String fieldName) {
        if (!StringUtils.isEmpty(input))
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(fieldName)), "%" + input.toLowerCase() + "%"));

    }

    @Query("SELECT rm FROM ReferralManagement rm")
    List<ReferralManagement> findAllByCriteria();
}
