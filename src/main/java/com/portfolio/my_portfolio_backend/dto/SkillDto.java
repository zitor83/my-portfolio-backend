package com.portfolio.my_portfolio_backend.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillDto {
    private Long id;

    @NotBlank(message = "El nombre de la habilidad no puede estar vacio")
    @Size(min = 2, max = 50, message = "El nombre de la habilidad debe tener entre 2 y 50 caracteres")
    private String name;

    @NotNull(message = "El nivel no puede ser nulo")
    @Min(value= 0, message = "El nivel no puede ser menor a 0")
    @Max(value = 100, message = "El nivel no puede ser mayor a 100")
    private Integer levelPercentage; //Ej:80,90(para barras de progreso)


    @NotBlank(message = "La clase de icono no puede estar vacia")
    private String iconClass; // Ej: "fab fa-java" para FontAwesome

    @NotNull(message = "El ID de información personal es obligatorio")
    private Long personalInfoId; //Clave foranea a PersonalInfo
}