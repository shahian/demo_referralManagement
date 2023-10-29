package com.haytech.demo_referralmanagement.model.mapper;

import com.haytech.demo_referralmanagement.model.dto.ReferralManagementDTO;
import com.haytech.demo_referralmanagement.model.entity.ReferralManagement;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReferralManagementMapper {
    List<ReferralManagementDTO>DTO_LIST(List<ReferralManagement>referralManagementList);
    ReferralManagementDTO DTO_ManagementDto(ReferralManagement referralManagement);
}
