package com.portfolio.my_portfolio_backend.controller;

import com.portfolio.my_portfolio_backend.dto.EducationDto;
import com.portfolio.my_portfolio_backend.mapper.EducationMapper;
import com.portfolio.my_portfolio_backend.model.Education;
import com.portfolio.my_portfolio_backend.service.IEducationService;
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
@RequestMapping("/education")
@RequiredArgsConstructor
public class EducationController {

    private final IEducationService educationService;


    @GetMapping
    public String listEducation(Model model) {
        List<Education> educationList = educationService.findAll();

        List<EducationDto> educationListDto = educationList.stream()
                .map(EducationMapper::toDto).toList();
        model.addAttribute("educationList", educationListDto);
        return "education/list-education";

    }

    @GetMapping("/new")
    public String showCreateEducationForm(Model model) {
        //Aqui es conveniente poner la fecha actual en la fecha de inicio
        //para mejorar la experiencia del usuario y evitar errores en el formulario
        EducationDto newEducationDto = new EducationDto();
        newEducationDto.setStartDate(LocalDate.now());
        model.addAttribute("educationDto", newEducationDto);
        return "education/form-education";
    }

    @PostMapping("/save")
    public String saveEducation(@Valid @ModelAttribute("educationDto") EducationDto educationDto,
                                BindingResult result,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "education/form-education";
        }
        try {
            Education education = EducationMapper.toEntity(educationDto);
            educationService.save(education);
            return "redirect:/education";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al guardar la educacion: " + e.getMessage());
            return "redirect:/education";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditEducationForm(@PathVariable Long id, Model model) {
        Optional<Education> educationOptional = educationService.findById(id);
        if (educationOptional.isPresent()) {
            EducationDto educationDto = EducationMapper.toDto(educationOptional.get());
            model.addAttribute("educationDto", educationDto);
            return "education/form-education";
        } else {
            model.addAttribute("errorMessage", "Educacion no encontrada con ID: " + id);
            return "redirect:/education";
        }
    }


    @GetMapping("/personal/{personalInfoId}")
    public String listEducationsByPersonalInfoId(@PathVariable Long personalInfoId, Model model) {
        List<Education> educationList = educationService.findEducationsByPersonalInfoId(personalInfoId);
        List<EducationDto>educationsDto = educationList.stream()
                .map(EducationMapper::toDto)
                .collect(Collectors.toList());
        // Collect es la forma antigua de recoger los datos en una lista(mutable), tambien serviria usar .toList() directamente (inmutable)
        model.addAttribute("educationList", educationsDto);
        return "education/list-education";
    }

    @PostMapping("/delete/{id}")
    public String deleteEducation(@PathVariable Long id,
                                   RedirectAttributes redirectAttributes) {
        try {
            educationService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Educacion eliminada con éxito¡");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar la educacion: " + e.getMessage());
        }
        return "redirect:/education";
    }
}
