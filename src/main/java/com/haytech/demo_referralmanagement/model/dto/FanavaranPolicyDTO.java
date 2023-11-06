package com.haytech.demo_referralmanagement.model.dto;

import com.haytech.demo_referralmanagement.model.base.BaseEntityDTO;
import com.haytech.demo_referralmanagement.model.entity.AgencyChecking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class FanavaranPolicyDTO extends BaseEntityDTO{

    private long id;
    private Long policyId;
    private String insuranceNumber;
    private Long beginDate;
    private Long endDate;
    private Long issueDate;
    private Integer fkInsuranceCourseTypeId;
    private String gid;
    private String personnelId;
    private String completeInsuranceNumber;
    private Byte agencyName;
    private String nationalCode;
    private List<AgencyChecking> agencyCheckings;

}
