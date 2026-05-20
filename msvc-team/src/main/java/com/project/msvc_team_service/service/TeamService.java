package com.project.msvc_team_service.Service;

import com.project.msvc_team_service.DTO.TeamDTO;
import com.project.msvc_team_service.DTO.TeamMemberDTO;
import com.project.msvc_team_service.Exception.TeamException;
import com.project.msvc_team_service.Model.TeamMemberModel;
import com.project.msvc_team_service.Model.TeamModel;
import com.project.msvc_team_service.Repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private static final Logger log = LoggerFactory.getLogger(TeamService.class);

    @Autowired
    private TeamRepository teamRepository;

    @Transactional
    public TeamModel crearEquipo(TeamDTO dto) {
        log.info("Iniciando creación del equipo: {}", dto.getNombre());

        // Regla de negocio: Validar que el capitán esté definido
        if (dto.getCapitanId() == null) {
            log.error("Error al crear equipo: No se especificó un capitán.");
            throw new TeamException("Un equipo debe tener un capitán de forma obligatoria.");
        }

        TeamModel equipo = new TeamModel();
        equipo.setNombre(dto.getNombre());
        equipo.setCapitanId(dto.getCapitanId());
        equipo.setJuegoPrincipalId(dto.getJuegoPrincipalId());
        equipo.setEstado("ACTIVO");

        // Regla de negocio: Validar que no haya jugadores duplicados
        if (dto.getIntegrantes() != null) {
            long countUnicos = dto.getIntegrantes().stream()
                    .map(TeamMemberDTO::getUsuarioId)
                    .distinct()
                    .count();
            if (countUnicos < dto.getIntegrantes().size()) {
                log.error("Error al crear equipo: Hay jugadores duplicados en la lista.");
                throw new TeamException("No se puede duplicar un jugador dentro del mismo equipo.");
            }

            List<TeamMemberModel> miembros = dto.getIntegrantes().stream()
                    .map(mDto -> new TeamMemberModel(equipo, mDto.getUsuarioId(), mDto.getRolDentroEquipo()))
                    .collect(Collectors.toList());
            equipo.setIntegrantes(miembros);
        }

        TeamModel guardado = teamRepository.save(equipo);
        log.info("Equipo creado exitosamente con ID: {}", guardado.getId());
        return guardado;
    }

    @Transactional(readOnly = true)
    public List<TeamModel> listarEquipos(Long juegoId, Long capitanId, String estado) {
        log.info("Listando equipos con filtros - Juego: {}, Capitán: {}, Estado: {}", juegoId, capitanId, estado);
        if (juegoId != null) return teamRepository.findByJuegoPrincipalId(juegoId);
        if (capitanId != null) return teamRepository.findByCapitanId(capitanId);
        if (estado != null) return teamRepository.findByEstado(estado);
        return teamRepository.findAll();
    }

    @Transactional(readOnly = true)
    public TeamModel buscarPorId(Long id) {
        log.info("Buscando equipo con ID: {}", id);
        return teamRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Equipo con ID {} no fue encontrado.", id);
                    return new TeamException("Equipo no encontrado.");
                });
    }

    @Transactional
    public TeamModel actualizarEquipo(Long id, TeamDTO dto) {
        log.info("Actualizando equipo con ID: {}", id);
        TeamModel equipo = buscarPorId(id);

        equipo.setNombre(dto.getNombre());
        equipo.setCapitanId(dto.getCapitanId());
        equipo.setJuegoPrincipalId(dto.getJuegoPrincipalId());
        equipo.setEstado(dto.getEstado());

        if (dto.getIntegrantes() != null) {
            equipo.getIntegrantes().clear();
            List<TeamMemberModel> nuevosMiembros = dto.getIntegrantes().stream()
                    .map(mDto -> new TeamMemberModel(equipo, mDto.getUsuarioId(), mDto.getRolDentroEquipo()))
                    .collect(Collectors.toList());
            equipo.getIntegrantes().addAll(nuevosMiembros);
        }

        return teamRepository.save(equipo);
    }

    @Transactional
    public void desactivarEquipo(Long id) {
        log.info("Desactivando (eliminación lógica) equipo con ID: {}", id);
        TeamModel equipo = buscarPorId(id);
        equipo.setEstado("INACTIVO");
        teamRepository.save(equipo);
        log.info("Equipo con ID {} cambiado a INACTIVO.", id);
    }
}