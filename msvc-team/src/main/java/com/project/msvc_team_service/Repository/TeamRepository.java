package com.project.msvc_team_service.Repository;

import com.project.msvc_team_service.Model.TeamModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<TeamModel, Long> {
    // Filtros solicitados: listar por juego, capitan o estado
    List<TeamModel> findByJuegoPrincipalId(Long juegoPrincipalId);
    List<TeamModel> findByCapitanId(Long capitanId);
    List<TeamModel> findByEstado(String estado);
}
asd