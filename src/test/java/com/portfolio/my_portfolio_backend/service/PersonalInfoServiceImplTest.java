package com.portfolio.my_portfolio_backend.service;

import com.portfolio.my_portfolio_backend.exception.ValidationException;
import com.portfolio.my_portfolio_backend.model.PersonalInfo;
import com.portfolio.my_portfolio_backend.repository.IPersonalInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class PersonalInfoServiceImplTest {

    @Mock
    private IPersonalInfoRepository personalInfoRepository;

    @Mock
    private Validator validator;

    @InjectMocks
    private PersonalInfoServiceImpl personalInfoService;

    @Test
    void testFindAllReturnsListOfPersonalInfo() {
        //Arrange
        List<PersonalInfo> mockList = Arrays.asList(new PersonalInfo(), new PersonalInfo());
        when(personalInfoRepository.findAll()).thenReturn(mockList);

        //Act
        List<PersonalInfo> result = personalInfoService.findAll();

        //Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(personalInfoRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdReturnsPersonalInfoWhenFound() {
        //Arrange
        Long id = 1L;
        PersonalInfo mockInfo = new PersonalInfo();
        when(personalInfoRepository.findById(id)).thenReturn(Optional.of(mockInfo));

        //Act
        Optional<PersonalInfo> result = personalInfoService.findById(id);

        //Assert
        assertTrue(result.isPresent());
        assertEquals(mockInfo, result.get());
        verify(personalInfoRepository, times(1)).findById(id);
    }

    @Test
    void testSavePersonalInfoThrowsExceptionWhenInvalid() {
        //Arrange
        PersonalInfo invalidInfo = new PersonalInfo();
        doAnswer(invocation -> {
            BindingResult result = invocation.getArgument(1);
            result.rejectValue("firstName", "NotBlank", "El nombre no puede estar vacio");
            return null;
        }).when(validator).validate(any(PersonalInfo.class), any(BindingResult.class));

        //Act & Assert
        assertThrows(ValidationException.class, () -> personalInfoService.save(invalidInfo));
        verify(personalInfoRepository, never()).save(any(PersonalInfo.class));
    }

    @Test
    void testSavePersonalInfoSavesValidInfo() {
        //Arrange
        PersonalInfo validInfo = new PersonalInfo(null,"Jhon","Doe","Dev","Descripcion","ImageURL",
                2,"email@gmail.com","telefono","linkedin.com","github.com");
        when(personalInfoRepository.save(any(PersonalInfo.class))).thenReturn(validInfo);
        doNothing().when(validator).validate(any(PersonalInfo.class), any(BindingResult.class));

        //
        PersonalInfo savedInfo = personalInfoService.save(validInfo);

        // Assert
        assertNotNull(savedInfo);
        verify(personalInfoRepository, times(1)).save(validInfo);
    }

}