package com.haytech.demo_referralmanagement.repository;

import com.haytech.demo_referralmanagement.model.entity.AgencyChecking;
import com.haytech.demo_referralmanagement.model.enums.ReferrType;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
@Repository
public interface AgencyCheckingRepository extends JpaRepository<AgencyChecking,Long> ,JpaSpecificationExecutor<AgencyChecking>{



    @Query("SELECT ac FROM AgencyChecking ac,FanavaranPolicy fp,CheckingType ct " +
            "where ac.fanavaranPolicy.id=fp.id " +
            "and ac.checkingType.id =ct.id " +
            "and (:personnelId IS NULL OR fp.personnelId = :personnelId) " +
            "AND (:insuranceNumber IS NULL OR fp.insuranceNumber = :insuranceNumber) " +
            "AND (:nationalCode IS NULL OR fp.nationalCode = :nationalCode) " +
            "AND (:isDone IS NULL OR ac.isDone = :isDone) " +
            "AND (:checkingTypeId IS NULL OR ct.id = :checkingTypeId)")
    List<AgencyChecking> findByQuery(
            String personnelId, String insuranceNumber, String nationalCode, Boolean isDone, Long checkingTypeId);
  default List<AgencyChecking> findByFilter(
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

    default void likeFilter(Root<AgencyChecking> root, List<Predicate> predicates, CriteriaBuilder criteriaBuilder, String input, String fieldName) {
        if (!StringUtils.isEmpty(input))
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(fieldName)), "%" + input.toLowerCase() + "%"));

    }

    Page<AgencyChecking> findAll (Pageable pageable);
}
