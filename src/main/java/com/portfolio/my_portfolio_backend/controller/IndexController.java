package com.portfolio.my_portfolio_backend.controller;

import com.portfolio.my_portfolio_backend.service.IEducationService;
import com.portfolio.my_portfolio_backend.service.IExperienceService;
import com.portfolio.my_portfolio_backend.service.IPersonalInfoService;
import com.portfolio.my_portfolio_backend.service.ISkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final IPersonalInfoService personalInfoService;
    private final IEducationService educationService;
    private final IExperienceService experienceService;
    private final ISkillService skillService;

    @GetMapping("/")
    public String showIndex(Model model) {
        System.out.println("Mostrando la página principal...");

        model.addAttribute("personalInfo",personalInfoService.findAll().getFirst());
        model.addAttribute("experienceList",experienceService.findAll());
        model.addAttribute("educationList",educationService.findAll());
        model.addAttribute("skills",skillService.findAll());


        return "index";
    }

}
