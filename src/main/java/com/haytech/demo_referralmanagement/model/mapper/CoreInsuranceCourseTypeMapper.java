package com.haytech.demo_referralmanagement.model.mapper;

import com.haytech.demo_referralmanagement.model.dto.CoreInsuranceCourseTypeDTO;
import com.haytech.demo_referralmanagement.model.entity.CoreInsuranceCourseType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoreInsuranceCourseTypeMapper {
    CoreInsuranceCourseTypeDTO DTO_CoreInsuranceCourseType(CoreInsuranceCourseType coreInsuranceCourseType);
    CoreInsuranceCourseType CoreInsuranceCourseType_DTO(CoreInsuranceCourseTypeDTO  insuranceCourseTypeDTO);
    List<CoreInsuranceCourseTypeDTO> DTO_LIST(List<CoreInsuranceCourseType> coreInsuranceCourseTypes);
}
