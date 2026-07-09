package com.project.msvc_registration.service;

import com.project.msvc_registration.model.Registration;
import com.project.msvc_registration.repository.RegistrationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
    void listarRegistrationsTest() {

        Registration registration = new Registration();
        registration.setEquipoId(1L);

        when(registrationRepository.findAll()).thenReturn(Arrays.asList(registration));

        assertEquals(1, registrationService.listarRegistrations().size());

        verify(registrationRepository, times(1)).findAll();
    }

    @Test
    void buscarRegistrationPorIdTest() {

        Registration registration = new Registration();
        registration.setId(1L);

        when(registrationRepository.findById(1L)).thenReturn(Optional.of(registration));

        Registration resultado = registrationService.buscarPorId(1L);

        assertNotNull(resultado);

        verify(registrationRepository, times(1)).findById(1L);
    }

    @Test
    void actualizarRegistrationTest() {

        Registration registrationExistente = new Registration();
        registrationExistente.setId(1L);
        registrationExistente.setEquipoId(1L);
        registrationExistente.setTorneoId(1L);
        registrationExistente.setEstado("ACTIVO");

        Registration nuevaRegistration = new Registration();
        nuevaRegistration.setEquipoId(2L);
        nuevaRegistration.setTorneoId(2L);
        nuevaRegistration.setEstado("INACTIVO");

        when(registrationRepository.findById(1L)).thenReturn(Optional.of(registrationExistente));
        when(registrationRepository.save(any(Registration.class))).thenReturn(registrationExistente);

        Registration resultado =
                registrationService.actualizarRegistration(1L, nuevaRegistration);

        assertNotNull(resultado);
        assertEquals(2L, resultado.getEquipoId());
        assertEquals(2L, resultado.getTorneoId());
        assertEquals("INACTIVO", resultado.getEstado());

        verify(registrationRepository, times(1)).findById(1L);
        verify(registrationRepository, times(1)).save(any(Registration.class));
    }

    @Test
    void eliminarRegistrationTest() {

        registrationService.eliminarRegistration(1L);

        verify(registrationRepository, times(1)).deleteById(1L);
    }

}