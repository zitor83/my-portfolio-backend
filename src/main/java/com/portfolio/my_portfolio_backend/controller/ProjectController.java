package com.portfolio.my_portfolio_backend.controller;

import com.portfolio.my_portfolio_backend.dto.ProjectDto;
import com.portfolio.my_portfolio_backend.dto.ProjectMapper;
import com.portfolio.my_portfolio_backend.model.Project;
import com.portfolio.my_portfolio_backend.service.IProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final IProjectService projectService;

    @GetMapping
    public String getAll(Model model) {
        List<ProjectDto> projects = projectService.findAll().stream()
                .map(ProjectMapper::toDto)
                .toList();
        model.addAttribute("projects", projects);
        return "projects/list";
    }
    @PostMapping("/save")
    public String saveProject(@Valid @ModelAttribute("project") ProjectDto projectDto) {
        Project project = ProjectMapper.toEntity(projectDto);
        projectService.save(project);
        return "redirect:/projects";
    }



}
