package com.project.msvc_match.Repository;

import com.project.msvc_match.Model.MatchModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<MatchModel, Long> {
    // Permite el listado dinámico requerido por especificación
    List<List<MatchModel>> findByTorneoIdAndRondaAndEstado(Long torneoId, Integer ronda, String estado);
    List<MatchModel> findByTorneoId(Long torneoId);
}