package com.project.msvc_tournament.service;

import com.project.msvc_tournament.model.Tournament;
import com.project.msvc_tournament.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class TournamentService {

    private static final Logger logger = LoggerFactory.getLogger(TournamentService.class);

    @Autowired
    private TournamentRepository tournamentRepository;

    public List<Tournament> listarTournaments() {

        logger.info("Listando torneos");

        return tournamentRepository.findAll();
    }

    public Tournament guardarTournament(Tournament tournament) {

        logger.info("Guardando torneo: " + tournament.getNombre());

        return tournamentRepository.save(tournament);
    }

    public Tournament buscarPorId(Long id) {

        logger.info("Buscando torneo con id: " + id);

        return tournamentRepository.findById(id).orElse(null);
    }

    public Tournament actualizarTournament(Long id, Tournament tournament) {

        logger.info("Actualizando torneo con id: " + id);

        Tournament tournamentExistente = tournamentRepository.findById(id).orElse(null);

        if (tournamentExistente != null) {

            tournamentExistente.setNombre(tournament.getNombre());
            tournamentExistente.setUbicacion(tournament.getUbicacion());
            tournamentExistente.setCantidadEquipos(tournament.getCantidadEquipos());
            tournamentExistente.setEstado(tournament.getEstado());

            return tournamentRepository.save(tournamentExistente);
        }

        return null;
    }

    public void eliminarTournament(Long id) {

        logger.info("Eliminando torneo con id: " + id);

        tournamentRepository.deleteById(id);
    }
}