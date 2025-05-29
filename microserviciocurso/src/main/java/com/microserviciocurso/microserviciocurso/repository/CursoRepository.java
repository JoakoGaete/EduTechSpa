package com.microserviciocurso.microserviciocurso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microserviciocurso.microserviciocurso.model.CursoModel;

import java.util.Optional;



@Repository
public interface CursoRepository extends JpaRepository<CursoModel, Long> {
    Optional<CursoModel> findByNombreCurso(String nombreCurso);
}
