package com.project.msvc_ranking.service;

import com.project.msvc_ranking.model.Ranking;
import com.project.msvc_ranking.repository.RankingRepository;
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
public class RankingServiceTest {

    @Mock
    private RankingRepository rankingRepository;

    @InjectMocks
    private RankingService rankingService;

    @Test
    void listarRankingsTest() {

        Ranking ranking = new Ranking();
        ranking.setNombreEquipo("Equipo 1");

        when(rankingRepository.findAll()).thenReturn(Arrays.asList(ranking));

        assertEquals(1, rankingService.listarRankings().size());

        verify(rankingRepository, times(1)).findAll();
    }

    @Test
    void buscarRankingPorIdTest() {

        Ranking ranking = new Ranking();
        ranking.setId(1L);
        ranking.setNombreEquipo("Equipo 1");

        when(rankingRepository.findById(1L)).thenReturn(Optional.of(ranking));

        Ranking resultado = rankingService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("Equipo 1", resultado.getNombreEquipo());

        verify(rankingRepository, times(1)).findById(1L);
    }

    @Test
    void guardarRankingTest() {

        Ranking ranking = new Ranking();
        ranking.setNombreEquipo("Equipo Nuevo");

        when(rankingRepository.save(ranking)).thenReturn(ranking);

        Ranking resultado = rankingService.guardarRanking(ranking);

        assertNotNull(resultado);
        assertEquals("Equipo Nuevo", resultado.getNombreEquipo());

        verify(rankingRepository, times(1)).save(ranking);
    }

    @Test
    void actualizarRankingTest() {

        Ranking rankingExistente = new Ranking();
        rankingExistente.setId(1L);
        rankingExistente.setNombreEquipo("Equipo 1");
        rankingExistente.setPuntos(100);
        rankingExistente.setPosicion(1);
        rankingExistente.setEstado("Activo");

        Ranking nuevoRanking = new Ranking();
        nuevoRanking.setNombreEquipo("Equipo Actualizado");
        nuevoRanking.setPuntos(200);
        nuevoRanking.setPosicion(2);
        nuevoRanking.setEstado("Inactivo");

        when(rankingRepository.findById(1L)).thenReturn(Optional.of(rankingExistente));
        when(rankingRepository.save(any(Ranking.class))).thenReturn(rankingExistente);

        Ranking resultado = rankingService.actualizarRanking(1L, nuevoRanking);

        assertNotNull(resultado);
        assertEquals("Equipo Actualizado", resultado.getNombreEquipo());
        assertEquals(200, resultado.getPuntos());
        assertEquals(2, resultado.getPosicion());
        assertEquals("Inactivo", resultado.getEstado());

        verify(rankingRepository, times(1)).findById(1L);
        verify(rankingRepository, times(1)).save(any(Ranking.class));
    }

    @Test
    void eliminarRankingTest() {

        rankingService.eliminarRanking(1L);

        verify(rankingRepository, times(1)).deleteById(1L);
    }

}