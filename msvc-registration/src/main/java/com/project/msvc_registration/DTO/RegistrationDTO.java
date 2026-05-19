package com.project.msvc_registration.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class RegistrationDTO {

    @NotNull(message = "El ID del torneo es obligatorio")
    private Long torneoId;

    private Long equipoId;
    private Long jugadorId;

    @NotBlank(message = "El tipo de participante es obligatorio")
    @Pattern(regexp = "JUGADOR|EQUIPO", message = "El tipo debe ser 'JUGADOR' o 'EQUIPO'")
    private String tipoParticipante;

    // Getters y Setters
    public Long getTorneoId() { return torneoId; }
    public void setTorneoId(Long torneoId) { this.torneoId = torneoId; }
    public Long getEquipoId() { return equipoId; }
    public void setEquipoId(Long equipoId) { this.equipoId = equipoId; }
    public Long getJugadorId() { return jugadorId; }
    public void setJugadorId(Long jugadorId) { this.jugadorId = jugadorId; }
    public String getTipoParticipante() { return tipoParticipante; }
    public void setTipoParticipante(String tipoParticipante) { this.tipoParticipante = tipoParticipante; }
}