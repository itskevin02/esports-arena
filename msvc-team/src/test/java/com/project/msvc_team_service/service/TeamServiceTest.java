package com.project.msvc_team.service;

import com.project.msvc_team.model.Team;
import com.project.msvc_team.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamService teamService;

    @Test
    void listarTeamsDebeRetornarLista() {

        Team team = new Team();
        team.setNombre("Team Alpha");

        when(teamRepository.findAll()).thenReturn(List.of(team));

        List<Team> resultado = teamService.listarTeams();

        assertEquals(1, resultado.size());
        assertEquals("Team Alpha", resultado.get(0).getNombre());

        verify(teamRepository, times(1)).findAll();
    }

    @Test
    void guardarTeamDebeGuardarCorrectamente() {

        Team team = new Team();
        team.setNombre("Team Alpha");

        when(teamRepository.save(team)).thenReturn(team);

        Team resultado = teamService.guardarTeam(team);

        assertEquals("Team Alpha", resultado.getNombre());

        verify(teamRepository, times(1)).save(team);
    }
}