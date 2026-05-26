package com.project.msvc_ranking.controller;

import com.project.msvc_ranking.model.Ranking;
import com.project.msvc_ranking.service.RankingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rankings")
public class RankingController {

    @Autowired
    private RankingService rankingService;

    @GetMapping
    public ResponseEntity<List<Ranking>> listar() {

        List<Ranking> lista = rankingService.listarRankings();

        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<Ranking> guardar(@Valid @RequestBody Ranking ranking) {

        Ranking guardado = rankingService.guardarRanking(ranking);

        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ranking> buscarPorId(@PathVariable Long id) {

        Ranking ranking = rankingService.buscarPorId(id);

        if (ranking != null) {
            return ResponseEntity.ok(ranking);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ranking> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Ranking ranking
    ) {

        Ranking actualizado = rankingService.actualizarRanking(id, ranking);

        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        rankingService.eliminarRanking(id);

        return ResponseEntity.noContent().build();
    }
}