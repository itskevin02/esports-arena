package com.project.msvc_team.service;

import com.project.msvc_team.model.Team;
import com.project.msvc_team.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class TeamService {

    private static final Logger logger = LoggerFactory.getLogger(TeamService.class);

    @Autowired
    private TeamRepository teamRepository;

    public List<Team> listarTeams() {

        logger.info("Listando equipos");

        return teamRepository.findAll();
    }

    public Team guardarTeam(Team team) {

        logger.info("Guardando equipo: " + team.getNombre());

        return teamRepository.save(team);
    }

    public Team buscarPorId(Long id) {

        logger.info("Buscando equipo con id: " + id);

        return teamRepository.findById(id).orElse(null);
    }

    public Team actualizarTeam(Long id, Team team) {

        logger.info("Actualizando equipo con id: " + id);

        Team teamExistente = teamRepository.findById(id).orElse(null);

        if (teamExistente != null) {

            teamExistente.setNombre(team.getNombre());
            teamExistente.setCoach(team.getCoach());
            teamExistente.setJuegoId(team.getJuegoId());
            teamExistente.setEstado(team.getEstado());

            return teamRepository.save(teamExistente);
        }

        return null;
    }

    public void eliminarTeam(Long id) {

        logger.info("Eliminando equipo con id: " + id);

        teamRepository.deleteById(id);
    }
}