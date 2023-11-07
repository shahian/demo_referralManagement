package com.haytech.demo_referralmanagement.service.impl;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.base.MetaDTO;
import com.haytech.demo_referralmanagement.model.dto.AgencyDTO;
import com.haytech.demo_referralmanagement.model.entity.Agency;
import com.haytech.demo_referralmanagement.model.mapper.AgencyMapper;
import com.haytech.demo_referralmanagement.repository.AgencyRepository;
import com.haytech.demo_referralmanagement.service.intrface.AgencyService;
import com.haytech.demo_referralmanagement.utility.ApplicationProperties;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class AgencyServiceImpl implements AgencyService {

    private final AgencyRepository agencyRepository;
    private final ApplicationProperties applicationProperties;
    private final AgencyMapper agencyMapper;

    public AgencyServiceImpl(AgencyRepository agencyRepository, ApplicationProperties applicationProperties, AgencyMapper agencyMapper) {
        this.agencyRepository = agencyRepository;
        this.applicationProperties = applicationProperties;
        this.agencyMapper = agencyMapper;
    }
    @Override
    public BaseDTO getAgencyById(Long agencyId) {
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), agencyMapper.DTO_AgencyDTO(agencyRepository.getById(agencyId)));
    }
    @Override
    public BaseDTO getAllAgencies() {
        List<Agency> result = agencyRepository.findAll();
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), agencyMapper.DTO_LIST(result));
    }
    @Override
    public BaseDTO createAgency(AgencyDTO agencyDTO) {
        Agency agency = agencyMapper.Agency_DTO(agencyDTO); // Map DTO to Entity
        agency = agencyRepository.save(agency); // Save the entity
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), agencyMapper.DTO_AgencyDTO(agency)); // Map Entity back to DTO
    }

    @Override
    public BaseDTO updateAgency(Long agencyId, AgencyDTO updatedAgency) {
        Agency existingAgency = agencyRepository.findById(agencyId)
                .orElseThrow(() -> new EntityNotFoundException("Agency not found"));
        existingAgency.setCode(updatedAgency.getCode());
        existingAgency.setName(updatedAgency.getName());
        agencyRepository.save(existingAgency);
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), agencyMapper.DTO_AgencyDTO(existingAgency));
    }
    @Override
    public BaseDTO deleteAgency(Long agencyId) {
        Agency agency = agencyRepository.findById(agencyId)
                .orElseThrow(() -> new EntityNotFoundException("Agency not found"));
        agency.setDeleted(true);
        agencyRepository.save(agency);
        return new BaseDTO(MetaDTO.getInstance(applicationProperties), agency);
    }
}