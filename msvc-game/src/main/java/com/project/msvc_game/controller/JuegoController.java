package com.project.msvc_game.controller;

import com.project.msvc_game.model.Juego;
import com.project.msvc_game.service.JuegoService;
import jakarta.validation.Valid;
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
    public Juego guardarJuego(@Valid @RequestBody Juego juego) {
        return juegoService.guardarJuego(juego);
    }


    @GetMapping("/{id}")
    public Juego buscarJuego(@PathVariable Long id) {
        return juegoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Juego actualizarJuego(@PathVariable Long id, @RequestBody Juego juego) {
        return juegoService.actualizarJuego(id, juego);
    }

    @DeleteMapping("/{id}")
    public void eliminarJuego(@PathVariable Long id) {
        juegoService.eliminarJuego(id);
    }
}