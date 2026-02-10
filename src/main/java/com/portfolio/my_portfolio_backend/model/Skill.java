package com.portfolio.my_portfolio_backend.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill {
    private Long id;
    private String name;
    private Integer levelPercentage; //Ej:80,90(para barras de progreso)
    private String iconClass; // Ej: "fab fa-java" para FontAwesome
    private Long personalInfoId; //Clave foranea a PersonalInfo
}
