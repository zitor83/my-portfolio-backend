package com.portfolio.my_portfolio_backend.service;


import com.portfolio.my_portfolio_backend.exception.ValidationException;
import com.portfolio.my_portfolio_backend.model.Education;
import com.portfolio.my_portfolio_backend.repository.IEducationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EducationServiceImplTest {
    //
    @Mock
    private IEducationRepository educationRepository;

    @Mock
    private Validator validator;


    @InjectMocks
    private EducationServiceImpl educationService;


    @Test
    void testFindAllReturnsListOfEducations() {
        //Arrange
        List<Education> mockEducations = Arrays.asList(new Education(), new Education());
        when(educationRepository.findAll()).thenReturn(mockEducations);

        //Act
        List<Education> result = educationService.findAll();
        //Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(educationRepository, times(1)).findAll();

    }

    @Test
    void testFindByIdReturnsEducationWhenFound() {
        //Arrange
        Long Id = 1L;
        Education mockEducation = new Education();
        when(educationRepository.findById(Id)).thenReturn(Optional.of(mockEducation));

        //Act
        Optional<Education> result = educationService.findById(Id);

        //Assert
        assertTrue(result.isPresent());
        assertEquals(mockEducation, result.get());
        verify(educationRepository, times(1)).findById(Id);

    }

    @Test
    void testSaveEducationThrowsExceptionWhenInvalid() {
        //Arrange
        Education invalidEducation = new Education();

        doAnswer(invocationOnMock -> {
            BindingResult result = invocationOnMock.getArgument(1);
            result.rejectValue("degree", "NotBlank", "El titulo no puede estar vacio");
            return null;
        }).when(validator).validate(any(Education.class), any(BindingResult.class));

        //Act y Assert
        assertThrows(ValidationException.class, () -> educationService.save(invalidEducation),
                "Debe lanzarse una excepcion de validacion cuando el nombre del titulo esta vacio");
        verify(educationRepository, never()).save(any(Education.class));


    }

    @Test
    void testSaveEducationSavesValidEducation() {
        //Arrange--Preparación
        Education validEducation = new Education(null, "Ingenieria de sistemas", "UMA", LocalDate.of(2020, 2, 12), LocalDate.of(
                2022, 10, 7), "Descripcion", 1L);
        when(educationRepository.save(any(Education.class))).thenReturn(validEducation);
        doNothing().when(validator).validate(any(Education.class), any(BindingResult.class));

        //Act--Acción
        Education savedEducation = educationService.save(validEducation);

        //Assert--Verificación
        assertNotNull(savedEducation);
        verify(educationRepository, times(1)).save(validEducation);


    }


}