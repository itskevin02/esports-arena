package com.project.msvc_match.Controller;

import com.project.msvc_match.DTO.MatchDTO;
import com.project.msvc_match.Service.MatchService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/matches")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    // POST: Crear partida
    @PostMapping
    public ResponseEntity<MatchDTO> createMatch(@Valid @RequestBody MatchDTO matchDTO) {
        MatchDTO created = matchService.createMatch(matchDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // GET: Listar por Torneo
    @GetMapping("/tournament/{torneoId}")
    public ResponseEntity<List<MatchDTO>> listByTorneo(@PathVariable Long torneoId) {
        List<MatchDTO> matches = matchService.listByTorneo(torneoId);
        return ResponseEntity.ok(matches);
    }

    // GET: Buscar partida por ID
    @GetMapping("/{id}")
    public ResponseEntity<MatchDTO> findById(@PathVariable Long id) {
        MatchDTO match = matchService.findById(id);
        return ResponseEntity.ok(match);
    }

    // PUT: Actualizar horario, participantes o estado
    @PutMapping("/{id}")
    public ResponseEntity<MatchDTO> updateMatch(@PathVariable Long id, @Valid @RequestBody MatchDTO matchDTO) {
        MatchDTO updated = matchService.updateMatch(id, matchDTO);
        return ResponseEntity.ok(updated);
    }

    // DELETE: Cancelar Partida (Eliminación lógica / Desactivar)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelMatch(@PathVariable Long id) {
        matchService.cancelMatch(id);
        return ResponseEntity.noContent().build();
    }
}