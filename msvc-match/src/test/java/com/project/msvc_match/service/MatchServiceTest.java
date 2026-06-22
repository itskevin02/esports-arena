package com.project.msvc_match.service;

import com.project.msvc_match.model.Match;
import com.project.msvc_match.repository.MatchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MatchServiceTest {

    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    private MatchService matchService;

    @Test
    void listarMatchesDebeRetornarLista() {

        Match match = new Match();
        match.setRonda("Final");

        when(matchRepository.findAll()).thenReturn(List.of(match));

        List<Match> resultado = matchService.listarMatches();

        assertEquals(1, resultado.size());
        assertEquals("Final", resultado.get(0).getRonda());

        verify(matchRepository, times(1)).findAll();
    }

    @Test
    void guardarMatchDebeGuardarCorrectamente() {

        Match match = new Match();
        match.setRonda("Final");

        when(matchRepository.save(match)).thenReturn(match);

        Match resultado = matchService.guardarMatch(match);

        assertEquals("Final", resultado.getRonda());

        verify(matchRepository, times(1)).save(match);
    }
}