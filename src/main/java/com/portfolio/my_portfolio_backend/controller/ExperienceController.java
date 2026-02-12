package com.portfolio.my_portfolio_backend.controller;

import com.portfolio.my_portfolio_backend.dto.ExperienceDto;
import com.portfolio.my_portfolio_backend.dto.SkillDto;
import com.portfolio.my_portfolio_backend.mapper.ExperienceMapper;
import com.portfolio.my_portfolio_backend.mapper.SkillMapper;
import com.portfolio.my_portfolio_backend.model.Experience;
import com.portfolio.my_portfolio_backend.model.Skill;
import com.portfolio.my_portfolio_backend.repository.IExperienceRepository;
import com.portfolio.my_portfolio_backend.service.IExperienceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/experience")
@RequiredArgsConstructor
public class ExperienceController {

    private final IExperienceService experienceService;

    @GetMapping
    public String listExperience(Model model) {
        List<Experience> experienceList = experienceService.findAll();

        List<ExperienceDto> experienceListDto = experienceList.stream()
                .map(ExperienceMapper::toDto).toList();
        model.addAttribute("experienceList", experienceListDto);
        return "experience/list-experience";

    }

    @GetMapping("/new")
    public String showCreateExperienceForm(Model model) {
        //Aqui es conveniente poner la fecha actual en la fecha de inicio
        //para mejorar la experiencia del usuario y evitar errores en el formulario
        ExperienceDto newExperienceDto = new ExperienceDto();
        newExperienceDto.setStartDate(LocalDate.now());
        model.addAttribute("experienceDto", newExperienceDto);
        return "experience/form-experience";
    }

    @PostMapping("/save")
    public String saveExperience(@Valid @ModelAttribute("experienceDto") ExperienceDto experienceDto, BindingResult result,
                                 RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "experience/form-experience";
        }
        try {
            Experience experience = ExperienceMapper.toEntity(experienceDto);
            experienceService.save(experience);
            return "redirect:/experience";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al guardar la experiencia: " + e.getMessage());
            return "redirect:/experience";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditExperienceForm(@PathVariable Long id, Model model) {
        Optional<Experience> experienceOptional = experienceService.findById(id);
        if (experienceOptional.isPresent()) {
            ExperienceDto experienceDto = ExperienceMapper.toDto(experienceOptional.get());
            model.addAttribute("experienceDto", experienceDto);
            return "experience/form-experience";
        } else {
            model.addAttribute("errorMessage", "Experiencia no encontrada con ID: " + id);
            return "redirect:/experience";
        }
    }


    @GetMapping("/personal/{personalInfoId}")
    public String listExperiencesByPersonalInfoId(@PathVariable Long personalInfoId, Model model) {
        List<Experience> experienceList = experienceService.findExperienceByPersonalInfoId(personalInfoId);
        List<ExperienceDto> experiencesDto = experienceList.stream()
                .map(ExperienceMapper::toDto)
                .collect(Collectors.toList());
        // Collect es la forma antigua de recoger los datos en una lista(mutable), tambien serviria usar .toList() directamente (inmutable)
        model.addAttribute("experienceList", experiencesDto);
        return "experience/list-experience";
    }

    @PostMapping("/delete/{id}")
    public String deleteExperience(@PathVariable Long id,
                                   RedirectAttributes redirectAttributes) {
        try {
            experienceService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Experiencia eliminada con éxito¡");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar la experiencia: " + e.getMessage());
        }
        return "redirect:/experience";
    }
}