package com.project.msvc_result.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "resultados")
public class ResultModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long partidaId;

    @Column(nullable = false)
    private Long ganadorId;

    @Column(nullable = false)
    private Integer puntajeA;

    @Column(nullable = false)
    private Integer puntajeB;

    @Column(nullable = false)
    private String estadoValidacion; // "PENDIENTE", "VALIDADO", "ANULADO"

    private String justificacionAnulacion;

    @Column(nullable = false)
    private LocalDateTime fechaRegistro;

    // Constructores
    public ResultModel() {}

    public ResultModel(Long partidaId, Long ganadorId, Integer puntajeA, Integer puntajeB, String estadoValidacion, LocalDateTime fechaRegistro) {
        this.partidaId = partidaId;
        this.ganadorId = ganadorId;
        this.puntajeA = puntajeA;
        this.puntajeB = puntajeB;
        this.estadoValidacion = estadoValidacion;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getPartidaId() { return partidaId; }
    public void setPartidaId(Long partidaId) { this.partidaId = partidaId; }
    public Long getGanadorId() { return ganadorId; }
    public void setGanadorId(Long ganadorId) { this.ganadorId = ganadorId; }
    public Integer getPuntajeA() { return puntajeA; }
    public void setPuntajeA(Integer puntajeA) { this.puntajeA = puntajeA; }
    public Integer getPuntajeB() { return puntajeB; }
    public void setPuntajeB(Integer puntajeB) { this.puntajeB = puntajeB; }
    public String getEstadoValidacion() { return estadoValidacion; }
    public void setEstadoValidacion(String estadoValidacion) { this.estadoValidacion = estadoValidacion; }
    public String getJustificacionAnulacion() { return justificacionAnulacion; }
    public void setJustificacionAnulacion(String justificacionAnulacion) { this.justificacionAnulacion = justificacionAnulacion; }
    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}