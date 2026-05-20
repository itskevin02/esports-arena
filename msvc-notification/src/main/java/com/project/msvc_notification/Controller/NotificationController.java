package com.project.msvc_notification_service.Controller;

import com.project.msvc_notification_service.DTO.NotificationDTO;
import com.project.msvc_notification_service.Model.NotificationModel;
import com.project.msvc_notification_service.Service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // Endpoint clave para ser consumido por msvc-team, msvc-sanction o msvc-prize
    @PostMapping("/enviar")
    public ResponseEntity<NotificationModel> enviarNotificacion(@Valid @RequestBody NotificationDTO dto) {
        NotificationModel nuevaNotificacion = notificationService.despacharNotificacion(dto);
        return new ResponseEntity<>(nuevaNotificacion, HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<NotificationModel>> obtenerPorUsuario(@PathVariable Long usuarioId) {
        List<NotificationModel> historial = notificationService.obtenerHistorialPorUsuario(usuarioId);
        return ResponseEntity.ok(historial);
    }

    @GetMapping
    public ResponseEntity<List<NotificationModel>> listarTodas(
            @RequestParam(required = false) String tipoEvento,
            @RequestParam(required = false) String estado) {
        List<NotificationModel> notificaciones = notificationService.listarTodas(tipoEvento, estado);
        return ResponseEntity.ok(notificaciones);
    }
}