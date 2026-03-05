package com.portfolio.my_portfolio_backend.controller.api;

import com.portfolio.my_portfolio_backend.model.Experience;
import com.portfolio.my_portfolio_backend.service.IExperienceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/experience")
@Tag(name = "3. Experiencia", description = "Endpoints públicos para consultar la trayectoria profesional")
public class ExperienceApiController {

    private final IExperienceService experienceService;

    public ExperienceApiController(IExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @Operation(
            summary = "Listar toda la experiencia",
            description = "Devuelve la lista completa de la experiencia laboral (empresas, cargos, fechas) en formato JSON."
    )
    @GetMapping()
    public List<Experience> findAll() {
        return experienceService.findAll();
    }

    @Operation(
            summary = "Buscar experiencia por ID",
            description = "Busca y devuelve un registro de experiencia laboral específico usando su identificador único."
    )
    @GetMapping("/{id}")
    public Optional<Experience> findById(@PathVariable Long id){
        return experienceService.findById(id);
    }

    @Operation(
            summary = "Crear nueva experiencia (Protegido)",
            description = "Añade un nuevo registro a la trayectoria profesional. Requiere token/sesión de administrador."
    )
    @PostMapping
    public Experience save(@RequestBody Experience experience){
        return experienceService.save(experience);
    }

    @Operation(
            summary = "Actualizar experiencia (Protegido)",
            description = "Modifica un registro de experiencia existente. Requiere token/sesión de administrador."
    )
    @PutMapping("/{id}")
    public Experience update(@PathVariable Long id, @RequestBody Experience experience){
        experience.setId(id);
        return experienceService.save(experience);
    }

    @Operation(
            summary = "Eliminar experiencia (Protegido)",
            description = "Borra un registro de experiencia de la base de datos por su ID. Requiere token/sesión de administrador."
    )
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        experienceService.deleteById(id);
    }
}