package com.project.msvc_team_service.Model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "equipos")
public class TeamModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "capitan_id", nullable = false)
    private Long capitanId;

    @Column(name = "juego_principal_id", nullable = false)
    private Long juegoPrincipalId;

    @Column(nullable = false)
    private String estado; // Ejemplo: "ACTIVO", "INACTIVO"

    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TeamMemberModel> integrantes = new ArrayList<>();

    // Constructores
    public TeamModel() {}

    public TeamModel(Long id, String nombre, Long capitanId, Long juegoPrincipalId, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.capitanId = capitanId;
        this.juegoPrincipalId = juegoPrincipalId;
        this.estado = estado;
    }

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
    public List<TeamMemberModel> getIntegrantes() { return integrantes; }
    public void setIntegrantes(List<TeamMemberModel> integrantes) { this.integrantes = integrantes; }
}
asd