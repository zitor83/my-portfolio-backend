package com.portfolio.my_portfolio_backend.controller.api;

import com.portfolio.my_portfolio_backend.model.Skill;
import com.portfolio.my_portfolio_backend.service.ISkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/skills") // Ruta pluralizada para cumplir el estándar REST
@Tag(name = "5. Habilidades", description = "Endpoints públicos para consultar las habilidades técnicas y blandas")
public class SkillApiController {

    private final ISkillService skillService;

    public SkillApiController(ISkillService skillService) {
        this.skillService = skillService;
    }

    @Operation(
            summary = "Listar todas las habilidades",
            description = "Devuelve la lista completa de habilidades (skills) registradas en el portfolio."
    )
    @GetMapping()
    public List<Skill> findAll() {
        return skillService.findAll();
    }

    @Operation(
            summary = "Buscar habilidad por ID",
            description = "Busca y devuelve una habilidad específica usando su identificador único."
    )
    @GetMapping("/{id}")
    public Optional<Skill> findById(@PathVariable Long id) {
        return skillService.findById(id);
    }

    @Operation(
            summary = "Buscar habilidades por ID de Perfil",
            description = "Devuelve todas las habilidades asociadas a un identificador de información personal específico."
    )
    @GetMapping("/personal-info/{id}")
    public List<Skill> findSkillsByPersonalInfoId(@PathVariable("id") Long personalInfoId) {
        return skillService.findSkillsByPersonalInfoId(personalInfoId);
    }

    @Operation(
            summary = "Crear nueva habilidad (Protegido)",
            description = "Añade una nueva habilidad tecnológica o blanda a la base de datos. Requiere token/sesión de administrador."
    )
    @PostMapping
    public Skill save(@RequestBody Skill skill) {
        return skillService.save(skill);
    }

    @Operation(
            summary = "Actualizar habilidad (Protegido)",
            description = "Modifica los datos de una habilidad existente. Requiere token/sesión de administrador."
    )
    @PutMapping("/{id}")
    public Skill update(@PathVariable Long id, @RequestBody Skill skill) {
        skill.setId(id);
        return skillService.save(skill);
    }

    @Operation(
            summary = "Eliminar habilidad (Protegido)",
            description = "Borra una habilidad de la base de datos por su ID. Requiere token/sesión de administrador."
    )
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        skillService.deleteById(id);
    }
}