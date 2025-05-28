package com.microserviciocrear.microserviciocrear.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microserviciocrear.microserviciocrear.model.CrearModel;

@Repository
public interface CrearRepository extends JpaRepository<CrearModel,Long>{
    Optional<CrearModel> findByCorreo(String correo);
    Optional<CrearModel> findByPassword(String password);
}
