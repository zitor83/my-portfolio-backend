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
        // 1. ¿Es un proyecto nuevo? (ID nulo o 0)
        boolean isNewProject = (projectDto.getId() == null || projectDto.getId() == 0);

        // 2. Si es nuevo, la imagen es estrictamente obligatoria.
        if (isNewProject && file.isEmpty()) {
            result.rejectValue("imageUrl", "file.required", "La imagen es obligatoria para proyectos nuevos");
        }

        // 3. Si hay errores (de texto o por falta de foto en uno nuevo), volvemos al formulario.
        if (result.hasErrors()) {
            return "projects/form-project";
        }

        try {
            // 4. Si el usuario subió un archivo (sea nuevo o editando), lo guardamos.
            if (!file.isEmpty()) {
                String imageUrl = fileStorageService.storeFile(file);
                projectDto.setImageUrl(imageUrl);
            } else {
                // 5. Si está editando y NO subió foto, rescatamos la foto antigua de la Base de Datos.
                Optional<Project> existingProject = projectService.findById(projectDto.getId());
                existingProject.ifPresent(p -> projectDto.setImageUrl(p.getImageUrl()));
            }

            // 6. Guardamos y volvemos a la lista.
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
            // 1. Buscamos el proyecto para saber qué imagen tiene asociada
            Optional<Project> projectOptional = projectService.findById(id);

            if (projectOptional.isPresent()) {
                String imageUrl = projectOptional.get().getImageUrl();

                // 2. Borramos el registro de la base de datos
                projectService.deleteById(id);

                // 3. Borramos el archivo físico del disco duro
                if (imageUrl != null && !imageUrl.isEmpty()) {
                    fileStorageService.deleteFile(imageUrl); // <-- Llamada al nuevo método
                }

                redirectAttributes.addFlashAttribute("message", "Proyecto e imagen eliminados con éxito.");
            } else {
                redirectAttributes.addFlashAttribute("error", "El proyecto no existe.");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el proyecto.");
        }
        return "redirect:/projects";
    }


}
