package com.project.msvc_tournament.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del torneo es obligatorio")
    private String nombre;

    @NotBlank(message = "La ubicacion es obligatoria")
    private String ubicacion;

    @Positive(message = "La cantidad de equipos debe ser mayor a 0")
    private Integer cantidadEquipos;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    public Tournament() {
    }

    public Tournament(Long id, String nombre, String ubicacion, Integer cantidadEquipos, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.cantidadEquipos = cantidadEquipos;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public Integer getCantidadEquipos() {
        return cantidadEquipos;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setCantidadEquipos(Integer cantidadEquipos) {
        this.cantidadEquipos = cantidadEquipos;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}