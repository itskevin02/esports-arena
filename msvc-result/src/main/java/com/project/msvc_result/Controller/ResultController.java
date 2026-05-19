package com.project.msvc_result.Controller;

import com.project.msvc_result.DTO.ResultDTO;
import com.project.msvc_result.Model.ResultModel;
import com.project.msvc_result.Service.ResultService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/result")
public class ResultController {

    @Autowired
    private ResultService service;

    @PostMapping
    public ResponseEntity<ResultModel> registrar(@Valid @RequestBody ResultDTO dto) {
        ResultModel creado = service.registrarResultado(dto);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ResultModel>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultModel> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultModel> actualizar(@PathVariable Long id, @Valid @RequestBody ResultDTO dto) {
        return ResponseEntity.ok(service.actualizarResultadoAntesDeValidar(id, dto));
    }

    @PatchMapping("/{id}/validar")
    public ResponseEntity<ResultModel> validar(@PathVariable Long id) {
        return ResponseEntity.ok(service.validarResultadoPorOrganizador(id));
    }

    @PutMapping("/{id}/anular")
    public ResponseEntity<ResultModel> anular(@PathVariable Long id, @RequestParam String justificacion) {
        return ResponseEntity.ok(service.anularResultado(id, justificacion));
    }
}