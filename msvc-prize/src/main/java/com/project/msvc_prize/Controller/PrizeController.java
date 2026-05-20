package com.project.msvc_prize_service.Controller;

import com.project.msvc_prize_service.DTO.PrizeDTO;
import com.project.msvc_prize_service.Model.PrizeModel;
import com.project.msvc_prize_service.Service.PrizeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/prizes")
public class PrizeController {

    @Autowired
    private PrizeService prizeService;

    @PostMapping
    public ResponseEntity<PrizeModel> crear(@Valid @RequestBody PrizeDTO dto) {
        PrizeModel nuevoPremio = prizeService.crearPremio(dto);
        return new ResponseEntity<>(nuevoPremio, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PrizeModel>> listar(
            @RequestParam(required = false) Long torneoId,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) Long ganadorId) {
        List<PrizeModel> premios = prizeService.listarPremios(torneoId, estado, ganadorId);
        return ResponseEntity.ok(premios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrizeModel> buscarPorId(@PathVariable Long id) {
        PrizeModel premio = prizeService.buscarPorId(id);
        return ResponseEntity.ok(premio);
    }

    // Endpoint especializado para el flujo de finalización de torneos
    @PutMapping("/{id}/asignar-ganador")
    public ResponseEntity<PrizeModel> asignarGanador(
            @PathVariable Long id,
            @RequestParam Long ganadorId) {
        PrizeModel premioAdjudicado = prizeService.asignarGanador(id, ganadorId);
        return ResponseEntity.ok(premioAdjudicado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        prizeService.eliminarPremio(id);
        return ResponseEntity.noContent().build();
    }
}