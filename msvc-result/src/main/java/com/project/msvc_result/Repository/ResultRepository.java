package com.project.msvc_result.Repository;

import com.project.msvc_result.Model.ResultModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<ResultModel, Long> {
    Optional<ResultModel> findByPartidaId(Long partidaId);
    boolean existsByPartidaId(Long partidaId);
}