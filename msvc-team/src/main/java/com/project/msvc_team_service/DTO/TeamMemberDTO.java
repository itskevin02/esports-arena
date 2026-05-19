package com.project.msvc_team_service.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TeamMemberDTO {

    @NotNull(message = "El ID de usuario es obligatorio.")
    private Long usuarioId;

    @NotBlank(message = "El rol dentro del equipo es obligatorio.")
    private String rolDentroEquipo;

    // Getters y Setters
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public String getRolDentroEquipo() { return rolDentroEquipo; }
    public void setRolDentroEquipo(String rolDentroEquipo) { this.rolDentroEquipo = rolDentroEquipo; }
}