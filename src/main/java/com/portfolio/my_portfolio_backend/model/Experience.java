package com.portfolio.my_portfolio_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Experience {
    private Long id;
    private String jobTitle;
    private String companyName;
    private LocalDate startDate;
    private LocalDate endDate; //puede ser null si esta en curso
    private String description; // Responsabilidades o logros
    private Long personalInfoId; //Clave foranea a PersonalInfo se valida en el servicio

}
