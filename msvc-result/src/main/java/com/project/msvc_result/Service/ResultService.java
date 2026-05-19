package com.project.msvc_result.Service;

import com.project.msvc_result.DTO.ResultDTO;
import com.project.msvc_result.Exception.ResultException;
import com.project.msvc_result.Model.ResultModel;
import com.project.msvc_result.Repository.ResultRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResultService {

    private static final Logger log = LoggerFactory.getLogger(ResultService.class);

    @Autowired
    private ResultRepository repository;

    public ResultModel registrarResultado(ResultDTO dto) {
        log.info("Registrando resultado inicial para la partida ID: {}", dto.getPartidaId());

        // 1. Regla: Evitar duplicar el resultado de una misma partida
        if (repository.existsByPartidaId(dto.getPartidaId())) {
            log.error("Validación fallida: Ya existe un resultado registrado para la partida ID: {}", dto.getPartidaId());
            throw new ResultException("La partida seleccionada ya cuenta con un resultado registrado.");
        }

        // TODO: Validación Feign con match-service para verificar que la partida realmente exista
        boolean partidaExiste = true;
        if (!partidaExiste) {
            log.error("Validación fallida: La partida ID: {} no existe en el sistema", dto.getPartidaId());
            throw new ResultException("No se puede registrar resultado: Partida no encontrada.");
        }

        ResultModel nuevoResultado = new ResultModel(
                dto.getPartidaId(),
                dto.getGanadorId(),
                dto.getPuntajeA(),
                dto.getPuntajeB(),
                "PENDIENTE", // Comienza pendiente hasta la revisión del Organizador
                LocalDateTime.now()
        );

        ResultModel guardado = repository.save(nuevoResultado);
        log.info("Resultado guardado con éxito bajo el ID interno: {}", guardado.getId());
        return guardado;
    }

    public List<ResultModel> listarTodos() {
        return repository.findAll();
    }

    public ResultModel buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResultException("Resultado deportivo no encontrado con el ID: " + id));
    }

    public ResultModel actualizarResultadoAntesDeValidar(Long id, ResultDTO dto) {
        log.info("Intentando actualizar el resultado ID: {}", id);
        ResultModel resultado = buscarPorId(id);

        // Regla: No se puede modificar si ya fue VALIDADO
        if ("VALIDADO".equals(resultado.getEstadoValidacion())) {
            log.error("Error de negocio: Intento de modificar un resultado que ya fue validado formalmente.");
            throw new ResultException("Operación denegada: Un resultado validado no puede modificarse sin permisos de Organizador de alto rango.");
        }

        resultado.setGanadorId(dto.getGanadorId());
        resultado.setPuntajeA(dto.getPuntajeA());
        resultado.setPuntajeB(dto.getPuntajeB());

        return repository.save(resultado);
    }

    public ResultModel validarResultadoPorOrganizador(Long id) {
        log.info("El Organizador está aprobando y validando el resultado ID: {}", id);
        ResultModel resultado = buscarPorId(id);
        resultado.setEstadoValidacion("VALIDADO");

        ResultModel actualizado = repository.save(resultado);
        log.info("Resultado ID: {} marcado como VALIDADO. Listo para actualizar rankings.", id);
        // TODO: Aquí se dispararía un evento o llamada Feign a ranking-service y prize-service
        return actualizado;
    }

    public ResultModel anularResultado(Long id, String justificacion) {
        log.info("Anulando resultado ID: {} debido a: {}", id, justificacion);
        if (justificacion == null || justificacion.trim().isEmpty()) {
            throw new ResultException("Se requiere obligatoriamente una justificación para proceder con la anulación.");
        }

        ResultModel resultado = buscarPorId(id);
        resultado.setEstadoValidacion("ANULADO");
        resultado.setJustificacionAnulacion(justificacion);
        return repository.save(resultado);
    }
}