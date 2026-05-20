package com.project.msvc_prize_service.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PrizeDTO {

    private Long id;

    @NotBlank(message = "El nombre del premio es obligatorio.")
    private String nombre;

    @NotBlank(message = "El tipo de premio (ej. MONETARIO, ARTICULO) es obligatorio.")
    private String tipo;

    @NotNull(message = "El valor del premio no puede ser nulo.")
    @DecimalMin(value = "0.0", inclusive = true, message = "El valor del premio no puede ser negativo.")
    private BigDecimal valor;

    @NotNull(message = "El ID del torneo asociado es obligatorio.")
    private Long torneoId;

    private Long ganadorId;

    private String estado;

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