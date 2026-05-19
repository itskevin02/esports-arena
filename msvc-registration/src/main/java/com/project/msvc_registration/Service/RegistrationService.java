package com.project.msvc_registration.Service;

import com.project.msvc_registration.DTO.RegistrationDTO;
import com.project.msvc_registration.Exception.RegistrationException;
import com.project.msvc_registration.Model.RegistrationModel;
import com.project.msvc_registration.Repository.RegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegistrationService {

    private static final Logger log = LoggerFactory.getLogger(RegistrationService.class);

    @Autowired
    private RegistrationRepository repository;

    public RegistrationModel crearInscripcion(RegistrationDTO dto) {
        log.info("Iniciando proceso de inscripción para el torneo ID: {}", dto.getTorneoId());

        // 1. Regla: Validar Duplicados
        if ("JUGADOR".equals(dto.getTipoParticipante()) && repository.existsByTorneoIdAndJugadorId(dto.getTorneoId(), dto.getJugadorId())) {
            log.error("Validación fallida: El jugador {} ya está inscrito en este torneo", dto.getJugadorId());
            throw new RegistrationException("El jugador ya se encuentra inscrito en este torneo.");
        }
        if ("EQUIPO".equals(dto.getTipoParticipante()) && repository.existsByTorneoIdAndEquipoId(dto.getTorneoId(), dto.getEquipoId())) {
            log.error("Validación fallida: El equipo {} ya está inscrito en este torneo", dto.getEquipoId());
            throw new RegistrationException("El equipo ya se encuentra inscrito en este torneo.");
        }

        // 2. Simulaciones de lógica de negocio (Sanciones, Cupos y Fechas)
        // TODO: En la integración final, estos datos vendrán vía OpenFeign/WebClient
        boolean participanteSancionado = false;
        boolean torneoFueraDePlazo = false;
        boolean torneoLleno = false;

        if (participanteSancionado) {
            log.error("Validación fallida: Participante sancionado intentó inscribirse.");
            throw new RegistrationException("No se puede inscribir: El participante posee una sanción activa.");
        }
        if (torneoFueraDePlazo) {
            log.error("Validación fallida: Intento de inscripción fuera de plazo.");
            throw new RegistrationException("El periodo de inscripciones para este torneo ha finalizado.");
        }
        if (torneoLleno) {
            log.error("Validación fallida: Cupos agotados.");
            throw new RegistrationException("No hay cupos disponibles para este torneo.");
        }

        // Mapear DTO a Modelo
        RegistrationModel nuevaInscripcion = new RegistrationModel(
                dto.getTorneoId(),
                dto.getEquipoId(),
                dto.getJugadorId(),
                dto.getTipoParticipante(),
                "ACEPTADA",
                LocalDateTime.now()
        );

        RegistrationModel guardado = repository.save(nuevaInscripcion);
        log.info("Inscripción creada con éxito de manera exitosa. ID asignado: {}", guardado.getId());
        return guardado;
    }

    public List<RegistrationModel> listarTodas() {
        return repository.findAll();
    }

    public List<RegistrationModel> listarPorTorneo(Long torneoId) {
        return repository.findByTorneoId(torneoId);
    }

    public RegistrationModel buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RegistrationException("Inscripción no encontrada con el ID: " + id));
    }

    public RegistrationModel actualizarEstado(Long id, String nuevoEstado) {
        log.info("Actualizando estado de la inscripción ID: {} a {}", id, nuevoEstado);
        RegistrationModel inscripcion = buscarPorId(id);
        inscripcion.setEstado(nuevoEstado);
        return repository.save(inscripcion);
    }

    public void cancelarInscripcion(Long id) {
        log.info("Cancelando inscripción ID: {}", id);
        RegistrationModel inscripcion = buscarPorId(id);
        inscripcion.setEstado("CANCELADA");
        repository.save(inscripcion);
    }
}