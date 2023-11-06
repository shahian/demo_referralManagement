package com.haytech.demo_referralmanagement.model.mapper;

import com.haytech.demo_referralmanagement.model.dto.AgencyDTO;
import com.haytech.demo_referralmanagement.model.dto.FanavaranPolicyDTO;
import com.haytech.demo_referralmanagement.model.entity.Agency;
import com.haytech.demo_referralmanagement.model.entity.FanavaranPolicy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FanavaranPolicyMapper {
    List<FanavaranPolicyDTO>DTO_LIST(List<FanavaranPolicy>fanavaranPolicyList);
    FanavaranPolicyDTO DTO_FanavaranPolicyDTO(FanavaranPolicy fanavaranPolicy);
    FanavaranPolicy FanavaranPolicy_DTO(FanavaranPolicyDTO fanavaranPolicyDTO);
}
