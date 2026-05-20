package com.project.msvc_prize_service.Repository;

import com.project.msvc_prize_service.Model.PrizeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrizeRepository extends JpaRepository<PrizeModel, Long> {
    // Filtros lógicos requeridos
    List<PrizeModel> findByTorneoId(Long torneoId);
    List<PrizeModel> findByEstado(String estado);
    List<PrizeModel> findByGanadorId(Long ganadorId);
}