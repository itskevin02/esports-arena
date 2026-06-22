package com.project.msvc_notification.service;

import com.project.msvc_notification.model.Notification;
import com.project.msvc_notification.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationService notificationService;

    @Test
    void listarNotificationsDebeRetornarLista() {

        Notification notification = new Notification();
        notification.setMensaje("Partida iniciada");

        when(notificationRepository.findAll()).thenReturn(List.of(notification));

        List<Notification> resultado = notificationService.listarNotifications();

        assertEquals(1, resultado.size());
        assertEquals("Partida iniciada", resultado.get(0).getMensaje());

        verify(notificationRepository, times(1)).findAll();
    }

    @Test
    void guardarNotificationDebeGuardarCorrectamente() {

        Notification notification = new Notification();
        notification.setMensaje("Partida iniciada");

        when(notificationRepository.save(notification)).thenReturn(notification);

        Notification resultado = notificationService.guardarNotification(notification);

        assertEquals("Partida iniciada", resultado.getMensaje());

        verify(notificationRepository, times(1)).save(notification);
    }
}