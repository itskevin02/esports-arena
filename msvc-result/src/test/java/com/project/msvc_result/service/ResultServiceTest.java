package com.project.msvc_result.service;

import com.project.msvc_result.model.Result;
import com.project.msvc_result.repository.ResultRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ResultServiceTest {

    @Mock
    private ResultRepository resultRepository;

    @InjectMocks
    private ResultService resultService;

    @Test
    void listarResultsTest() {

        Result result = new Result();
        result.setPartidaId(1L);

        when(resultRepository.findAll()).thenReturn(Arrays.asList(result));

        assertEquals(1, resultService.listarResults().size());

        verify(resultRepository, times(1)).findAll();
    }

    @Test
    void buscarResultPorIdTest() {

        Result result = new Result();
        result.setId(1L);
        result.setPartidaId(1L);

        when(resultRepository.findById(1L)).thenReturn(Optional.of(result));

        Result resultado = resultService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getPartidaId());

        verify(resultRepository, times(1)).findById(1L);
    }

    @Test
    void guardarResultTest() {

        Result result = new Result();
        result.setPartidaId(1L);
        result.setGanadorId(2L);
        result.setPuntajeA(3);
        result.setPuntajeB(1);
        result.setEstadoValidacion("Validado");

        when(resultRepository.save(result)).thenReturn(result);

        Result resultado = resultService.guardarResult(result);

        assertNotNull(resultado);
        assertEquals(3, resultado.getPuntajeA());

        verify(resultRepository, times(1)).save(result);
    }

    @Test
    void guardarResultConPuntajeNegativoTest() {

        Result result = new Result();
        result.setPartidaId(1L);
        result.setGanadorId(2L);
        result.setPuntajeA(-1);
        result.setPuntajeB(2);
        result.setEstadoValidacion("Validado");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            resultService.guardarResult(result);
        });

        assertEquals("Los puntajes no pueden ser negativos", exception.getMessage());

        verify(resultRepository, never()).save(any(Result.class));
    }

    @Test
    void actualizarResultTest() {

        Result resultExistente = new Result();
        resultExistente.setId(1L);
        resultExistente.setPartidaId(1L);
        resultExistente.setGanadorId(2L);
        resultExistente.setPuntajeA(2);
        resultExistente.setPuntajeB(1);
        resultExistente.setEstadoValidacion("Pendiente");

        Result nuevoResult = new Result();
        nuevoResult.setPartidaId(2L);
        nuevoResult.setGanadorId(3L);
        nuevoResult.setPuntajeA(5);
        nuevoResult.setPuntajeB(4);
        nuevoResult.setEstadoValidacion("Validado");

        when(resultRepository.findById(1L)).thenReturn(Optional.of(resultExistente));
        when(resultRepository.save(any(Result.class))).thenReturn(resultExistente);

        Result resultado = resultService.actualizarResult(1L, nuevoResult);

        assertNotNull(resultado);
        assertEquals(2L, resultado.getPartidaId());
        assertEquals(3L, resultado.getGanadorId());
        assertEquals(5, resultado.getPuntajeA());
        assertEquals(4, resultado.getPuntajeB());
        assertEquals("Validado", resultado.getEstadoValidacion());

        verify(resultRepository, times(1)).findById(1L);
        verify(resultRepository, times(1)).save(any(Result.class));
    }

    @Test
    void actualizarResultConPuntajeNegativoTest() {

        Result resultExistente = new Result();
        resultExistente.setId(1L);
        resultExistente.setPuntajeA(2);
        resultExistente.setPuntajeB(1);

        Result nuevoResult = new Result();
        nuevoResult.setPartidaId(2L);
        nuevoResult.setGanadorId(3L);
        nuevoResult.setPuntajeA(-5);
        nuevoResult.setPuntajeB(4);
        nuevoResult.setEstadoValidacion("Validado");

        when(resultRepository.findById(1L)).thenReturn(Optional.of(resultExistente));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            resultService.actualizarResult(1L, nuevoResult);
        });

        assertEquals("Los puntajes no pueden ser negativos", exception.getMessage());

        verify(resultRepository, times(1)).findById(1L);
        verify(resultRepository, never()).save(any(Result.class));
    }

    @Test
    void eliminarResultTest() {

        resultService.eliminarResult(1L);

        verify(resultRepository, times(1)).deleteById(1L);
    }

}