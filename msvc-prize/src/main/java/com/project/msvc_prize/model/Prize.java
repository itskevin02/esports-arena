package com.project.msvc_prize.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Prize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El torneo es obligatorio")
    private Long torneoId;

    @NotNull(message = "La posicion es obligatoria")
    private Integer posicion;

    @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;

    @NotNull(message = "El valor es obligatorio")
    private Double valor;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    public Prize() {
    }

    public Prize(Long id, Long torneoId,
                 Integer posicion, String descripcion,
                 Double valor, String estado) {

        this.id = id;
        this.torneoId = torneoId;
        this.posicion = posicion;
        this.descripcion = descripcion;
        this.valor = valor;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public Long getTorneoId() {
        return torneoId;
    }

    public Integer getPosicion() {
        return posicion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Double getValor() {
        return valor;
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

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}