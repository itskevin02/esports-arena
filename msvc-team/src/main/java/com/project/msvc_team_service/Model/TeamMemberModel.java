package com.project.msvc_team_service.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "miembros_equipo")
public class TeamMemberModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipo_id", nullable = false)
    @JsonIgnore
    private TeamModel equipo;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "rol_dentro_equipo", nullable = false)
    private String rolDentroEquipo; // Ejemplo: "CAPITAN", "JUGADOR"

    // Constructores
    public TeamMemberModel() {}

    public TeamMemberModel(TeamModel equipo, Long usuarioId, String rolDentroEquipo) {
        this.equipo = equipo;
        this.usuarioId = usuarioId;
        this.rolDentroEquipo = rolDentroEquipo;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public TeamModel getEquipo() { return equipo; }
    public void setEquipo(TeamModel equipo) { this.equipo = equipo; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public String getRolDentroEquipo() { return rolDentroEquipo; }
    public void setRolDentroEquipo(String rolDentroEquipo) { this.rolDentroEquipo = rolDentroEquipo; }
}