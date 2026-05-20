package com.project.msvc_match.controller;

import com.project.msvc_match.model.Match;
import com.project.msvc_match.service.MatchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping
    public List<Match> listarMatches() {
        return matchService.listarMatches();
    }

    @PostMapping
    public Match guardarMatch(@Valid @RequestBody Match match) {
        return matchService.guardarMatch(match);
    }

    @GetMapping("/{id}")
    public Match buscarMatch(@PathVariable Long id) {
        return matchService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Match actualizarMatch(@PathVariable Long id,
                                 @RequestBody Match match) {

        return matchService.actualizarMatch(id, match);
    }

    @DeleteMapping("/{id}")
    public void eliminarMatch(@PathVariable Long id) {
        matchService.eliminarMatch(id);
    }
}