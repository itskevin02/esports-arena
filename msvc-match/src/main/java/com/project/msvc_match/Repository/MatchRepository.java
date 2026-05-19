package com.project.msvc_match.Repository;

import com.project.msvc_match.Model.MatchModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<MatchModel, Long> {
    List<MatchModel> findByTorneoId(Long torneoId);
    List<MatchModel> findByTorneoIdAndRonda(Long torneoId, Integer ronda);
    List<MatchModel> findByEstado(String estado);

    // Validador para evitar duplicar el mismo enfrentamiento exacto en la misma ronda
    boolean existsByTorneoIdAndRondaAndParticipanteAIdAndParticipanteBId(Long torneoId, Integer ronda, Long participanteAId, Long participanteBId);
}