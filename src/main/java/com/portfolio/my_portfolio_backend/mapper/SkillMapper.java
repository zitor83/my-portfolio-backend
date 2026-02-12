package com.portfolio.my_portfolio_backend.mapper;

import com.portfolio.my_portfolio_backend.dto.SkillDto;
import com.portfolio.my_portfolio_backend.model.Skill;

public class SkillMapper {

    public static SkillDto toDto(Skill entity) {
        if(entity == null){
            return null;
        }
        SkillDto skillDto = new SkillDto();

        skillDto.setId(entity.getId());
        skillDto.setName(entity.getName());
        skillDto.setLevelPercentage(entity.getLevelPercentage());
        skillDto.setIconClass(entity.getIconClass());
        skillDto.setPersonalInfoId(entity.getPersonalInfoId());

        return skillDto;
    }
    public static Skill toEntity(SkillDto dto){
        if(dto == null){
            return null;


        }
        Skill entity = new Skill();


        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setLevelPercentage(dto.getLevelPercentage());
        entity.setIconClass(dto.getIconClass());
        entity.setPersonalInfoId(dto.getPersonalInfoId());

        return entity;
    }
}
