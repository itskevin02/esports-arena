package com.project.msvc_match.service;

import com.project.msvc_match.model.Match;
import com.project.msvc_match.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class MatchService {

    private static final Logger logger = LoggerFactory.getLogger(MatchService.class);

    @Autowired
    private MatchRepository matchRepository;

    public List<Match> listarMatches() {

        logger.info("Listando partidas");

        return matchRepository.findAll();
    }

    public Match guardarMatch(Match match) {

        logger.info("Guardando partida");

        return matchRepository.save(match);
    }

    public Match buscarPorId(Long id) {

        logger.info("Buscando partida con id: " + id);

        return matchRepository.findById(id).orElse(null);
    }

    public Match actualizarMatch(Long id, Match match) {

        logger.info("Actualizando partida con id: " + id);

        Match matchExistente = matchRepository.findById(id).orElse(null);

        if (matchExistente != null) {

            matchExistente.setTorneoId(match.getTorneoId());
            matchExistente.setParticipanteAId(match.getParticipanteAId());
            matchExistente.setParticipanteBId(match.getParticipanteBId());
            matchExistente.setRonda(match.getRonda());
            matchExistente.setEstado(match.getEstado());

            return matchRepository.save(matchExistente);
        }

        return null;
    }

    public void eliminarMatch(Long id) {

        logger.info("Eliminando partida con id: " + id);

        matchRepository.deleteById(id);
    }
}