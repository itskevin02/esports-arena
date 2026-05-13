package com.project.msvc_game.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Juego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String genero;
    private String modalidad;
    private Integer jugadoresPorEquipo;
    private String estado;

    public Juego() {
    }

    public Juego(Long id, String nombre, String genero, String modalidad, Integer jugadoresPorEquipo, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.modalidad = modalidad;
        this.jugadoresPorEquipo = jugadoresPorEquipo;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public Integer getJugadoresPorEquipo() {
        return jugadoresPorEquipo;
    }

    public void setJugadoresPorEquipo(Integer jugadoresPorEquipo) {
        this.jugadoresPorEquipo = jugadoresPorEquipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}