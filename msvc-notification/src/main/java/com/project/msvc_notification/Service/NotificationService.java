package com.project.msvc_notification_service.Service;

import com.project.msvc_notification_service.DTO.NotificationDTO;
import com.project.msvc_notification_service.Exception.NotificationException;
import com.project.msvc_notification_service.Model.NotificationModel;
import com.project.msvc_notification_service.Repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    private NotificationRepository notificationRepository;

    @Transactional
    public NotificationModel despacharNotificacion(NotificationDTO dto) {
        log.info("Procesando solicitud de notificación para el Usuario ID: {}. Evento: {}", dto.getUsuarioId(), dto.getTipoEvento());

        // Regla de negocio interna: Validar canales soportados
        String canalUpper = dto.getCanal().toUpperCase();
        if (!canalUpper.equals("EMAIL") && !canalUpper.equals("PUSH") && !canalUpper.equals("SMS")) {
            log.error("Fallo al despachar notificación: El canal '{}' no es soportado.", dto.getCanal());
            throw new NotificationException("Canal de envío no válido. Use EMAIL, PUSH o SMS.");
        }

        NotificationModel notificacion = new NotificationModel();
        notificacion.setUsuarioId(dto.getUsuarioId());
        notificacion.setTitulo(dto.getTitulo());
        notificacion.setMensaje(dto.getMensaje());
        notificacion.setTipoEvento(dto.getTipoEvento().toUpperCase());
        notificacion.setCanal(canalUpper);
        notificacion.setFechaEnvio(LocalDateTime.now());

        // Simulación del despacho del mensaje
        try {
            log.info("Enviando mensaje de forma síncrona a través del proveedor de {}", canalUpper);
            // Aquí iría el cliente SMTP, pasarela SMS o Firebase Cloud Messaging
            notificacion.setEstado("ENVIADO");
        } catch (Exception e) {
            log.error("Error crítico de infraestructura al transmitir la alerta: {}", e.getMessage());
            notificacion.setEstado("FALLIDO");
        }

        NotificationModel guardada = notificationRepository.save(notificacion);
        log.info("Notificación guardada en historial con ID: {} y estado: {}", guardada.getId(), guardada.getEstado());
        return guardada;
    }

    @Transactional(readOnly = true)
    public List<NotificationModel> obtenerHistorialPorUsuario(Long usuarioId) {
        log.info("Consultando historial de alertas para el Usuario ID: {}", usuarioId);
        return notificationRepository.findByUsuarioId(usuarioId);
    }

    @Transactional(readOnly = true)
    public List<NotificationModel> listarTodas(String tipoEvento, String estado) {
        log.info("Listando notificaciones globales con filtros dinámicos.");
        if (tipoEvento != null) return notificationRepository.findByTipoEvento(tipoEvento.toUpperCase());
        if (estado != null) return notificationRepository.findByEstado(estado.toUpperCase());
        return notificationRepository.findAll();
    }
}