package com.project.msvc_auth.service;

import com.project.msvc_auth.model.Auth;
import com.project.msvc_auth.repository.AuthRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private AuthRepository authRepository;

    @InjectMocks
    private AuthService authService;

    @Test
    void listarAuthDebeRetornarLista() {

        Auth auth = new Auth();
        auth.setUsername("kevin");

        when(authRepository.findAll()).thenReturn(List.of(auth));

        List<Auth> resultado = authService.listarAuth();

        assertEquals(1, resultado.size());
        assertEquals("kevin", resultado.get(0).getUsername());

        verify(authRepository, times(1)).findAll();
    }

    @Test
    void guardarAuthDebeGuardarCorrectamente() {

        Auth auth = new Auth();
        auth.setUsername("kevin");

        when(authRepository.save(auth)).thenReturn(auth);

        Auth resultado = authService.guardarAuth(auth);

        assertEquals("kevin", resultado.getUsername());

        verify(authRepository, times(1)).save(auth);
    }
}