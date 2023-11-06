package com.haytech.demo_referralmanagement.model.mapper;

import com.haytech.demo_referralmanagement.model.dto.CompanyDTO;
import com.haytech.demo_referralmanagement.model.entity.Company;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    List<CompanyDTO> DTO_LIST(List<Company> companyList);

    CompanyDTO DTO_Company(Company company);
    Company  Company_DTO(CompanyDTO companyDTO);
}
