package com.project.msvc_game.service;

import com.project.msvc_game.model.Juego;
import com.project.msvc_game.repository.JuegoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JuegoServiceTest {

    @Mock
    private JuegoRepository juegoRepository;

    @InjectMocks
    private JuegoService juegoService;

    @Test
    void listarJuegosDebeRetornarLista() {

        Juego juego = new Juego();
        juego.setNombre("League of Legends");

        when(juegoRepository.findAll()).thenReturn(List.of(juego));

        List<Juego> resultado = juegoService.listarJuegos();

        assertEquals(1, resultado.size());
        assertEquals("League of Legends", resultado.get(0).getNombre());

        verify(juegoRepository, times(1)).findAll();
    }

    @Test
    void guardarJuegoDebeGuardarCorrectamente() {

        Juego juego = new Juego();
        juego.setNombre("Valorant");

        when(juegoRepository.save(juego)).thenReturn(juego);

        Juego resultado = juegoService.guardarJuego(juego);

        assertEquals("Valorant", resultado.getNombre());

        verify(juegoRepository, times(1)).save(juego);
    }
}