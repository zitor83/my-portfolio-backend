package com.portfolio.my_portfolio_backend.service;


import com.portfolio.my_portfolio_backend.exception.ValidationException;
import com.portfolio.my_portfolio_backend.model.Skill;
import com.portfolio.my_portfolio_backend.repository.ISkillRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest //Carga toda la aplicacion
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) //<-- Limpia la base de datos despues de cada test

public class SkillServiceTest {
    @Autowired
    private ISkillService skillService;   //Inyecta el servicio real
    @Autowired
    private ISkillRepository skillRepository;

    @Test  //Dice que es un test
    void testSaveValidSkill(){
        //A. PREPARAR: Crear un objeto valido como dato de prueba
        Skill validSkill= new Skill(null,"Java",90,"fab fa-java",1L);

        //B. ACTUAR: Llamar al metodo a probar
        Skill savedSkill= skillService.save(validSkill);

        //C. VERIFICAR: Comprobar que pasó lo esperado
        assertNotNull(savedSkill.getId(), "El objeto guardado debe tener un ID asignado");

        assertNotNull(skillRepository
                        .findById(savedSkill.getId())
                        .orElse(null),
                "El objeto guardado debe estar en la base de datos");
    }

    @Test
    void testSaveInvalidSkill(){
        //A. PREPARAR: Crear un objeto invalido como dato de prueba
        Skill invalidSkill= new Skill(null,"",90,"fab fa-java",1L);

        //B. ACTUAR: Llamar al metodo a probar
        assertThrows(ValidationException.class,() -> skillService.save(invalidSkill),
                "Debe lanzarse una excepcion de validacion cuando el nombre de la habilidad esta vacio");


    }
}
