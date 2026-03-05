package com.portfolio.my_portfolio_backend.controller.api;

import com.portfolio.my_portfolio_backend.model.Education;
import com.portfolio.my_portfolio_backend.service.IEducationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/education")
@Tag(name = "4. Educación", description = "Endpoints públicos para consultar la formación académica")
public class EducationApiController {

    private final IEducationService educationService;

    public EducationApiController(IEducationService educationService) {
        this.educationService = educationService;
    }

    @Operation(
            summary = "Listar toda la educación",
            description = "Devuelve la lista completa del historial académico y cursos en formato JSON."
    )
    @GetMapping()
    public List<Education> findAll() {
        return educationService.findAll();
    }

    @Operation(
            summary = "Buscar educación por ID",
            description = "Busca y devuelve una formación académica específica usando su identificador único."
    )
    @GetMapping("/{id}")
    public Optional<Education> findById(@PathVariable Long id){
        return educationService.findById(id);
    }

    @Operation(
            summary = "Crear nueva educación (Protegido)",
            description = "Añade un nuevo registro académico a la base de datos. Requiere token/sesión de administrador."
    )
    @PostMapping
    public Education save(@RequestBody Education education){
        return educationService.save(education);
    }

    @Operation(
            summary = "Actualizar educación (Protegido)",
            description = "Modifica un registro académico existente. Requiere token/sesión de administrador."
    )
    @PutMapping("/{id}")
    public Education update(@PathVariable Long id, @RequestBody Education education){
        education.setId(id);
        return educationService.save(education);
    }

    @Operation(
            summary = "Eliminar educación (Protegido)",
            description = "Borra un registro académico de la base de datos por su ID. Requiere token/sesión de administrador."
    )
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        educationService.deleteById(id);
    }
}