package com.portfolio.my_portfolio_backend.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill {
    private Long id;

    @NotBlank(message = "El nombre de la habilidad no puede estar vacio")
    private String name;

    @NotNull(message = "El nivel no puede ser nulo")
    @Min(value= 0, message = "El nivel no puede ser menor a 0")
    @Max(value = 100, message = "El nivel no puede ser mayor a 100")
    private Integer levelPercentage; //Ej:80,90(para barras de progreso)


    @NotBlank(message = "La clase de icono no puede estar vacia")
    private String iconClass; // Ej: "fab fa-java" para FontAwesome

    private Long personalInfoId; //Clave foranea a PersonalInfo
}
