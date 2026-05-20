package com.project.msvc_notification_service.Repository;

import com.project.msvc_notification_service.Model.NotificationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationModel, Long> {
    // Filtros de auditoría útiles para la plataforma
    List<NotificationModel> findByUsuarioId(Long usuarioId);
    List<NotificationModel> findByTipoEvento(String tipoEvento);
    List<NotificationModel> findByEstado(String estado);
}