package com.project.msvc_tournament.controller;

import com.project.msvc_tournament.model.Tournament;
import com.project.msvc_tournament.service.TournamentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @GetMapping
    public List<Tournament> listarTournaments() {
        return tournamentService.listarTournaments();
    }

    @PostMapping
    public Tournament guardarTournament(@Valid @RequestBody Tournament tournament) {
        return tournamentService.guardarTournament(tournament);
    }

    @GetMapping("/{id}")
    public Tournament buscarTournament(@PathVariable Long id) {
        return tournamentService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Tournament actualizarTournament(@PathVariable Long id,
                                           @RequestBody Tournament tournament) {

        return tournamentService.actualizarTournament(id, tournament);
    }

    @DeleteMapping("/{id}")
    public void eliminarTournament(@PathVariable Long id) {
        tournamentService.eliminarTournament(id);
    }
}