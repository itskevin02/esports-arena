package com.project.msvc_auth.controller;

import com.project.msvc_auth.model.Auth;
import com.project.msvc_auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping
    public ResponseEntity<List<Auth>> listar() {

        List<Auth> lista = authService.listarAuth();

        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<Auth> guardar(@Valid @RequestBody Auth auth) {

        Auth guardado = authService.guardarAuth(auth);

        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auth> buscarPorId(@PathVariable Long id) {

        Auth auth = authService.buscarPorId(id);

        if (auth != null) {
            return ResponseEntity.ok(auth);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Auth> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Auth auth
    ) {

        Auth actualizado = authService.actualizarAuth(id, auth);

        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        authService.eliminarAuth(id);

        return ResponseEntity.noContent().build();
    }
}