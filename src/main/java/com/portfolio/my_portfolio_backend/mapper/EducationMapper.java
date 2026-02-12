package com.portfolio.my_portfolio_backend.mapper;

import com.portfolio.my_portfolio_backend.dto.EducationDto;
import com.portfolio.my_portfolio_backend.model.Education;

public class EducationMapper {

    public static EducationDto toDto(Education entity){
        if(entity == null){
            return null;
        }
        EducationDto educationDto = new EducationDto();

        educationDto.setId(entity.getId());
        educationDto.setDegree(entity.getDegree());
        educationDto.setInstitution(entity.getInstitution());
        educationDto.setStartDate(entity.getStartDate());
        educationDto.setEndDate(entity.getEndDate());
        educationDto.setDescription(entity.getDescription());
        educationDto.setPersonalInfoId(entity.getPersonalInfoId());

        return educationDto;
    }

    public static Education toEntity(EducationDto dto){
        if(dto == null){
            return null;
        }
        Education entity = new Education();
        entity.setId(dto.getId());
        entity.setDegree(dto.getDegree());
        entity.setInstitution(dto.getInstitution());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setDescription(dto.getDescription());
        entity.setPersonalInfoId(dto.getPersonalInfoId());

        return entity;
    }
}
