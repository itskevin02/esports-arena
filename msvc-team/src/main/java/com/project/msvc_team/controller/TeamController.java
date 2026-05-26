package com.project.msvc_team.controller;

import com.project.msvc_team.model.Team;
import com.project.msvc_team.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping
    public ResponseEntity<List<Team>> listar() {

        List<Team> lista = teamService.listarTeams();

        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<Team> guardar(@Valid @RequestBody Team team) {

        Team guardado = teamService.guardarTeam(team);

        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> buscarPorId(@PathVariable Long id) {

        Team team = teamService.buscarPorId(id);

        if (team != null) {
            return ResponseEntity.ok(team);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Team team
    ) {

        Team actualizado = teamService.actualizarTeam(id, team);

        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        teamService.eliminarTeam(id);

        return ResponseEntity.noContent().build();
    }
}