package com.project.msvc_prize.service;

import com.project.msvc_prize.model.Prize;
import com.project.msvc_prize.repository.PrizeRepository;
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
public class PrizeServiceTest {

    @Mock
    private PrizeRepository prizeRepository;

    @InjectMocks
    private PrizeService prizeService;

    @Test
    void listarPrizesTest() {

        Prize prize = new Prize();
        prize.setDescripcion("Premio 1");

        when(prizeRepository.findAll()).thenReturn(Arrays.asList(prize));

        assertEquals(1, prizeService.listarPrizes().size());

        verify(prizeRepository, times(1)).findAll();
    }

    @Test
    void buscarPrizePorIdTest() {

        Prize prize = new Prize();
        prize.setId(1L);
        prize.setDescripcion("Premio 1");

        when(prizeRepository.findById(1L)).thenReturn(Optional.of(prize));

        Prize resultado = prizeService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("Premio 1", resultado.getDescripcion());

        verify(prizeRepository, times(1)).findById(1L);
    }

    @Test
    void guardarPrizeTest() {

        Prize prize = new Prize();
        prize.setDescripcion("Nuevo Premio");

        when(prizeRepository.save(prize)).thenReturn(prize);

        Prize resultado = prizeService.guardarPrize(prize);

        assertNotNull(resultado);
        assertEquals("Nuevo Premio", resultado.getDescripcion());

        verify(prizeRepository, times(1)).save(prize);
    }

    @Test
    void actualizarPrizeTest() {

        Prize prizeExistente = new Prize();
        prizeExistente.setId(1L);
        prizeExistente.setTorneoId(1L);
        prizeExistente.setPosicion(1);
        prizeExistente.setDescripcion("Premio 1");
        prizeExistente.setValor(100000.0);
        prizeExistente.setEstado("Activo");

        Prize nuevoPrize = new Prize();
        nuevoPrize.setTorneoId(2L);
        nuevoPrize.setPosicion(2);
        nuevoPrize.setDescripcion("Premio Actualizado");
        nuevoPrize.setValor(50000.0);
        nuevoPrize.setEstado("Inactivo");

        when(prizeRepository.findById(1L)).thenReturn(Optional.of(prizeExistente));
        when(prizeRepository.save(any(Prize.class))).thenReturn(prizeExistente);

        Prize resultado = prizeService.actualizarPrize(1L, nuevoPrize);

        assertNotNull(resultado);
        assertEquals(2L, resultado.getTorneoId());
        assertEquals(2, resultado.getPosicion());
        assertEquals("Premio Actualizado", resultado.getDescripcion());
        assertEquals(50000.0, resultado.getValor());
        assertEquals("Inactivo", resultado.getEstado());

        verify(prizeRepository, times(1)).findById(1L);
        verify(prizeRepository, times(1)).save(any(Prize.class));
    }

    @Test
    void eliminarPrizeTest() {

        prizeService.eliminarPrize(1L);

        verify(prizeRepository, times(1)).deleteById(1L);
    }
}