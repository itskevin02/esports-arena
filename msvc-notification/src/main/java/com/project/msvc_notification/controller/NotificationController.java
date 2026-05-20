package com.project.msvc_notification.controller;

import com.project.msvc_notification.model.Notification;
import com.project.msvc_notification.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public List<Notification> listarNotifications() {
        return notificationService.listarNotifications();
    }

    @PostMapping
    public Notification guardarNotification(@Valid @RequestBody Notification notification) {
        return notificationService.guardarNotification(notification);
    }

    @GetMapping("/{id}")
    public Notification buscarNotification(@PathVariable Long id) {
        return notificationService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Notification actualizarNotification(@PathVariable Long id,
                                               @RequestBody Notification notification) {

        return notificationService.actualizarNotification(id, notification);
    }

    @DeleteMapping("/{id}")
    public void eliminarNotification(@PathVariable Long id) {
        notificationService.eliminarNotification(id);
    }
}