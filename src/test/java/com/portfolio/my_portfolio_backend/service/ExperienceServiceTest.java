package com.portfolio.my_portfolio_backend.service;


import com.portfolio.my_portfolio_backend.exception.ValidationException;
import com.portfolio.my_portfolio_backend.model.Experience;

import com.portfolio.my_portfolio_backend.repository.IExperienceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ExperienceServiceTest {
    @Autowired
    private IExperienceService experienceService;

    @Autowired
    private IExperienceRepository  experienceRepository;

    @Test
    void testSaveValidExperience(){

        Experience validExperience= new Experience(null,"Ingenieria de sistemas","UMA", LocalDate.of(2020,2,12),LocalDate.of(2022,10,7),"Descripcion",1L );

        Experience savedExperience= experienceService.save(validExperience);

        assertNotNull(savedExperience.getId(), "El objeto guardado debe tener un ID asignado");

        assertNotNull(experienceRepository
                        .findById(savedExperience.getId())
                        .orElse(null),
                "El objeto guardado debe estar en la base de datos");
    }

    @Test
    void testSaveInvalidExperience(){
        Experience invalidExperience= new Experience(null,"","UMA", LocalDate.of(2020,2,12),LocalDate.of(2022,10,7),"Descripcion",1L );

        assertThrows(ValidationException.class,() -> experienceService.save(invalidExperience),
                "Debe lanzarse una excepcion de validacion cuando el nombre del titulo esta vacio");


    }
}
