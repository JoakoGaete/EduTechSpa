package com.microservicioasignacion.microservicioasignacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservicioasignacion.microservicioasignacion.model.AsignacionModel;

@Repository
public interface AsignacionRepository extends JpaRepository<AsignacionModel, Long> {

}
