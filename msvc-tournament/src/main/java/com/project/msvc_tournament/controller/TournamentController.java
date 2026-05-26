package com.project.msvc_tournament.controller;

import com.project.msvc_tournament.model.Tournament;
import com.project.msvc_tournament.service.TournamentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @GetMapping
    public ResponseEntity<List<Tournament>> listar() {

        List<Tournament> lista = tournamentService.listarTournaments();

        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<Tournament> guardar(
            @Valid @RequestBody Tournament tournament
    ) {

        Tournament guardado =
                tournamentService.guardarTournament(tournament);

        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tournament> buscarPorId(@PathVariable Long id) {

        Tournament tournament = tournamentService.buscarPorId(id);

        if (tournament != null) {
            return ResponseEntity.ok(tournament);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tournament> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Tournament tournament
    ) {

        Tournament actualizado =
                tournamentService.actualizarTournament(id, tournament);

        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        tournamentService.eliminarTournament(id);

        return ResponseEntity.noContent().build();
    }
}