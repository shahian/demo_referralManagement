package com.haytech.demo_referralmanagement.model.mapper;

import com.haytech.demo_referralmanagement.model.dto.AgencyCheckingDTO;
import com.haytech.demo_referralmanagement.model.entity.AgencyChecking;
import com.haytech.demo_referralmanagement.model.entity.ReferralManagement;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AgencyCheckingMapper {
    List<AgencyCheckingDTO>DTO_LIST(List<AgencyChecking>referralManagementList);
    AgencyCheckingDTO DTO_ManagementDto(AgencyChecking referralManagement);
}
