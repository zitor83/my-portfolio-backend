package com.portfolio.my_portfolio_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfo {
    private Long id; //Clave primaria


    private String firstName;
    private String lastName;
    private String title;  // Ej: Full Stack Developer
    private String profileDescription; //Texto largo del "Who am I"
    private String profileImageUrl; //URL o ruta a la imagen del perfil
    private Integer yearsOfExperience;
    private String email;
    private String phone;
    private String linkedinUrl;
    private String githubUrl;

}
