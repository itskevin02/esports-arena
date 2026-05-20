package com.project.msvc_result.service;

import com.project.msvc_result.model.Result;
import com.project.msvc_result.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class ResultService {

    private static final Logger logger = LoggerFactory.getLogger(ResultService.class);

    @Autowired
    private ResultRepository resultRepository;

    public List<Result> listarResults() {

        logger.info("Listando resultados");

        return resultRepository.findAll();
    }

    public Result guardarResult(Result result) {

        logger.info("Guardando resultado");

        return resultRepository.save(result);
    }

    public Result buscarPorId(Long id) {

        logger.info("Buscando resultado con id: " + id);

        return resultRepository.findById(id).orElse(null);
    }

    public Result actualizarResult(Long id, Result result) {

        logger.info("Actualizando resultado con id: " + id);

        Result resultExistente = resultRepository.findById(id).orElse(null);

        if (resultExistente != null) {

            resultExistente.setPartidaId(result.getPartidaId());
            resultExistente.setGanadorId(result.getGanadorId());
            resultExistente.setPuntajeA(result.getPuntajeA());
            resultExistente.setPuntajeB(result.getPuntajeB());
            resultExistente.setEstadoValidacion(result.getEstadoValidacion());

            return resultRepository.save(resultExistente);
        }

        return null;
    }

    public void eliminarResult(Long id) {

        logger.info("Eliminando resultado con id: " + id);

        resultRepository.deleteById(id);
    }
}