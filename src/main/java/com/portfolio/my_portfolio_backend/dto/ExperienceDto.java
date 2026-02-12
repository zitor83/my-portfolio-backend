package com.portfolio.my_portfolio_backend.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceDto {
    private Long id;

    @NotBlank(message = "El titulo del puesto no puede estar vacio")
    @Size(min = 2, max = 100, message = "El titulo del puesto debe tener entre 2 y 100 caracteres")
    private String jobTitle;

    @NotBlank(message = "La empresa no puede estar vacia")
    @Size(min = 2, max = 50, message = "La empresa debe tener entre 2 y 50 caracteres")
    private String companyName;

    @NotNull(message = "La fecha de inicio no puede ser nula")
    @PastOrPresent(message = "La fecha de inicio no puede ser futura")
    private LocalDate startDate;

    @PastOrPresent(message = "La fecha de finalización no puede ser futura")
    private LocalDate endDate; //puede ser null si esta en curso

    @NotBlank(message = "La descripcion no puede estar vacia")
    @Size(min = 10, max = 200, message = "La descripcion debe tener entre 10 y 200 caracteres")
    private String description; // Responsabilidades o logros

    @NotNull(message = "El ID de informacion personal no puede ser nulo")
    @Min(value = 1, message = "El ID de informacion personal debe ser mayor a 0")
    private Long personalInfoId; //Clave foranea a PersonalInfo se valida en el servicio

}
