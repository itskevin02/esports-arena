package com.project.msvc_notification_service.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NotificationDTO {

    @NotNull(message = "El ID del usuario destinatario es obligatorio.")
    private Long usuarioId;

    @NotBlank(message = "El título de la notificación no puede estar vacío.")
    private String titulo;

    @NotBlank(message = "El mensaje o cuerpo no puede estar vacío.")
    private String mensaje;

    @NotBlank(message = "El tipo de evento de origen es obligatorio.")
    private String tipoEvento;

    @NotBlank(message = "El canal de envío (EMAIL, PUSH, SMS) es obligatorio.")
    private String canal;

    // Getters y Setters
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public String getTipoEvento() { return tipoEvento; }
    public void setTipoEvento(String tipoEvento) { this.tipoEvento = tipoEvento; }
    public String getCanal() { return canal; }
    public void setCanal(String canal) { this.canal = canal; }
}