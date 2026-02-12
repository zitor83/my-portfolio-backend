package com.portfolio.my_portfolio_backend.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationDto {
    private Long id;

    @NotBlank(message = "El nombre del titulo no puede estar vacio")
    @Size(min = 2, max = 100, message = "El nombre del titulo debe tener entre 2 y 100 caracteres")
    private String degree; //Ej:" Ingenieria de sistemas"

    @NotBlank(message = "La institucion no puede estar vacia")
    @Size(min = 2, max = 50, message = "La institucion debe tener entre 2 y 50 caracteres")
    private String institution; // UMA


    @NotNull(message = "La fecha de inicio no puede ser nula")
    @PastOrPresent(message = "La fecha de inicio no puede ser una fecha futura")
    @DateTimeFormat(pattern = "yyyy-MM-dd")// Aplicamos formato a la fecha
    private LocalDate startDate;

    @PastOrPresent(message = "La fecha de fin no puede ser una fecha futura")
    @DateTimeFormat(pattern = "yyyy-MM-dd")// Aplicamos formato a la fecha
    private LocalDate endDate; //puede ser null si esta en curso

    @NotBlank(message = "La descripcion no puede estar vacia")
    @Size(min = 10, max = 200, message = "La descripcion debe tener entre 10 y 200 caracteres")
    private String description; // Breve descripcion de logros o cursos

    @NotNull(message = "El ID de información personal es obligatorio")
    @Min(value = 1, message = "El ID de información personal debe ser mayor a 0")
    private Long personalInfoId; //Clave foranea a PersonalInfo que se valida en el servicio
}
