package com.project.msvc_prize.service;

import com.project.msvc_prize.model.Prize;
import com.project.msvc_prize.repository.PrizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class PrizeService {

    private static final Logger logger = LoggerFactory.getLogger(PrizeService.class);

    @Autowired
    private PrizeRepository prizeRepository;

    public List<Prize> listarPrizes() {

        logger.info("Listando premios");

        return prizeRepository.findAll();
    }

    public Prize guardarPrize(Prize prize) {

        logger.info("Guardando premio");

        return prizeRepository.save(prize);
    }

    public Prize buscarPorId(Long id) {

        logger.info("Buscando premio con id: " + id);

        return prizeRepository.findById(id).orElse(null);
    }

    public Prize actualizarPrize(Long id, Prize prize) {

        logger.info("Actualizando premio con id: " + id);

        Prize prizeExistente = prizeRepository.findById(id).orElse(null);

        if (prizeExistente != null) {

            prizeExistente.setTorneoId(prize.getTorneoId());
            prizeExistente.setPosicion(prize.getPosicion());
            prizeExistente.setDescripcion(prize.getDescripcion());
            prizeExistente.setValor(prize.getValor());
            prizeExistente.setEstado(prize.getEstado());

            return prizeRepository.save(prizeExistente);
        }

        return null;
    }

    public void eliminarPrize(Long id) {

        logger.info("Eliminando premio con id: " + id);

        prizeRepository.deleteById(id);
    }
}