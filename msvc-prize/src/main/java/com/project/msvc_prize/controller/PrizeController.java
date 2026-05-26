package com.project.msvc_prize.controller;

import com.project.msvc_prize.model.Prize;
import com.project.msvc_prize.service.PrizeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prizes")
public class PrizeController {

    @Autowired
    private PrizeService prizeService;

    @GetMapping
    public ResponseEntity<List<Prize>> listar() {

        List<Prize> lista = prizeService.listarPrizes();

        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<Prize> guardar(@Valid @RequestBody Prize prize) {

        Prize guardado = prizeService.guardarPrize(prize);

        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prize> buscarPorId(@PathVariable Long id) {

        Prize prize = prizeService.buscarPorId(id);

        if (prize != null) {
            return ResponseEntity.ok(prize);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prize> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Prize prize
    ) {

        Prize actualizado = prizeService.actualizarPrize(id, prize);

        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        prizeService.eliminarPrize(id);

        return ResponseEntity.noContent().build();
    }
}