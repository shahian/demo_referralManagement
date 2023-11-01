package com.haytech.demo_referralmanagement.model.mapper;

import com.haytech.demo_referralmanagement.model.dto.InsuranceCourseTypeDTO;
import com.haytech.demo_referralmanagement.model.entity.InsuranceCourseType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InsuranceCourseTypeMapper {
    List<InsuranceCourseTypeDTO> DTO_LIST(List<InsuranceCourseType> insuranceCourseTypeList);

    InsuranceCourseTypeDTO DTO_Course(InsuranceCourseType insuranceCourseType);
}
