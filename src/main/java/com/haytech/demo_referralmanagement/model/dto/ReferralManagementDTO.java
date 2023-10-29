package com.haytech.demo_referralmanagement.model.dto;

import com.haytech.demo_referralmanagement.model.base.BaseEntityDTO;
import com.haytech.demo_referralmanagement.model.enums.ReferrType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ReferralManagementDTO extends BaseEntityDTO{

    private long id;
    private long personnelId;

    private long endDate;

    private long startDate;

    private long issueDate;

    private String nationalCode;

    private String insuranceNumber;

    private boolean processed;

    private ReferrType referrType;


}
