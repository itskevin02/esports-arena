package com.project.msvc_match.Service;

import com.project.msvc_match.DTO.MatchDTO;
import com.project.msvc_match.Exception.MatchException;
import com.project.msvc_match.Model.MatchModel;
import com.project.msvc_match.Repository.MatchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchService {

    private static final Logger log = LoggerFactory.getLogger(MatchService.class);
    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    // CREAR PARTIDA
    public MatchDTO createMatch(MatchDTO dto) {
        log.info("Iniciando creación de partida para el torneo ID: {}", dto.getTorneoId());

        // REGLA DE NEGOCIO: Evitar que juegue contra sí mismo
        if (dto.getParticipanteAId().equals(dto.getParticipanteBId())) {
            log.error("Validación fallida: Un participante no puede enfrentarse a sí mismo.");
            throw new MatchException("El participante A y B no pueden ser el mismo.");
        }

        // NOTA: Aquí iría la comunicación Feign/WebClient para validar si están inscritos en registration-service
        // (Requisito: No crear partida con participante no inscrito)

        MatchModel match = new MatchModel(
                dto.getTorneoId(),
                dto.getParticipanteAId(),
                dto.getParticipanteBId(),
                dto.getRonda(),
                dto.getFechaHora(),
                "PENDIENTE"
        );

        MatchModel savedMatch = matchRepository.save(match);
        log.info("Partida creada exitosamente con ID: {}", savedMatch.getId());
        return convertToDTO(savedMatch);
    }

    // LISTAR PARTIDAS POR TORNEO
    public List<MatchDTO> listByTorneo(Long torneoId) {
        log.info("Buscando partidas para el torneo ID: {}", torneoId);
        return matchRepository.findByTorneoId(torneoId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // BUSCAR POR ID
    public MatchDTO findById(Long id) {
        log.info("Buscando partida con ID: {}", id);
        MatchModel match = matchRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Partida con ID {} no encontrada", id);
                    return new MatchException("La partida no existe.");
                });
        return convertToDTO(match);
    }

    // ACTUALIZAR PARTIDA (Horario, participantes, estado)
    public MatchDTO updateMatch(Long id, MatchDTO dto) {
        log.info("Actualizando partida con ID: {}", id);
        MatchModel match = matchRepository.findById(id)
                .orElseThrow(() -> new MatchException("La partida no existe."));

        // REGLA DE NEGOCIO: No iniciar partida cancelada
        if ("CANCELADA".equals(match.getEstado()) && "EN_CURSO".equals(dto.getEstado())) {
            log.error("Error de regla de negocio: Intento de iniciar una partida cancelada (ID: {})", id);
            throw new MatchException("No se puede iniciar una partida que ya ha sido cancelada.");
        }

        match.setParticipanteAId(dto.getParticipanteAId());
        match.setParticipanteBId(dto.getParticipanteBId());
        match.setFechaHora(dto.getFechaHora());
        if (dto.getEstado() != null) {
            match.setEstado(dto.getEstado());
        }

        MatchModel updatedMatch = matchRepository.save(match);
        log.info("Partida ID: {} actualizada correctamente", id);
        return convertToDTO(updatedMatch);
    }

    // CANCELAR PARTIDA (Desactivación lógica de registro)
    public void cancelMatch(Long id) {
        log.info("Solicitud para cancelar partida con ID: {}", id);
        MatchModel match = matchRepository.findById(id)
                .orElseThrow(() -> new MatchException("La partida no existe."));

        match.setEstado("CANCELADA");
        match.setRepository = matchRepository.save(match);
        log.warn("Partida ID: {} ha sido CANCELADA", id);
    }

    // Conversor auxiliar de Entidad a DTO
    private MatchDTO convertToDTO(MatchModel model) {
        MatchDTO dto = new MatchDTO();
        dto.setId(model.getId());
        dto.setTorneoId(model.getTorneoId());
        dto.setParticipanteAId(model.getParticipanteAId());
        dto.setParticipanteBId(model.getParticipanteBId());
        dto.setRonda(model.getRonda());
        dto.setFechaHora(model.getFechaHora());
        dto.setEstado(model.getEstado());
        return dto;
    }
}