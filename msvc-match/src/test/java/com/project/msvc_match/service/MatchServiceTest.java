package com.project.msvc_match.service;

import com.project.msvc_match.model.Match;
import com.project.msvc_match.repository.MatchRepository;
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
public class MatchServiceTest {

    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    private MatchService matchService;

    @Test
    void listarMatchesTest() {

        Match match = new Match();
        match.setRonda("Final");

        when(matchRepository.findAll()).thenReturn(Arrays.asList(match));

        assertEquals(1, matchService.listarMatches().size());

        verify(matchRepository, times(1)).findAll();
    }

    @Test
    void buscarMatchPorIdTest() {

        Match match = new Match();
        match.setId(1L);
        match.setRonda("Final");

        when(matchRepository.findById(1L)).thenReturn(Optional.of(match));

        Match resultado = matchService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("Final", resultado.getRonda());

        verify(matchRepository, times(1)).findById(1L);
    }

    @Test
    void guardarMatchTest() {

        Match match = new Match();
        match.setRonda("Semifinal");

        when(matchRepository.save(match)).thenReturn(match);

        Match resultado = matchService.guardarMatch(match);

        assertNotNull(resultado);
        assertEquals("Semifinal", resultado.getRonda());

        verify(matchRepository, times(1)).save(match);
    }

    @Test
    void actualizarMatchTest() {

        Match matchExistente = new Match();
        matchExistente.setId(1L);
        matchExistente.setTorneoId(1L);
        matchExistente.setParticipanteAId(10L);
        matchExistente.setParticipanteBId(20L);
        matchExistente.setRonda("Semifinal");
        matchExistente.setEstado("Activo");

        Match nuevoMatch = new Match();
        nuevoMatch.setTorneoId(2L);
        nuevoMatch.setParticipanteAId(30L);
        nuevoMatch.setParticipanteBId(40L);
        nuevoMatch.setRonda("Final");
        nuevoMatch.setEstado("Finalizado");

        when(matchRepository.findById(1L)).thenReturn(Optional.of(matchExistente));
        when(matchRepository.save(any(Match.class))).thenReturn(matchExistente);

        Match resultado = matchService.actualizarMatch(1L, nuevoMatch);

        assertNotNull(resultado);
        assertEquals(2L, resultado.getTorneoId());
        assertEquals(30L, resultado.getParticipanteAId());
        assertEquals(40L, resultado.getParticipanteBId());
        assertEquals("Final", resultado.getRonda());
        assertEquals("Finalizado", resultado.getEstado());

        verify(matchRepository, times(1)).findById(1L);
        verify(matchRepository, times(1)).save(any(Match.class));
    }

    @Test
    void eliminarMatchTest() {

        matchService.eliminarMatch(1L);

        verify(matchRepository, times(1)).deleteById(1L);
    }

}