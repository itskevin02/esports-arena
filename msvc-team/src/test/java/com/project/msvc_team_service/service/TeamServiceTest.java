package com.project.msvc_team.service;

import com.project.msvc_team.model.Team;
import com.project.msvc_team.repository.TeamRepository;
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
public class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamService teamService;

    @Test
    void listarTeamsTest() {

        Team team = new Team();
        team.setNombre("Equipo 1");

        when(teamRepository.findAll()).thenReturn(Arrays.asList(team));

        assertEquals(1, teamService.listarTeams().size());

        verify(teamRepository, times(1)).findAll();
    }

    @Test
    void buscarTeamPorIdTest() {

        Team team = new Team();
        team.setId(1L);
        team.setNombre("Equipo 1");

        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));

        Team resultado = teamService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("Equipo 1", resultado.getNombre());

        verify(teamRepository, times(1)).findById(1L);
    }

    @Test
    void guardarTeamTest() {

        Team team = new Team();
        team.setNombre("Equipo Nuevo");

        when(teamRepository.save(team)).thenReturn(team);

        Team resultado = teamService.guardarTeam(team);

        assertNotNull(resultado);
        assertEquals("Equipo Nuevo", resultado.getNombre());

        verify(teamRepository, times(1)).save(team);
    }

    @Test
    void actualizarTeamTest() {

        Team teamExistente = new Team();
        teamExistente.setId(1L);
        teamExistente.setNombre("Equipo 1");
        teamExistente.setCoach("Coach 1");
        teamExistente.setJuegoId(1L);
        teamExistente.setEstado("Activo");

        Team nuevoTeam = new Team();
        nuevoTeam.setNombre("Equipo Actualizado");
        nuevoTeam.setCoach("Coach 2");
        nuevoTeam.setJuegoId(2L);
        nuevoTeam.setEstado("Inactivo");

        when(teamRepository.findById(1L)).thenReturn(Optional.of(teamExistente));
        when(teamRepository.save(any(Team.class))).thenReturn(teamExistente);

        Team resultado = teamService.actualizarTeam(1L, nuevoTeam);

        assertNotNull(resultado);
        assertEquals("Equipo Actualizado", resultado.getNombre());
        assertEquals("Coach 2", resultado.getCoach());
        assertEquals(2L, resultado.getJuegoId());
        assertEquals("Inactivo", resultado.getEstado());

        verify(teamRepository, times(1)).findById(1L);
        verify(teamRepository, times(1)).save(any(Team.class));
    }

    @Test
    void eliminarTeamTest() {

        teamService.eliminarTeam(1L);

        verify(teamRepository, times(1)).deleteById(1L);
    }

}