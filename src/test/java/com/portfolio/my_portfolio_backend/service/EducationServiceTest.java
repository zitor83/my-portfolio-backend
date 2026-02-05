package com.portfolio.my_portfolio_backend.service;


import com.portfolio.my_portfolio_backend.exception.ValidationException;
import com.portfolio.my_portfolio_backend.model.Education;

import com.portfolio.my_portfolio_backend.repository.IEducationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EducationServiceTest {
    @Autowired
    private IEducationService educationService;

    @Autowired
    private IEducationRepository educationRepository;

    @Test
    void testSaveValidEducation(){

        Education validEducation= new Education(null,"Ingenieria de sistemas","UMA", LocalDate.of(2020,2,12),LocalDate.of(2022,10,7),"Descripcion",1L );

        Education savedEducation= educationService.save(validEducation);

        assertNotNull(savedEducation.getId(), "El objeto guardado debe tener un ID asignado");

        assertNotNull(educationRepository
                        .findById(savedEducation.getId())
                        .orElse(null),
                "El objeto guardado debe estar en la base de datos");
    }

    @Test
    void testSaveInvalidEducation(){
        Education invalidEducation= new Education(null,"","UMA", LocalDate.of(2020,2,12),LocalDate.of(2022,10,7),"Descripcion",1L );

        assertThrows(ValidationException.class,() -> educationService.save(invalidEducation),
                "Debe lanzarse una excepcion de validacion cuando el nombre del titulo esta vacio");


    }
}
