package com.project.msvc_result.service;

import com.project.msvc_result.model.Result;
import com.project.msvc_result.repository.ResultRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ResultServiceTest {

    @Mock
    private ResultRepository resultRepository;

    @InjectMocks
    private ResultService resultService;

    @Test
    void listarResultsDebeRetornarLista() {

        Result result = new Result();

        when(resultRepository.findAll()).thenReturn(List.of(result));

        List<Result> resultado = resultService.listarResults();

        assertEquals(1, resultado.size());

        verify(resultRepository, times(1)).findAll();
    }

    @Test
    void guardarResultDebeGuardarCorrectamente() {

        Result result = new Result();
        result.setPuntajeA(2);
        result.setPuntajeB(1);

        when(resultRepository.save(result)).thenReturn(result);

        Result resultado = resultService.guardarResult(result);

        assertEquals(2, resultado.getPuntajeA());
        assertEquals(1, resultado.getPuntajeB());

        verify(resultRepository, times(1)).save(result);
    }

    @Test
    void guardarResultDebeLanzarExcepcionSiPuntajeEsNegativo() {

        Result result = new Result();
        result.setPuntajeA(-1);
        result.setPuntajeB(3);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> resultService.guardarResult(result)
        );

        assertEquals(
                "Los puntajes no pueden ser negativos",
                exception.getMessage()
        );

        verify(resultRepository, never()).save(any(Result.class));
    }
}