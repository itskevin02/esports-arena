package com.project.msvc_sanction.controller;

import com.project.msvc_sanction.model.Sanction;
import com.project.msvc_sanction.service.SanctionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sanctions")
public class SanctionController {

    @Autowired
    private SanctionService sanctionService;

    @GetMapping
    public ResponseEntity<List<Sanction>> listar() {

        List<Sanction> lista = sanctionService.listarSanctions();

        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<Sanction> guardar(@Valid @RequestBody Sanction sanction) {

        Sanction guardado = sanctionService.guardarSanction(sanction);

        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sanction> buscarPorId(@PathVariable Long id) {

        Sanction sanction = sanctionService.buscarPorId(id);

        if (sanction != null) {
            return ResponseEntity.ok(sanction);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sanction> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Sanction sanction
    ) {

        Sanction actualizado = sanctionService.actualizarSanction(id, sanction);

        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        sanctionService.eliminarSanction(id);

        return ResponseEntity.noContent().build();
    }
}