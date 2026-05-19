package com.project.msvc_result.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ResultDTO {

    @NotNull(message = "El ID de la partida es obligatorio")
    private Long partidaId;

    @NotNull(message = "El ID del ganador es obligatorio")
    private Long ganadorId;

    @NotNull(message = "El puntaje del participante A es obligatorio")
    @Min(value = 0, message = "El puntaje no puede ser negativo")
    private Integer puntajeA;

    @NotNull(message = "El puntaje del participante B es obligatorio")
    @Min(value = 0, message = "El puntaje no puede ser negativo")
    private Integer puntajeB;

    // Getters y Setters
    public Long getPartidaId() { return partidaId; }
    public void setPartidaId(Long partidaId) { this.partidaId = partidaId; }
    public Long getGanadorId() { return ganadorId; }
    public void setGanadorId(Long ganadorId) { this.ganadorId = ganadorId; }
    public Integer getPuntajeA() { return puntajeA; }
    public void setPuntajeA(Integer puntajeA) { this.puntajeA = puntajeA; }
    public Integer getPuntajeB() { return puntajeB; }
    public void setPuntajeB(Integer puntajeB) { this.puntajeB = puntajeB; }
}