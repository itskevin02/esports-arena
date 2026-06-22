package com.project.msvc_registration.service;

import com.project.msvc_registration.model.Registration;
import com.project.msvc_registration.repository.RegistrationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegistrationServiceTest {

    @Mock
    private RegistrationRepository registrationRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private RegistrationService registrationService;

    @Test
    void listarRegistrationsDebeRetornarLista() {

        Registration registration = new Registration();

        when(registrationRepository.findAll())
                .thenReturn(List.of(registration));

        List<Registration> resultado =
                registrationService.listarRegistrations();

        assertEquals(1, resultado.size());

        verify(registrationRepository, times(1))
                .findAll();
    }
}