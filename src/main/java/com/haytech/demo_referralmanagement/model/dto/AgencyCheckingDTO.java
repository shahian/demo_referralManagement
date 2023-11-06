package com.haytech.demo_referralmanagement.model.dto;

import com.haytech.demo_referralmanagement.model.base.BaseEntityDTO;
import com.haytech.demo_referralmanagement.model.entity.Agency;
import com.haytech.demo_referralmanagement.model.entity.CheckingType;
import com.haytech.demo_referralmanagement.model.entity.FanavaranPolicy;
import com.haytech.demo_referralmanagement.model.enums.ReferrType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AgencyCheckingDTO extends BaseEntityDTO{

    private long id;
    private boolean isDone;
    private boolean isUnwilling;
    private FanavaranPolicy fanavaranPolicy;
    private Agency agency;
    private CheckingType checkingType;


}
