package com.project.msvc_registration.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El torneo es obligatorio")
    private Long torneoId;

    @NotNull(message = "El equipo es obligatorio")
    private Long equipoId;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    public Registration() {
    }

    public Registration(Long id, Long torneoId,
                        Long equipoId, String estado) {

        this.id = id;
        this.torneoId = torneoId;
        this.equipoId = equipoId;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public Long getTorneoId() {
        return torneoId;
    }

    public Long getEquipoId() {
        return equipoId;
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

    public void setEquipoId(Long equipoId) {
        this.equipoId = equipoId;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}