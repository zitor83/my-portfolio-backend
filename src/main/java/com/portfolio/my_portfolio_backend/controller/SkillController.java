package com.portfolio.my_portfolio_backend.controller;

import com.portfolio.my_portfolio_backend.dto.SkillDto;
import com.portfolio.my_portfolio_backend.dto.SkillMapper;
import com.portfolio.my_portfolio_backend.model.Skill;
import com.portfolio.my_portfolio_backend.service.ISkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/skills")
@RequiredArgsConstructor
public class SkillController {

    private final ISkillService skillService;

    @GetMapping
    public String listSkills(Model model){
        List<Skill> skills = skillService.findAll();

        List<SkillDto> skillsDto = skills.stream()
                .map(SkillMapper::toDto).toList();
        model.addAttribute("skills", skillsDto);
        return "skills/list-skills";
    }
    @GetMapping("/new")
    public String showCreateForm(Model model){
        model.addAttribute("skillDto", new SkillDto());
        return "skills/form-skill";
    }
}
