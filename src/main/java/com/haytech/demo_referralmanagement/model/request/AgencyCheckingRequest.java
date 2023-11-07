package com.haytech.demo_referralmanagement.model.request;

import com.haytech.demo_referralmanagement.model.base.BaseEntityDTO;
import com.haytech.demo_referralmanagement.model.entity.Agency;
import com.haytech.demo_referralmanagement.model.entity.CheckingType;
import com.haytech.demo_referralmanagement.model.entity.FanavaranPolicy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AgencyCheckingRequest extends BaseEntityDTO{

    private long id;
    private boolean isDone;
    private boolean isUnwilling;
    private long fanavaranPolicyId;
    private long agencyId;
    private long checkingTypeId;


}
