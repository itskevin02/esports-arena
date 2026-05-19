package com.project.msvc_match.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "partidas")
public class MatchModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long torneoId;

    @Column(nullable = false)
    private Long participanteAId;

    @Column(nullable = false)
    private Long participanteBId;

    @Column(nullable = false)
    private Integer ronda;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @Column(nullable = false)
    private String estado; // "PROGRAMADA", "EN_CURSO", "FINALIZADA", "CANCELADA"

    // Constructores
    public MatchModel() {}

    public MatchModel(Long torneoId, Long participanteAId, Long participanteBId, Integer ronda, LocalDateTime fechaHora, String estado) {
        this.torneoId = torneoId;
        this.participanteAId = participanteAId;
        this.participanteBId = participanteBId;
        this.ronda = ronda;
        this.fechaHora = fechaHora;
        this.estado = estado;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}