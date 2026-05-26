package com.project.msvc_notification.controller;

import com.project.msvc_notification.model.Notification;
import com.project.msvc_notification.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<Notification>> listar() {

        List<Notification> lista = notificationService.listarNotifications();

        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<Notification> guardar(@Valid @RequestBody Notification notification) {

        Notification guardado = notificationService.guardarNotification(notification);

        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> buscarPorId(@PathVariable Long id) {

        Notification notification = notificationService.buscarPorId(id);

        if (notification != null) {
            return ResponseEntity.ok(notification);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notification> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Notification notification
    ) {

        Notification actualizado =
                notificationService.actualizarNotification(id, notification);

        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        notificationService.eliminarNotification(id);

        return ResponseEntity.noContent().build();
    }
}