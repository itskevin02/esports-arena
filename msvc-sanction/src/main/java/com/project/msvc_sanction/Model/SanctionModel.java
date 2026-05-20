package com.project.msvc_sanction_service.Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "sanciones")
public class SanctionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Se registra el ID de usuario o el ID de equipo de manera independiente
    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "equipo_id")
    private Long equipoId;

    @Column(nullable = false)
    private String motivo;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(nullable = false)
    private String estado; // Ejemplo: "ACTIVA", "CERRADA"

    @Column(nullable = false)
    private String severidad; // Ejemplo: "LEVE", "GRAVE", "BLOQUEANTE"

    // Constructores
    public SanctionModel() {}

    public SanctionModel(Long id, Long usuarioId, Long equipoId, String motivo, LocalDate fechaInicio, LocalDate fechaFin, String estado, String severidad) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.equipoId = equipoId;
        this.motivo = motivo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.severidad = severidad;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public Long getEquipoId() { return equipoId; }
    public void setEquipoId(Long equipoId) { this.equipoId = equipoId; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getSeveridad() { return severidad; }
    public void setSeveridad(String severidad) { this.severidad = severidad; }
}