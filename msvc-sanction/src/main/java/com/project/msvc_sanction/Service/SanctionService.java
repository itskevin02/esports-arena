package com.project.msvc_sanction_service.Service;

import com.project.msvc_sanction_service.DTO.SanctionDTO;
import com.project.msvc_sanction_service.Exception.SanctionException;
import com.project.msvc_sanction_service.Model.SanctionModel;
import com.project.msvc_sanction_service.Repository.SanctionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SanctionService {

    private static final Logger log = LoggerFactory.getLogger(SanctionService.class);

    @Autowired
    private SanctionRepository sanctionRepository;

    @Transactional
    public SanctionModel crearSancion(SanctionDTO dto) {
        log.info("Procesando creación de una nueva sanción.");

        // Regla de negocio: Validar que se asigne al menos a un usuario o equipo
        if (dto.getUsuarioId() == null && dto.getEquipoId() == null) {
            log.error("Error al registrar sanción: Destinatario ausente.");
            throw new SanctionException("La sanción debe estar asociada a un usuario o a un equipo.");
        }

        // Regla de negocio: Fecha fin posterior a fecha inicio
        if (dto.getFechaFin().isBefore(dto.getFechaInicio())) {
            log.error("Error de coherencia temporal: Fecha fin ({}) previa a fecha inicio ({}).", dto.getFechaFin(), dto.getFechaInicio());
            throw new SanctionException("La fecha de finalización debe ser posterior a la fecha de inicio.");
        }

        SanctionModel sancion = new SanctionModel();
        sancion.setUsuarioId(dto.getUsuarioId());
        sancion.setEquipoId(dto.getEquipoId());
        sancion.setMotivo(dto.getMotivo());
        sancion.setFechaInicio(dto.getFechaInicio());
        sancion.setFechaFin(dto.getFechaFin());
        sancion.setEstado(dto.getEstado().toUpperCase());
        sancion.setSeveridad(dto.getSeveridad().toUpperCase());

        SanctionModel guardada = sanctionRepository.save(sancion);
        log.info("Sanción almacenada exitosamente con ID: {}", guardada.getId());
        return guardada;
    }

    @Transactional(readOnly = true)
    public List<SanctionModel> listarSanciones(Long usuarioId, Long equipoId, String estado) {
        log.info("Consultando listado de sanciones con filtros activos.");
        if (usuarioId != null) return sanctionRepository.findByUsuarioId(usuarioId);
        if (equipoId != null) return sanctionRepository.findByEquipoId(equipoId);
        if (estado != null) return sanctionRepository.findByEstado(estado.toUpperCase());
        return sanctionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public SanctionModel buscarPorId(Long id) {
        log.info("Buscando sanción mediante ID: {}", id);
        return sanctionRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Sanción con ID {} inexistente en la base de datos.", id);
                    return new SanctionException("Sanción no encontrada.");
                });
    }

    @Transactional
    public SanctionModel actualizarSancion(Long id, SanctionDTO dto) {
        log.info("Ejecutando actualización de la sanción ID: {}", id);
        SanctionModel sancion = buscarPorId(id);

        if (dto.getFechaFin().isBefore(dto.getFechaInicio())) {
            log.error("Error de validación en fechas al actualizar.");
            throw new SanctionException("La fecha de finalización debe ser posterior a la fecha de inicio.");
        }

        sancion.setMotivo(dto.getMotivo());
        sancion.setFechaInicio(dto.getFechaInicio());
        sancion.setFechaFin(dto.getFechaFin());
        sancion.setEstado(dto.getEstado().toUpperCase());
        sancion.setSeveridad(dto.getSeveridad().toUpperCase());

        return sanctionRepository.save(sancion);
    }

    @Transactional
    public void cerrarSancionCumplida(Long id) {
        log.info("Procediendo al cierre definitivo de la sanción ID: {}", id);
        SanctionModel sancion = buscarPorId(id);
        sancion.setEstado("CERRADA");
        sanctionRepository.save(sancion);
        log.info("La sanción ID {} ha cambiado su estado a CERRADA con éxito. Ya no bloqueará inscripciones.", id);
    }

    // Método de soporte clave para el flujo integrador de registration-service
    @Transactional(readOnly = true)
    public boolean verificarBloqueoActivo(Long usuarioId, Long equipoId) {
        log.info("Validando vigencia de restricciones para Usuario: {} o Equipo: {}", usuarioId, equipoId);

        if (usuarioId != null) {
            List<SanctionModel> activasUser = sanctionRepository.findByUsuarioIdAndEstado(usuarioId, "ACTIVA");
            boolean bloqueado = activasUser.stream().anyMatch(s -> s.getSeveridad().equals("BLOQUEANTE"));
            if (bloqueado) return true;
        }

        if (equipoId != null) {
            List<SanctionModel> activasTeam = sanctionRepository.findByEquipoIdAndEstado(equipoId, "ACTIVA");
            return activasTeam.stream().anyMatch(s -> s.getSeveridad().equals("BLOQUEANTE"));
        }

        return false;
    }
}