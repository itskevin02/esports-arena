package com.project.msvc_match.DTO;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class MatchDTO {

    @NotNull(message = "El ID del torneo es obligatorio")
    private Long torneoId;

    @NotNull(message = "El ID del participante A es obligatorio")
    private Long participanteAId;

    @NotNull(message = "El ID del participante B es obligatorio")
    private Long participanteBId;

    @NotNull(message = "El número de ronda es obligatorio")
    private Integer ronda;

    @NotNull(message = "La fecha y hora de la partida es obligatoria")
    @Future(message = "La partida debe programarse para una fecha futura")
    private LocalDateTime fechaHora;

    // Getters y Setters
    public Long getTorneoId() { return torneoId; }
    public void setTorneoId(Long torneoId) { this.torneoId = torneoId; }
    public Long getParticipanteAId() { return participanteAId; }
    public void setParticipanteAId(Long participanteAId) { this.participanteAId = participanteAId; }
    public Long getParticipanteBId() { return participanteBId; }
    public void setParticipanteBId(Long participanteBId) { this.participanteBId = participanteBId; }
    public Integer getRonda() { return ronda; }
    public void setRonda(Integer ronda) { this.ronda = ronda; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
}