package com.project.msvc_registration.Controller;

import com.project.msvc_registration.DTO.RegistrationDTO;
import com.project.msvc_registration.Model.RegistrationModel;
import com.project.msvc_registration.Service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService service;

    @PostMapping
    public ResponseEntity<RegistrationModel> crear(@Valid @RequestBody RegistrationDTO dto) {
        RegistrationModel creada = service.crearInscripcion(dto);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RegistrationModel>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrationModel> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/torneo/{torneoId}")
    public ResponseEntity<List<RegistrationModel>> listarPorTorneo(@PathVariable Long torneoId) {
        return ResponseEntity.ok(service.listarPorTorneo(torneoId));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<RegistrationModel> actualizarEstado(@PathVariable Long id, @RequestParam String estado) {
        return ResponseEntity.ok(service.actualizarEstado(id, estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        service.cancelarInscripcion(id);
        return ResponseEntity.noContent().build();
    }
}