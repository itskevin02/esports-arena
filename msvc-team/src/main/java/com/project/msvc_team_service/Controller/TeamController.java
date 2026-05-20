package com.project.msvc_team_service.Controller;

import com.project.msvc_team_service.DTO.TeamDTO;
import com.project.msvc_team_service.Model.TeamModel;
import com.project.msvc_team_service.Service.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping
    public ResponseEntity<TeamModel> crear(@Valid @RequestBody TeamDTO dto) {
        TeamModel nuevoEquipo = teamService.crearEquipo(dto);
        return new ResponseEntity<>(nuevoEquipo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TeamModel>> listar(
            @RequestParam(required = false) Long juegoId,
            @RequestParam(required = false) Long capitanId,
            @RequestParam(required = false) String estado) {
        List<TeamModel> equipos = teamService.listarEquipos(juegoId, capitanId, estado);
        return ResponseEntity.ok(equipos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamModel> buscarPorId(@PathVariable Long id) {
        TeamModel equipo = teamService.buscarPorId(id);
        return ResponseEntity.ok(equipo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamModel> actualizar(@PathVariable Long id, @Valid @RequestBody TeamDTO dto) {
        TeamModel equipoActualizado = teamService.actualizarEquipo(id, dto);
        return ResponseEntity.ok(equipoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable Long id) {
        teamService.desactivarEquipo(id);
        return ResponseEntity.noContent().build();
    }
}
asd