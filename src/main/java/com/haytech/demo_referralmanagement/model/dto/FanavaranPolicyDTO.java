package com.haytech.demo_referralmanagement.model.dto;

import com.haytech.demo_referralmanagement.model.base.BaseEntityDTO;
import com.haytech.demo_referralmanagement.model.entity.AgencyChecking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class FanavaranPolicyDTO extends BaseEntityDTO{

    private long id;
    private String name;
    private long personnelId;

    private long endDate;

    private long startDate;

    private long issueDate;

    private String nationalCode;

    private String insuranceNumber;

    private List<AgencyChecking> agencyCheckings;

}
