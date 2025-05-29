package com.microserviciogestiones.microserviciogestiones.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microserviciogestiones.microserviciogestiones.model.GestionesModel;

public interface GestionesRepository extends JpaRepository<GestionesModel,Long>{

}
