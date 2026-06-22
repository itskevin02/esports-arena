package com.project.msvc_tournament.service;

import com.project.msvc_tournament.model.Tournament;
import com.project.msvc_tournament.repository.TournamentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TournamentServiceTest {

    @Mock
    private TournamentRepository tournamentRepository;

    @InjectMocks
    private TournamentService tournamentService;

    @Test
    void listarTournamentsDebeRetornarLista() {

        Tournament tournament = new Tournament();
        tournament.setNombre("Torneo Mundial");

        when(tournamentRepository.findAll()).thenReturn(List.of(tournament));

        List<Tournament> resultado = tournamentService.listarTournaments();

        assertEquals(1, resultado.size());
        assertEquals("Torneo Mundial", resultado.get(0).getNombre());

        verify(tournamentRepository, times(1)).findAll();
    }

    @Test
    void guardarTournamentDebeGuardarCorrectamente() {

        Tournament tournament = new Tournament();
        tournament.setNombre("Torneo Mundial");

        when(tournamentRepository.save(tournament)).thenReturn(tournament);

        Tournament resultado = tournamentService.guardarTournament(tournament);

        assertEquals("Torneo Mundial", resultado.getNombre());

        verify(tournamentRepository, times(1)).save(tournament);
    }
}