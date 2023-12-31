package com.haytech.demo_referralmanagement.repository;

import com.haytech.demo_referralmanagement.model.entity.Agency;
import com.haytech.demo_referralmanagement.model.entity.FanavaranPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FanavaranPolicyRepository extends JpaRepository<FanavaranPolicy, Long> {
}