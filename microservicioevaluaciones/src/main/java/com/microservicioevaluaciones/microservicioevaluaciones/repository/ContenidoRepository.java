package com.microservicioevaluaciones.microservicioevaluaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservicioevaluaciones.microservicioevaluaciones.model.Contenido;

@Repository
public interface ContenidoRepository extends JpaRepository<Contenido, Long> {

}
