package com.project.msvc_auth.controller;

import com.project.msvc_auth.model.Auth;
import com.project.msvc_auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping
    public List<Auth> listarAuth() {
        return authService.listarAuth();
    }

    @PostMapping
    public Auth guardarAuth(@Valid @RequestBody Auth auth) {
        return authService.guardarAuth(auth);
    }

    @GetMapping("/{id}")
    public Auth buscarAuth(@PathVariable Long id) {
        return authService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Auth actualizarAuth(@PathVariable Long id,
                               @RequestBody Auth auth) {

        return authService.actualizarAuth(id, auth);
    }

    @DeleteMapping("/{id}")
    public void eliminarAuth(@PathVariable Long id) {
        authService.eliminarAuth(id);
    }
}