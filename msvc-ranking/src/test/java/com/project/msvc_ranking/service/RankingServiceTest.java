package com.project.msvc_ranking.service;

import com.project.msvc_ranking.model.Ranking;
import com.project.msvc_ranking.repository.RankingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RankingServiceTest {

    @Mock
    private RankingRepository rankingRepository;

    @InjectMocks
    private RankingService rankingService;

    @Test
    void listarRankingsDebeRetornarLista() {

        Ranking ranking = new Ranking();
        ranking.setNombreEquipo("Team Alpha");

        when(rankingRepository.findAll()).thenReturn(List.of(ranking));

        List<Ranking> resultado = rankingService.listarRankings();

        assertEquals(1, resultado.size());
        assertEquals("Team Alpha", resultado.get(0).getNombreEquipo());

        verify(rankingRepository, times(1)).findAll();
    }

    @Test
    void guardarRankingDebeGuardarCorrectamente() {

        Ranking ranking = new Ranking();
        ranking.setNombreEquipo("Team Alpha");

        when(rankingRepository.save(ranking)).thenReturn(ranking);

        Ranking resultado = rankingService.guardarRanking(ranking);

        assertEquals("Team Alpha", resultado.getNombreEquipo());

        verify(rankingRepository, times(1)).save(ranking);
    }
}