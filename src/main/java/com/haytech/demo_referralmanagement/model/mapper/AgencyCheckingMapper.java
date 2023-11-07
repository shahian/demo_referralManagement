package com.haytech.demo_referralmanagement.model.mapper;

import com.haytech.demo_referralmanagement.model.dto.AgencyCheckingDTO;
import com.haytech.demo_referralmanagement.model.entity.AgencyChecking;
import com.haytech.demo_referralmanagement.model.entity.ReferralManagement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AgencyCheckingMapper {
    List<AgencyCheckingDTO>DTO_LIST(List<AgencyChecking>referralManagementList);
    AgencyCheckingDTO DTO_AgencyChecking(AgencyChecking agencyChecking);
    AgencyChecking AgencyChecking_DTO(AgencyCheckingDTO agencyCheckingDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fanavaranPolicy", ignore = true)
    @Mapping(target = "agency", ignore = true)
    @Mapping(target = "checkingType", ignore = true)
    void updateFromDTO(AgencyCheckingDTO agencyCheckingDTO, @MappingTarget AgencyChecking agencyChecking);
}
