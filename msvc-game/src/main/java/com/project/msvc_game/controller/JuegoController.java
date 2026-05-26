package com.project.msvc_game.controller;

import com.project.msvc_game.model.Juego;
import com.project.msvc_game.service.JuegoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class JuegoController {

    @Autowired
    private JuegoService juegoService;

    @GetMapping
    public ResponseEntity<List<Juego>> listar() {

        List<Juego> lista = juegoService.listarJuegos();

        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<Juego> guardar(@Valid @RequestBody Juego juego) {

        Juego guardado = juegoService.guardarJuego(juego);

        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Juego> buscarPorId(@PathVariable Long id) {

        Juego juego = juegoService.buscarPorId(id);

        if (juego != null) {
            return ResponseEntity.ok(juego);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Juego> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Juego juego
    ) {

        Juego actualizado = juegoService.actualizarJuego(id, juego);

        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        juegoService.eliminarJuego(id);

        return ResponseEntity.noContent().build();
    }
}