package com.portfolio.my_portfolio_backend.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfoDto {
    private Long id; //Clave primaria

    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String firstName;

    @NotBlank(message = "El apellido no puede estar vacio")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    private String lastName;

    @NotBlank(message = "El titulo no puede estar vacio")
    @Size(min = 2, max = 100, message = "El titulo debe tener entre 2 y 100 caracteres")
    private String title;  // Ej: Full Stack Developer

    @NotBlank(message = "La descripcion no puede estar vacia")
    @Size(min = 10,max = 200, message = "La descripcion debe tener entre 10 y 200 caracteres")
    private String profileDescription; //Texto largo del "Who am I"

    @NotBlank(message = "La imagen no puede estar vacia")
    private String profileImageUrl; //URL o ruta a la imagen del perfil

    @Min(value = 0, message = "Los años de experiencia no pueden ser negativos")
    private Integer yearsOfExperience;

    @Email(message = "El email no es valido")
    private String email;

    @NotBlank(message = "El telefono no puede estar vacio")
    private String phone;

    @URL(message = "Linkedin URL es una red obligatoria")
    private String linkedinUrl;

    @URL(message = "Github URL es una red obligatoria")
    private String githubUrl;

}
