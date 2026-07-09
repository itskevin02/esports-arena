package com.project.msvc_notification.service;

import com.project.msvc_notification.model.Notification;
import com.project.msvc_notification.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationService notificationService;

    @Test
    void listarNotificationsTest() {

        Notification notification = new Notification();
        notification.setMensaje("Notificación 1");

        when(notificationRepository.findAll()).thenReturn(Arrays.asList(notification));

        assertEquals(1, notificationService.listarNotifications().size());

        verify(notificationRepository, times(1)).findAll();
    }

    @Test
    void buscarNotificationPorIdTest() {

        Notification notification = new Notification();
        notification.setId(1L);
        notification.setMensaje("Notificación 1");

        when(notificationRepository.findById(1L)).thenReturn(Optional.of(notification));

        Notification resultado = notificationService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("Notificación 1", resultado.getMensaje());

        verify(notificationRepository, times(1)).findById(1L);
    }

    @Test
    void guardarNotificationTest() {

        Notification notification = new Notification();
        notification.setMensaje("Nueva Notificación");

        when(notificationRepository.save(notification)).thenReturn(notification);

        Notification resultado = notificationService.guardarNotification(notification);

        assertNotNull(resultado);
        assertEquals("Nueva Notificación", resultado.getMensaje());

        verify(notificationRepository, times(1)).save(notification);
    }

    @Test
    void actualizarNotificationTest() {

        Notification notificationExistente = new Notification();
        notificationExistente.setId(1L);
        notificationExistente.setUsuarioId(1L);
        notificationExistente.setMensaje("Mensaje 1");
        notificationExistente.setTipo("Correo");
        notificationExistente.setEstado("Activo");

        Notification nuevaNotification = new Notification();
        nuevaNotification.setUsuarioId(2L);
        nuevaNotification.setMensaje("Mensaje Actualizado");
        nuevaNotification.setTipo("Sistema");
        nuevaNotification.setEstado("Inactivo");

        when(notificationRepository.findById(1L)).thenReturn(Optional.of(notificationExistente));
        when(notificationRepository.save(any(Notification.class))).thenReturn(notificationExistente);

        Notification resultado = notificationService.actualizarNotification(1L, nuevaNotification);

        assertNotNull(resultado);
        assertEquals(2L, resultado.getUsuarioId());
        assertEquals("Mensaje Actualizado", resultado.getMensaje());
        assertEquals("Sistema", resultado.getTipo());
        assertEquals("Inactivo", resultado.getEstado());

        verify(notificationRepository, times(1)).findById(1L);
        verify(notificationRepository, times(1)).save(any(Notification.class));
    }

    @Test
    void eliminarNotificationTest() {

        notificationService.eliminarNotification(1L);

        verify(notificationRepository, times(1)).deleteById(1L);
    }

}