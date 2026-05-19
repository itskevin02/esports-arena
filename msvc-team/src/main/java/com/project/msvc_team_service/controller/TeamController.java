package com.project.msvc_team_service.controller;

import com.project.msvc_team_service.model.Team;
import com.project.msvc_team_service.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping
    public List<Team> listarTeams() {
        return teamService.listarTeams();
    }

    @PostMapping
    public Team guardarTeam(@Valid @RequestBody Team team) {
        return teamService.guardarTeam(team);
    }

    @GetMapping("/{id}")
    public Team buscarTeam(@PathVariable Long id) {
        return teamService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Team actualizarTeam(@PathVariable Long id,
                               @RequestBody Team team) {

        return teamService.actualizarTeam(id, team);
    }

    @DeleteMapping("/{id}")
    public void eliminarTeam(@PathVariable Long id) {
        teamService.eliminarTeam(id);
    }
}