package com.project.msvc_match.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El torneo es obligatorio")
    private Long torneoId;

    @NotNull(message = "El participante A es obligatorio")
    private Long participanteAId;

    @NotNull(message = "El participante B es obligatorio")
    private Long participanteBId;

    @NotBlank(message = "La ronda es obligatoria")
    private String ronda;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    public Match() {
    }

    public Match(Long id, Long torneoId,
                 Long participanteAId, Long participanteBId,
                 String ronda, String estado) {

        this.id = id;
        this.torneoId = torneoId;
        this.participanteAId = participanteAId;
        this.participanteBId = participanteBId;
        this.ronda = ronda;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public Long getTorneoId() {
        return torneoId;
    }

    public Long getParticipanteAId() {
        return participanteAId;
    }

    public Long getParticipanteBId() {
        return participanteBId;
    }

    public String getRonda() {
        return ronda;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTorneoId(Long torneoId) {
        this.torneoId = torneoId;
    }

    public void setParticipanteAId(Long participanteAId) {
        this.participanteAId = participanteAId;
    }

    public void setParticipanteBId(Long participanteBId) {
        this.participanteBId = participanteBId;
    }

    public void setRonda(String ronda) {
        this.ronda = ronda;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}