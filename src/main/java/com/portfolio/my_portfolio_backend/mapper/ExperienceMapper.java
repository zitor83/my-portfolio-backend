package com.portfolio.my_portfolio_backend.mapper;

import com.portfolio.my_portfolio_backend.dto.ExperienceDto;
import com.portfolio.my_portfolio_backend.model.Experience;

public class ExperienceMapper {

    public static ExperienceDto toDto(Experience entity){
        if(entity == null){
            return null;
        }
        ExperienceDto experienceDto = new ExperienceDto();

        experienceDto.setId(entity.getId());
        experienceDto.setJobTitle(entity.getJobTitle());
        experienceDto.setCompanyName(entity.getCompanyName());
        experienceDto.setStartDate(entity.getStartDate());
        experienceDto.setEndDate(entity.getEndDate());
        experienceDto.setDescription(entity.getDescription());
        experienceDto.setPersonalInfoId(entity.getPersonalInfoId());

        return experienceDto;
    }

    public static Experience toEntity(ExperienceDto dto){
        if(dto == null){
            return null;
        }
        Experience entity = new Experience();

        entity.setId(dto.getId());
        entity.setJobTitle(dto.getJobTitle());
        entity.setCompanyName(dto.getCompanyName());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setDescription(dto.getDescription());
        entity.setPersonalInfoId(dto.getPersonalInfoId());

        return entity;
    }
}
