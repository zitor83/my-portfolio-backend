package com.portfolio.my_portfolio_backend.service;


import com.portfolio.my_portfolio_backend.exception.ValidationException;
import com.portfolio.my_portfolio_backend.model.PersonalInfo;
import com.portfolio.my_portfolio_backend.repository.IPersonalInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;



import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PersonalInfoServiceTest {
    @Autowired
    private IPersonalInfoService personalInfoService;

    @Autowired
    private IPersonalInfoRepository personalInfoRepository;

    @Test
    void testSaveValidPersonalInfo(){

        PersonalInfo validPersonalInfo= new PersonalInfo(null,"Jose","Ortiz","Junior",
                "Profile description","Url de imagen",1,
                "inventado@gmail.com","666555444","http://linkedin.com","http://github.com");

        PersonalInfo savedPersonalInfo= personalInfoService.save(validPersonalInfo);

        assertNotNull(savedPersonalInfo.getId(), "El objeto guardado debe tener un ID asignado");

        assertNotNull(personalInfoRepository
                        .findById(savedPersonalInfo.getId())
                        .orElse(null),
                "El objeto guardado debe estar en la base de datos");
    }

    @Test
    void testSaveInvalidPersonalInfo(){
        PersonalInfo invalidPersonalInfo= new PersonalInfo(null,"","Ortiz","Junior",
                "Profile description","Url de imagen",1,
                "inventado@gmail.com","666555444","linkedin.com","github.com");

        assertThrows(ValidationException.class,() -> personalInfoService.save(invalidPersonalInfo),
                "Debe lanzarse una excepcion de validacion cuando el nombre esta vacio");


    }
}
