package com.project.msvc_sanction.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Sanction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El usuario es obligatorio")
    private Long usuarioId;

    @NotBlank(message = "El motivo es obligatorio")
    private String motivo;

    @NotBlank(message = "La severidad es obligatoria")
    private String severidad;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    public Sanction() {
    }

    public Sanction(Long id, Long usuarioId,
                    String motivo, String severidad,
                    String estado) {

        this.id = id;
        this.usuarioId = usuarioId;
        this.motivo = motivo;
        this.severidad = severidad;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getSeveridad() {
        return severidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setSeveridad(String severidad) {
        this.severidad = severidad;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}