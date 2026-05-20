package com.project.msvc_sanction.service;

import com.project.msvc_sanction.model.Sanction;
import com.project.msvc_sanction.repository.SanctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class SanctionService {

    private static final Logger logger = LoggerFactory.getLogger(SanctionService.class);

    @Autowired
    private SanctionRepository sanctionRepository;

    public List<Sanction> listarSanctions() {

        logger.info("Listando sanciones");

        return sanctionRepository.findAll();
    }

    public Sanction guardarSanction(Sanction sanction) {

        logger.info("Guardando sancion");

        return sanctionRepository.save(sanction);
    }

    public Sanction buscarPorId(Long id) {

        logger.info("Buscando sancion con id: " + id);

        return sanctionRepository.findById(id).orElse(null);
    }

    public Sanction actualizarSanction(Long id, Sanction sanction) {

        logger.info("Actualizando sancion con id: " + id);

        Sanction sanctionExistente = sanctionRepository.findById(id).orElse(null);

        if (sanctionExistente != null) {

            sanctionExistente.setUsuarioId(sanction.getUsuarioId());
            sanctionExistente.setMotivo(sanction.getMotivo());
            sanctionExistente.setSeveridad(sanction.getSeveridad());
            sanctionExistente.setEstado(sanction.getEstado());

            return sanctionRepository.save(sanctionExistente);
        }

        return null;
    }

    public void eliminarSanction(Long id) {

        logger.info("Eliminando sancion con id: " + id);

        sanctionRepository.deleteById(id);
    }
}