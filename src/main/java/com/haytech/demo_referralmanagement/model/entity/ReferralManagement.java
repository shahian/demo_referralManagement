package com.haytech.demo_referralmanagement.model.entity;


import com.haytech.demo_referralmanagement.model.base.BaseEntity;
import com.haytech.demo_referralmanagement.model.enums.ReferrType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Table(name = "referral_management")
@Where(clause = "deleted = false")
public class ReferralManagement extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "personnel_id")
    private long personnelId;

    @Column(name = "end_date")
    private long endDate;

    @Column(name = "start_date")
    private long startDate;

    @Column(name = "issue_date")
    private long issueDate;

    @Column(name = "mational_code")
    private String nationalCode;

    @Column(name = "insurance_number")
    private String insuranceNumber;

    @Column(name = "processed")
    private boolean processed;

    @Column(name = "referr_type")
    private ReferrType referrType;

}
