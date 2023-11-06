package com.haytech.demo_referralmanagement.repository;

import com.haytech.demo_referralmanagement.model.entity.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {
}