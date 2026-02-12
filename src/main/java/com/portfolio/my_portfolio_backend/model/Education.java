package com.portfolio.my_portfolio_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Education {
    private Long id;
    private String degree; //Ej:" Ingenieria de sistemas"
    private String institution; // UMA
    private LocalDate startDate;
    private LocalDate endDate; //puede ser null si esta en curso
    private String description; // Breve descripcion de logros o cursos
    private Long personalInfoId; //Clave foranea a PersonalInfo que se valida en el servicio
}
