package com.portfolio.my_portfolio_backend.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Education {
    private Long id;

    @NotBlank(message = "El nombre del titulo no puede estar vacio")
    private String degree; //Ej:" Ingenieria de sistemas"

    @NotBlank(message = "La institucion no puede estar vacia")
    private String institution; // UMA


    @NotNull(message = "La fecha de inicio no puede ser nula")
    @PastOrPresent(message= "La fecha de inicio no puede ser futura")
    private LocalDate startDate;

    @PastOrPresent(message= "La fecha de fin no puede ser futura")
    private LocalDate endDate; //puede ser null si esta en curso

    @NotBlank(message = "La descripcion no puede estar vacia")
    private String description; // Breve descripcion de logros o cursos

    private Long personalInfoId; //Clave foranea a PersonalInfo que se valida en el servicio
}
