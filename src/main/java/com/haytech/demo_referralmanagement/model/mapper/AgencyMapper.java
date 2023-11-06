package com.haytech.demo_referralmanagement.model.mapper;

import com.haytech.demo_referralmanagement.model.dto.AgencyCheckingDTO;
import com.haytech.demo_referralmanagement.model.dto.AgencyDTO;
import com.haytech.demo_referralmanagement.model.entity.Agency;
import com.haytech.demo_referralmanagement.model.entity.AgencyChecking;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AgencyMapper {
    List<AgencyDTO>DTO_LIST(List<Agency>agencyList);
    AgencyDTO DTO_AgencyDTO(Agency agency);
    Agency Agency_DTO(AgencyDTO agencyDTO);
}
