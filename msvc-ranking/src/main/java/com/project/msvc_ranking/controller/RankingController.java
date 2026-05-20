package com.project.msvc_ranking.controller;

import com.project.msvc_ranking.model.Ranking;
import com.project.msvc_ranking.service.RankingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rankings")
public class RankingController {

    @Autowired
    private RankingService rankingService;

    @GetMapping
    public List<Ranking> listarRankings() {
        return rankingService.listarRankings();
    }

    @PostMapping
    public Ranking guardarRanking(@Valid @RequestBody Ranking ranking) {
        return rankingService.guardarRanking(ranking);
    }

    @GetMapping("/{id}")
    public Ranking buscarRanking(@PathVariable Long id) {
        return rankingService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Ranking actualizarRanking(@PathVariable Long id,
                                     @RequestBody Ranking ranking) {

        return rankingService.actualizarRanking(id, ranking);
    }

    @DeleteMapping("/{id}")
    public void eliminarRanking(@PathVariable Long id) {
        rankingService.eliminarRanking(id);
    }
}