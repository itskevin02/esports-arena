package com.project.msvc_match.controller;

import com.project.msvc_match.model.Match;
import com.project.msvc_match.service.MatchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping
    public ResponseEntity<List<Match>> listar() {

        List<Match> lista = matchService.listarMatches();

        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<Match> guardar(@Valid @RequestBody Match match) {

        Match guardado = matchService.guardarMatch(match);

        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Match> buscarPorId(@PathVariable Long id) {

        Match match = matchService.buscarPorId(id);

        if (match != null) {
            return ResponseEntity.ok(match);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Match> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Match match
    ) {

        Match actualizado = matchService.actualizarMatch(id, match);

        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        matchService.eliminarMatch(id);

        return ResponseEntity.noContent().build();
    }
}