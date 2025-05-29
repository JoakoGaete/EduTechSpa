package com.microservicioasignacion.microservicioasignacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.microservicioasignacion.microservicioasignacion.model.Profesor;

@Repository
public interface Profe extends JpaRepository<Profesor, Long> {
  Optional<Profesor> findByRutProfesor(String rutProfesor);
}
