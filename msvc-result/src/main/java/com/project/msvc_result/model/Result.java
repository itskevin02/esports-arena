package com.project.msvc_result.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La partida es obligatoria")
    private Long partidaId;

    @NotNull(message = "El ganador es obligatorio")
    private Long ganadorId;

    @NotNull(message = "El puntaje A es obligatorio")
    private Integer puntajeA;

    @NotNull(message = "El puntaje B es obligatorio")
    private Integer puntajeB;

    @NotBlank(message = "El estado de validacion es obligatorio")
    private String estadoValidacion;

    public Result() {
    }

    public Result(Long id, Long partidaId,
                  Long ganadorId, Integer puntajeA,
                  Integer puntajeB, String estadoValidacion) {

        this.id = id;
        this.partidaId = partidaId;
        this.ganadorId = ganadorId;
        this.puntajeA = puntajeA;
        this.puntajeB = puntajeB;
        this.estadoValidacion = estadoValidacion;
    }

    public Long getId() {
        return id;
    }

    public Long getPartidaId() {
        return partidaId;
    }

    public Long getGanadorId() {
        return ganadorId;
    }

    public Integer getPuntajeA() {
        return puntajeA;
    }

    public Integer getPuntajeB() {
        return puntajeB;
    }

    public String getEstadoValidacion() {
        return estadoValidacion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPartidaId(Long partidaId) {
        this.partidaId = partidaId;
    }

    public void setGanadorId(Long ganadorId) {
        this.ganadorId = ganadorId;
    }

    public void setPuntajeA(Integer puntajeA) {
        this.puntajeA = puntajeA;
    }

    public void setPuntajeB(Integer puntajeB) {
        this.puntajeB = puntajeB;
    }

    public void setEstadoValidacion(String estadoValidacion) {
        this.estadoValidacion = estadoValidacion;
    }
}