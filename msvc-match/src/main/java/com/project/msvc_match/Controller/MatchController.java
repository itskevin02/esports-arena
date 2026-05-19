package com.project.msvc_match.Controller;

import com.project.msvc_match.DTO.MatchDTO;
import com.project.msvc_match.Model.MatchModel;
import com.project.msvc_match.Service.MatchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/match")
public class MatchController {

    @Autowired
    private MatchService service;

    @PostMapping
    public ResponseEntity<MatchModel> crearPartida(@Valid @RequestBody MatchDTO dto) {
        MatchModel creada = service.crearPartida(dto);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MatchModel>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchModel> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/torneo/{torneoId}")
    public ResponseEntity<List<MatchModel>> listarPorTorneo(@PathVariable Long torneoId) {
        return ResponseEntity.ok(service.listarPorTorneo(torneoId));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<MatchModel> actualizarEstado(@PathVariable Long id, @RequestParam String estado) {
        return ResponseEntity.ok(service.actualizarEstado(id, estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarPartida(@PathVariable Long id) {
        service.cancelarPartida(id);
        return ResponseEntity.noContent().build();
    }
}