package com.microservicioprogreso.microservicioprogreso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservicioprogreso.microservicioprogreso.model.ProgresoModel;

@Repository
public interface ProgresoRepository extends JpaRepository<ProgresoModel, Long>{

}
