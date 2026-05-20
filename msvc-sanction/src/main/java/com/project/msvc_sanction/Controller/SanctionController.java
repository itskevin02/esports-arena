package com.project.msvc_sanction_service.Controller;

import com.project.msvc_sanction_service.DTO.SanctionDTO;
import com.project.msvc_sanction_service.Model.SanctionModel;
import com.project.msvc_sanction_service.Service.SanctionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/sanctions")
public class SanctionController {

    @Autowired
    private SanctionService sanctionService;

    @PostMapping
    public ResponseEntity<SanctionModel> crear(@Valid @RequestBody SanctionDTO dto) {
        SanctionModel nuevaSancion = sanctionService.crearSancion(dto);
        return new ResponseEntity<>(nuevaSancion, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SanctionModel>> listar(
            @RequestParam(required = false) Long usuarioId,
            @RequestParam(required = false) Long equipoId,
            @RequestParam(required = false) String estado) {
        List<SanctionModel> sanciones = sanctionService.listarSanciones(usuarioId, equipoId, estado);
        return ResponseEntity.ok(sanciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SanctionModel> buscarPorId(@PathVariable Long id) {
        SanctionModel sancion = sanctionService.buscarPorId(id);
        return ResponseEntity.ok(sancion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SanctionModel> actualizar(@PathVariable Long id, @Valid @RequestBody SanctionDTO dto) {
        SanctionModel sancionActualizada = sanctionService.actualizarSancion(id, dto);
        return ResponseEntity.ok(sancionActualizada);
    }

    @PutMapping("/{id}/cerrar")
    public ResponseEntity<Void> cerrarSancion(@PathVariable Long id) {
        sanctionService.cerrarSancionCumplida(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint de comunicación entre servicios (consumido idealmente vía OpenFeign)
    @GetMapping("/validar-bloqueo")
    public ResponseEntity<Boolean> verificarBloqueo(
            @RequestParam(required = false) Long usuarioId,
            @RequestParam(required = false) Long equipoId) {
        boolean estaBloqueado = sanctionService.verificarBloqueoActivo(usuarioId, equipoId);
        return ResponseEntity.ok(estaBloqueado);
    }
}