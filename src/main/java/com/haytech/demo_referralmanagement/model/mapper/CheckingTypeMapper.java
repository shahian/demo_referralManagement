package com.haytech.demo_referralmanagement.model.mapper;

import com.haytech.demo_referralmanagement.model.dto.AgencyDTO;
import com.haytech.demo_referralmanagement.model.dto.CheckingTypeDTO;
import com.haytech.demo_referralmanagement.model.entity.Agency;
import com.haytech.demo_referralmanagement.model.entity.CheckingType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CheckingTypeMapper {
    List<CheckingTypeDTO> DTO_LIST(List<CheckingType> checkingTypeList);

    CheckingTypeDTO DTO_CheckingType(CheckingType checkingType);

    CheckingType CheckingTypeDTO(CheckingTypeDTO checkingTypeDTO);
}