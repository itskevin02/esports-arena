package com.project.msvc_registration.controller;

import com.project.msvc_registration.model.Registration;
import com.project.msvc_registration.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping
    public ResponseEntity<List<Registration>> listar() {

        List<Registration> lista = registrationService.listarRegistrations();

        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<Registration> guardar(@Valid @RequestBody Registration registration) {

        Registration guardado = registrationService.guardarRegistration(registration);

        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Registration> buscarPorId(@PathVariable Long id) {

        Registration registration = registrationService.buscarPorId(id);

        if (registration != null) {
            return ResponseEntity.ok(registration);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Registration> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Registration registration
    ) {

        Registration actualizado =
                registrationService.actualizarRegistration(id, registration);

        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        registrationService.eliminarRegistration(id);

        return ResponseEntity.noContent().build();
    }
}