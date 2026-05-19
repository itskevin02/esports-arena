package com.project.msvc_registration.Repository;

import com.project.msvc_registration.Model.RegistrationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationModel, Long> {
    List<RegistrationModel> findByTorneoId(Long torneoId);
    List<RegistrationModel> findByEquipoId(Long equipoId);
    List<RegistrationModel> findByJugadorId(Long jugadorId);

    // Para validar que no se dupliquen inscripciones en el mismo torneo
    boolean existsByTorneoIdAndJugadorId(Long torneoId, Long jugadorId);
    boolean existsByTorneoIdAndEquipoId(Long torneoId, Long equipoId);
}
asd