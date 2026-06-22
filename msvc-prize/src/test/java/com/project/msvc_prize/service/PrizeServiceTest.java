package com.project.msvc_prize.service;

import com.project.msvc_prize.model.Prize;
import com.project.msvc_prize.repository.PrizeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PrizeServiceTest {

    @Mock
    private PrizeRepository prizeRepository;

    @InjectMocks
    private PrizeService prizeService;

    @Test
    void listarPrizesDebeRetornarLista() {

        Prize prize = new Prize();
        prize.setDescripcion("Premio Campeón");

        when(prizeRepository.findAll()).thenReturn(List.of(prize));

        List<Prize> resultado = prizeService.listarPrizes();

        assertEquals(1, resultado.size());
        assertEquals("Premio Campeón", resultado.get(0).getDescripcion());

        verify(prizeRepository, times(1)).findAll();
    }

    @Test
    void guardarPrizeDebeGuardarCorrectamente() {

        Prize prize = new Prize();
        prize.setDescripcion("Premio Campeón");

        when(prizeRepository.save(prize)).thenReturn(prize);

        Prize resultado = prizeService.guardarPrize(prize);

        assertEquals("Premio Campeón", resultado.getDescripcion());

        verify(prizeRepository, times(1)).save(prize);
    }
}