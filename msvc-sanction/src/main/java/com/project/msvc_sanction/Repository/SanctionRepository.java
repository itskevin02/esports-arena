package com.project.msvc_sanction_service.Repository;

import com.project.msvc_sanction_service.Model.SanctionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanctionRepository extends JpaRepository<SanctionModel, Long> {
    // Filtros solicitados: por usuario, equipo o estado
    List<SanctionModel> findByUsuarioId(Long usuarioId);
    List<SanctionModel> findByEquipoId(Long equipoId);
    List<SanctionModel> findByEstado(String estado);

    // Filtro combinado para verificar si hay bloqueos vigentes en cascada
    List<SanctionModel> findByUsuarioIdAndEstado(Long usuarioId, String estado);
    List<SanctionModel> findByEquipoIdAndEstado(Long equipoId, String estado);
}