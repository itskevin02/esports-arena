package com.project.msvc_match.Service;

import com.project.msvc_match.DTO.MatchDTO;
import com.project.msvc_match.Exception.MatchException;
import com.project.msvc_match.Model.MatchModel;
import com.project.msvc_match.Repository.MatchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    private static final Logger log = LoggerFactory.getLogger(MatchService.class);

    @Autowired
    private MatchRepository repository;

    public MatchModel crearPartida(MatchDTO dto) {
        log.info("Intentando programar una nueva partida para el torneo ID: {}, Ronda: {}", dto.getTorneoId(), dto.getRonda());

        // 1. Regla de negocio: No enfrentar a un participante consigo mismo
        if (dto.getParticipanteAId().equals(dto.getParticipanteBId())) {
            log.error("Validación fallida: Un participante no puede jugar contra sí mismo.");
            throw new MatchException("El participante A y el participante B no pueden ser el mismo.");
        }

        // 2. Regla de negocio: Evitar duplicar enfrentamiento en la misma ronda
        if (repository.existsByTorneoIdAndRondaAndParticipanteAIdAndParticipanteBId(
                dto.getTorneoId(), dto.getRonda(), dto.getParticipanteAId(), dto.getParticipanteBId())) {
            log.error("Validación fallida: El enfrentamiento ya existe en esta ronda.");
            throw new MatchException("Este enfrentamiento ya se encuentra programado para la misma ronda.");
        }

        // 3. Simulación de comunicación externa (Feign / WebClient)
        // TODO: Validar en registration-service si los participantes están realmente inscritos y activos
        boolean participantesInscritos = true;
        if (!participantesInscritos) {
            log.error("Validación fallida: Uno o ambos participantes no se encuentran debidamente inscritos.");
            throw new MatchException("No se puede crear la partida: Participantes no válidos o no inscritos.");
        }

        MatchModel nuevaPartida = new MatchModel(
                dto.getTorneoId(),
                dto.getParticipanteAId(),
                dto.getParticipanteBId(),
                dto.getRonda(),
                dto.getFechaHora(),
                "PROGRAMADA"
        );

        MatchModel guardado = repository.save(nuevaPartida);
        log.info("Partida guardada exitosamente con el ID: {}", guardado.getId());
        return guardado;
    }

    public List<MatchModel> listarTodas() {
        return repository.findAll();
    }

    public List<MatchModel> listarPorTorneo(Long torneoId) {
        return repository.findByTorneoId(torneoId);
    }

    public MatchModel buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new MatchException("Partida no encontrada con el ID: " + id));
    }

    public MatchModel actualizarEstado(Long id, String nuevoEstado) {
        log.info("Solicitud para cambiar el estado de la partida ID: {} a {}", id, nuevoEstado);
        MatchModel partida = buscarPorId(id);

        // Regla de negocio mínima: No iniciar una partida que ya fue cancelada
        if ("CANCELADA".equals(partida.getEstado()) && "EN_CURSO".equals(nuevoEstado)) {
            log.error("Error de negocio: Intento inválido de iniciar una partida previamente cancelada.");
            throw new MatchException("Operación no permitida: No se puede iniciar una partida que ha sido cancelada.");
        }

        partida.setEstado(nuevoEstado);
        return repository.save(partida);
    }

    public void cancelarPartida(Long id) {
        log.info("Cancelando de forma definitiva la partida ID: {}", id);
        MatchModel partida = buscarPorId(id);
        partida.setEstado("CANCELADA");
        repository.save(partida);
    }
}