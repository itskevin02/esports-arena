package com.project.msvc_prize_service.Model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "premios")
public class PrizeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre; // Ej: "Primer Lugar", "MVP del Torneo", "Premio de Consolación"

    @Column(nullable = false)
    private String tipo; // Ej: "MONETARIO", "ARTICULO", "TROFEO"

    @Column(precision = 10, scale = 2)
    private BigDecimal valor; // Monto en dinero o valor estimado del artículo

    @Column(name = "torneo_id", nullable = false)
    private Long torneoId; // ID del torneo al que pertenece el premio

    // Flujo de asignación al finalizar el torneo
    @Column(name = "ganador_id")
    private Long ganadorId; // ID del equipo o jugador que ganó (puede ser null hasta que finalice)

    @Column(nullable = false)
    private String estado; // Ej: "DISPONIBLE", "ASIGNADO", "ENTREGADO"

    // Constructores
    public PrizeModel() {}

    public PrizeModel(Long id, String nombre, String tipo, BigDecimal valor, Long torneoId, Long ganadorId, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.valor = valor;
        this.torneoId = torneoId;
        this.ganadorId = ganadorId;
        this.estado = estado;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public Long getTorneoId() { return torneoId; }
    public void setTorneoId(Long torneoId) { this.torneoId = torneoId; }
    public Long getGanadorId() { return ganadorId; }
    public void setGanadorId(Long ganadorId) { this.ganadorId = ganadorId; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}