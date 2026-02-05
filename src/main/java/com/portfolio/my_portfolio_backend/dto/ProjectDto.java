package com.portfolio.my_portfolio_backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProjectDto {
    private Long id;

    @NotBlank(message = "El titulo no puede estar vacio")
    @Size(min = 2, max = 100, message = "El titulo debe tener entre 2 y 100 caracteres")
    private String title;

    @NotBlank(message = "La descripcion no puede estar vacia")
    @Size(min=10, message = "La descripcion debe tener al menos 10 caracteres")
    private String description;

    @URL(message = "La URL de la imagen no es valida")
    private String imageUrl; //URL o ruta a la imagen del proyecto

    @URL(message = "La URL del proyecto no es valida")
    private String projectUrl; //Link al proyecto desplegado

    @NotNull(message = "El ID de informacion personal no puede ser nulo")
    @Min(value = 1, message = "El ID de informacion personal debe ser mayor a 0")
    private Long personalInfoId; //Clave foranea a PersonalInfo
}
