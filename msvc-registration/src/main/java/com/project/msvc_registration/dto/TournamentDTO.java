package com.project.msvc_registration.dto;

public class TournamentDTO {

    private Long id;
    private String nombre;
    private String ubicacion;
    private Integer cantidadEquipos;
    private String estado;

    public TournamentDTO() {
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