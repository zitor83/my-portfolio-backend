package com.portfolio.my_portfolio_backend.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor //@
public class Experience {
    private Long id;

    @NotBlank(message = "El titulo del puesto no puede estar vacio")
    private String jobTitle;

    @NotBlank(message = "La empresa no puede estar vacia")
    private String companyName;

    @NotNull(message ="La fecha de inicio no puede ser nula")
    @PastOrPresent(message= "La fecha de inicio no puede ser futura")
    private LocalDate startDate;

    @PastOrPresent(message= "La fecha de fin no puede ser futura")
    private LocalDate endDate; //puede ser null si esta en curso

    @NotBlank(message = "La descripcion no puede estar vacia")
    private String description; // Responsabilidades o logros


    private Long personalInfoId; //Clave foranea a PersonalInfo se valida en el servicio

}
