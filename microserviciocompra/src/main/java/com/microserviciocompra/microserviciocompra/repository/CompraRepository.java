package com.microserviciocompra.microserviciocompra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microserviciocompra.microserviciocompra.model.CompraModel;

@Repository
public interface CompraRepository extends JpaRepository<CompraModel, Long>{

}
