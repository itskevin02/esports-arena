package com.project.msvc_prize_service.Service;

import com.project.msvc_prize_service.DTO.PrizeDTO;
import com.project.msvc_prize_service.Exception.PrizeException;
import com.project.msvc_prize_service.Model.PrizeModel;
import com.project.msvc_prize_service.Repository.PrizeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PrizeService {

    private static final Logger log = LoggerFactory.getLogger(PrizeService.class);

    @Autowired
    private PrizeRepository prizeRepository;

    @Transactional
    public PrizeModel crearPremio(PrizeDTO dto) {
        log.info("Iniciando creación del premio '{}' para el torneo ID: {}", dto.getNombre(), dto.getTorneoId());

        // Regla de negocio: Si el premio es monetario, debe tener un fondo real asignado
        if ("MONETARIO".equalsIgnoreCase(dto.getTipo()) && dto.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            log.error("Fallo de negocio: Premio monetario sin valor financiero válido.");
            throw new PrizeException("Un premio de tipo MONETARIO debe tener un valor mayor a cero.");
        }

        PrizeModel premio = new PrizeModel();
        premio.setNombre(dto.getNombre());
        premio.setTipo(dto.getTipo().toUpperCase());
        premio.setValor(dto.getValor());
        premio.setTorneoId(dto.getTorneoId());
        premio.setEstado("DISPONIBLE"); // Estado inicial por defecto

        PrizeModel guardado = prizeRepository.save(premio);
        log.info("Premio guardado con éxito. ID asignado: {}", guardado.getId());
        return guardado;
    }

    @Transactional(readOnly = true)
    public List<PrizeModel> listarPremios(Long torneoId, String estado, Long ganadorId) {
        log.info("Buscando lista de premios bajo los criterios especificados.");
        if (torneoId != null) return prizeRepository.findByTorneoId(torneoId);
        if (estado != null) return prizeRepository.findByEstado(estado.toUpperCase());
        if (ganadorId != null) return prizeRepository.findByGanadorId(ganadorId);
        return prizeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public PrizeModel buscarPorId(Long id) {
        log.info("Buscando premio con ID: {}", id);
        return prizeRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("No se encontró el premio con ID: {}", id);
                    return new PrizeException("Premio no encontrado.");
                });
    }

    // Flujo crucial indicado en el core de la arquitectura del PDF: Asignar premios al finalizar el torneo
    @Transactional
    public PrizeModel asignarGanador(Long premioId, Long ganadorId) {
        log.info("Intentando asignar el ganador ID: {} al premio ID: {}", ganadorId, premioId);

        PrizeModel premio = buscarPorId(premioId);

        if (ganadorId == null) {
            log.error("Asignación inválida: El ID del ganador es nulo.");
            throw new PrizeException("Debe proveer un ID de ganador válido para la adjudicación.");
        }

        premio.setGanadorId(ganadorId);
        premio.setEstado("ASIGNADO");

        log.info("Premio ID: {} asignado con éxito al participante/equipo ID: {}", premioId, ganadorId);
        return prizeRepository.save(premio);
    }

    @Transactional
    public void eliminarPremio(Long id) {
        log.info("Procesando eliminación del premio ID: {}", id);
        PrizeModel premio = buscarPorId(id);

        if ("ASIGNADO".equals(premio.getEstado()) || "ENTREGADO".equals(premio.getEstado())) {
            log.error("No se puede eliminar el premio ID: {} porque ya se encuentra en estado: {}", id, premio.getEstado());
            throw new PrizeException("No se pueden eliminar premios que ya han sido asignados o entregados.");
        }

        prizeRepository.delete(premio);
        log.info("Premio ID: {} eliminado correctamente del inventario.", id);
    }
}