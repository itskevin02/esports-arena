package com.project.msvc_notification.service;

import com.project.msvc_notification.model.Notification;
import com.project.msvc_notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> listarNotifications() {

        logger.info("Listando notificaciones");

        return notificationRepository.findAll();
    }

    public Notification guardarNotification(Notification notification) {

        logger.info("Guardando notificacion");

        return notificationRepository.save(notification);
    }

    public Notification buscarPorId(Long id) {

        logger.info("Buscando notificacion con id: " + id);

        return notificationRepository.findById(id).orElse(null);
    }

    public Notification actualizarNotification(Long id, Notification notification) {

        logger.info("Actualizando notificacion con id: " + id);

        Notification notificationExistente = notificationRepository.findById(id).orElse(null);

        if (notificationExistente != null) {

            notificationExistente.setUsuarioId(notification.getUsuarioId());
            notificationExistente.setMensaje(notification.getMensaje());
            notificationExistente.setTipo(notification.getTipo());
            notificationExistente.setEstado(notification.getEstado());

            return notificationRepository.save(notificationExistente);
        }

        return null;
    }

    public void eliminarNotification(Long id) {

        logger.info("Eliminando notificacion con id: " + id);

        notificationRepository.deleteById(id);
    }
}