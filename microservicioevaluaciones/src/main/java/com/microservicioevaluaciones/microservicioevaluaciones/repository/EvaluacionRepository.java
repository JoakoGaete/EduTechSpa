package com.microservicioevaluaciones.microservicioevaluaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservicioevaluaciones.microservicioevaluaciones.model.EvaluacionModel;


@Repository
public interface EvaluacionRepository extends JpaRepository<EvaluacionModel,Long>{

}
