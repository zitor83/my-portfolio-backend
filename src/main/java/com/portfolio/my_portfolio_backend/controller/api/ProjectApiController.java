package com.portfolio.my_portfolio_backend.controller.api;

import com.portfolio.my_portfolio_backend.dto.ProjectDto;
import com.portfolio.my_portfolio_backend.mapper.ProjectMapper;
import com.portfolio.my_portfolio_backend.model.Project;
import com.portfolio.my_portfolio_backend.service.IProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
@Tag(name = "2. Proyectos", description = "Endpoints públicos para consultar los proyectos del portfolio")
public class ProjectApiController {

    private final IProjectService projectService;

    @Operation(
            summary = "Listar todos los proyectos",
            description = "Devuelve la lista completa de proyectos en formato JSON."
    )
    @GetMapping
    public List<ProjectDto> getAllProjectsApi() {
        return projectService.findAll().stream()
                .map(ProjectMapper::toDto)
                .toList();
    }

    @Operation(
            summary = "Buscar proyecto por ID",
            description = "Busca y devuelve los detalles de un proyecto específico."
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long id) {
        Optional<Project> project = projectService.findById(id);
        return project.map(p -> ResponseEntity.ok(ProjectMapper.toDto(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Crear nuevo proyecto (Protegido)",
            description = "Añade un nuevo proyecto a la base de datos. Requiere token/sesión de administrador."
    )
    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto) {
        Project project = ProjectMapper.toEntity(projectDto);
        Project savedProject = projectService.save(project);
        return new ResponseEntity<>(ProjectMapper.toDto(savedProject), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Actualizar proyecto (Protegido)",
            description = "Modifica los datos de un proyecto existente. Requiere token/sesión de administrador."
    )
    @PutMapping("/{id}")
    public ResponseEntity<ProjectDto> updateProject(@PathVariable Long id, @RequestBody ProjectDto projectDto) {
        projectDto.setId(id); // Aseguramos que el ID del DTO es el de la ruta
        Project project = ProjectMapper.toEntity(projectDto);
        Project updatedProject = projectService.save(project);
        return ResponseEntity.ok(ProjectMapper.toDto(updatedProject));
    }

    @Operation(
            summary = "Eliminar proyecto (Protegido)",
            description = "Borra un proyecto de la base de datos por su ID. Requiere token/sesión de administrador."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}