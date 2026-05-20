package com.project.msvc_team_service.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;

public class TeamDTO {

    private Long id;

    @NotBlank(message = "El nombre del equipo no puede estar vacío.")
    private String nombre;

    @NotNull(message = "El ID del capitán es obligatorio.")
    @Positive(message = "El ID del capitán debe ser un número positivo.")
    private Long capitanId;

    @NotNull(message = "El ID del juego principal es obligatorio.")
    @Positive(message = "El ID del juego debe ser un número positivo.")
    private Long juegoPrincipalId;

    @NotBlank(message = "El estado no puede estar vacío.")
    private String estado;

    private List<TeamMemberDTO> integrantes;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Long getCapitanId() { return capitanId; }
    public void setCapitanId(Long capitanId) { this.capitanId = capitanId; }
    public Long getJuegoPrincipalId() { return juegoPrincipalId; }
    public void setJuegoPrincipalId(Long juegoPrincipalId) { this.juegoPrincipalId = juegoPrincipalId; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public List<TeamMemberDTO> getIntegrantes() { return integrantes; }
    public void setIntegrantes(List<TeamMemberDTO> integrantes) { this.integrantes = integrantes; }
}
asd