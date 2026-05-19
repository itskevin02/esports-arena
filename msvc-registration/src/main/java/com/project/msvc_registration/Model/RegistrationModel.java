package com.project.msvc_registration.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inscripciones")
public class RegistrationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long torneoId;

    private Long equipoId; // Puede ser null si es participación individual
    private Long jugadorId; // Puede ser null si es participación por equipo

    @Column(nullable = false)
    private String tipoParticipante; // "EQUIPO" o "JUGADOR"

    @Column(nullable = false)
    private String estado; // "PENDIENTE", "ACEPTADA", "CANCELADA"

    @Column(nullable = false)
    private LocalDateTime fechaInscripcion;

    // Constructores
    public RegistrationModel() {}

    public RegistrationModel(Long torneoId, Long equipoId, Long jugadorId, String tipoParticipante, String estado, LocalDateTime fechaInscripcion) {
        this.torneoId = torneoId;
        this.equipoId = equipoId;
        this.jugadorId = jugadorId;
        this.tipoParticipante = tipoParticipante;
        this.estado = estado;
        this.fechaInscripcion = fechaInscripcion;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getTorneoId() { return torneoId; }
    public void setTorneoId(Long torneoId) { this.torneoId = torneoId; }
    public Long getEquipoId() { return equipoId; }
    public void setEquipoId(Long equipoId) { this.equipoId = equipoId; }
    public Long getJugadorId() { return jugadorId; }
    public void setJugadorId(Long jugadorId) { this.jugadorId = jugadorId; }
    public String getTipoParticipante() { return tipoParticipante; }
    public void setTipoParticipante(String tipoParticipante) { this.tipoParticipante = tipoParticipante; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public LocalDateTime getFechaInscripcion() { return fechaInscripcion; }
    public void setFechaInscripcion(LocalDateTime fechaInscripcion) { this.fechaInscripcion = fechaInscripcion; }
}
asd