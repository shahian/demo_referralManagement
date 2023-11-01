package com.haytech.demo_referralmanagement.model.dto;

import com.haytech.demo_referralmanagement.model.base.BaseEntityDTO;
import com.haytech.demo_referralmanagement.model.entity.Company;
import com.haytech.demo_referralmanagement.model.entity.CoreInsuranceCourseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class InsuranceCourseTypeDTO extends BaseEntityDTO{

    private long id;
    private String name;
    private String code;
    private Company company;
    private CoreInsuranceCourseType coreInsuranceCourseType;


}
