package com.portfolio.my_portfolio_backend.controller.api;

import com.portfolio.my_portfolio_backend.model.PersonalInfo;
import com.portfolio.my_portfolio_backend.service.IPersonalInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personal-info") // Ruta limpia, sin "test"
@Tag(name = "1. Perfil Personal", description = "Endpoints públicos para consultar la información principal del desarrollador")
public class PersonalInfoApiController {

    private final IPersonalInfoService personalInfoService;

    public PersonalInfoApiController(IPersonalInfoService personalInfoService) {
        this.personalInfoService = personalInfoService;
    }

    @Operation(
            summary = "Listar todos los perfiles",
            description = "Devuelve una lista con toda la información personal almacenada."
    )
    @GetMapping // Estandarizado: El GET a la ruta base devuelve la lista completa
    public List<PersonalInfo> getAllPersonalInfo() {
        return personalInfoService.findAll();
    }

    @Operation(
            summary = "Buscar perfil por ID",
            description = "Busca y devuelve los datos personales (nombre, descripción, enlaces) asociados a un ID específico."
    )
    @GetMapping("/{id}")
    public PersonalInfo getPersonalInfoById(@PathVariable Long id) {
        Optional<PersonalInfo> info = personalInfoService.findById(id);
        if (info.isPresent()) {
            return info.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Información personal no disponible en el ID: " + id);
        }
    }

    @Operation(
            summary = "Crear perfil personal (Protegido)",
            description = "Añade un nuevo perfil con información personal a la base de datos. Requiere token/sesión de administrador."
    )
    @PostMapping
    public ResponseEntity<PersonalInfo> createPersonalInfo(@RequestBody PersonalInfo personalInfo){
        PersonalInfo newPersonalInfo = personalInfoService.save(personalInfo);
        return new ResponseEntity<>(newPersonalInfo, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Actualizar perfil personal (Protegido)",
            description = "Modifica los datos personales de un perfil existente. Requiere token/sesión de administrador."
    )
    @PutMapping("/{id}")
    public PersonalInfo update (@PathVariable Long id, @RequestBody PersonalInfo personalInfo){
        personalInfo.setId(id);
        return personalInfoService.save(personalInfo);
    }

    @Operation(
            summary = "Eliminar perfil personal (Protegido)",
            description = "Borra un perfil de información personal por su ID. Requiere token/sesión de administrador."
    )
    @DeleteMapping("/{id}")
    public void deleteById (@PathVariable Long id){
        personalInfoService.deleteById(id);
    }
}