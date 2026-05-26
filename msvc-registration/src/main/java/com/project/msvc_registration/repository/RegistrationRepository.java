package com.project.msvc_registration.repository;

import com.project.msvc_registration.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    Registration findByEquipoIdAndTorneoId(Long equipoId, Long torneoId);

    long countByTorneoId(Long torneoId);
}