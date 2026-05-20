package com.project.msvc_registration.service;

import com.project.msvc_registration.model.Registration;
import com.project.msvc_registration.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class RegistrationService {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationService.class);

    @Autowired
    private RegistrationRepository registrationRepository;

    public List<Registration> listarRegistrations() {

        logger.info("Listando inscripciones");

        return registrationRepository.findAll();
    }

    public Registration guardarRegistration(Registration registration) {

        logger.info("Guardando inscripcion");

        return registrationRepository.save(registration);
    }

    public Registration buscarPorId(Long id) {

        logger.info("Buscando inscripcion con id: " + id);

        return registrationRepository.findById(id).orElse(null);
    }

    public Registration actualizarRegistration(Long id, Registration registration) {

        logger.info("Actualizando inscripcion con id: " + id);

        Registration registrationExistente = registrationRepository.findById(id).orElse(null);

        if (registrationExistente != null) {

            registrationExistente.setTorneoId(registration.getTorneoId());
            registrationExistente.setEquipoId(registration.getEquipoId());
            registrationExistente.setEstado(registration.getEstado());

            return registrationRepository.save(registrationExistente);
        }

        return null;
    }

    public void eliminarRegistration(Long id) {

        logger.info("Eliminando inscripcion con id: " + id);

        registrationRepository.deleteById(id);
    }
}