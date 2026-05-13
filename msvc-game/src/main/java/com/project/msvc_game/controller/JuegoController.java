package com.project.msvc_game.controller;

import com.project.msvc_game.model.Juego;
import com.project.msvc_game.service.JuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/juegos")
public class JuegoController {

    @Autowired
    private JuegoService juegoService;

    @GetMapping
    public List<Juego> listarJuegos() {
        return juegoService.listarJuegos();
    }

    @PostMapping
    public Juego guardarJuego(@RequestBody Juego juego) {
        return juegoService.guardarJuego(juego);
    }
}