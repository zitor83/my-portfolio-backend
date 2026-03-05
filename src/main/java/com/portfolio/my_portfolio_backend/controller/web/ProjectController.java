package com.portfolio.my_portfolio_backend.controller.web;

import com.portfolio.my_portfolio_backend.dto.ProjectDto;
import com.portfolio.my_portfolio_backend.mapper.ProjectMapper;
import com.portfolio.my_portfolio_backend.model.Project;
import com.portfolio.my_portfolio_backend.service.FileStorageService;
import com.portfolio.my_portfolio_backend.service.IProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final IProjectService projectService;
    private final FileStorageService fileStorageService;

    @GetMapping
    public String getAll(Model model) {
        List<ProjectDto> projects = projectService.findAll().stream()
                .map(ProjectMapper::toDto)
                .toList();
        model.addAttribute("projects", projects);
        return "projects/list-projects";
    }

    @GetMapping("/new-project")
    public String showForm(Model model) {
        model.addAttribute("projectDto", new ProjectDto());
        return "projects/form-project";
    }


    @PostMapping("/save")
    public String saveProject(@Valid @ModelAttribute("projectDto") ProjectDto projectDto, BindingResult result,
                              @RequestParam("file") MultipartFile file
    ) {
        if(file.isEmpty()){
            result.rejectValue("imageUrl","file.required","La imagen es obligatoria");
        }

        if(result.hasErrors()){
            return "projects/form-project";
        }
        try {
            String imageUrl = fileStorageService.storeFile(file);

            projectDto.setImageUrl(imageUrl);

            Project project = ProjectMapper.toEntity(projectDto);

            projectService.save(project);

            return "redirect:/projects";
        } catch (IOException e) {
            return "error-page";
        }
    }
    @GetMapping("/edit/{id}")
    public String editProject(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Project> projectOptional = projectService.findById(id);

        if (projectOptional.isPresent()) {
            ProjectDto projectDto = ProjectMapper.toDto(projectOptional.get());
            model.addAttribute("projectDto", projectDto);
            return "projects/form-project"; // Reutilizamos el formulario
        } else {
            redirectAttributes.addFlashAttribute("error", "El proyecto con ID " + id + " no existe.");
            return "redirect:/projects";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteProject(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            projectService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Proyecto eliminado con éxito.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el proyecto.");
        }
        return "redirect:/projects";
    }


}
