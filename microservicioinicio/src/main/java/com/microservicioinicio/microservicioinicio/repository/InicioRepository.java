package com.microservicioinicio.microservicioinicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservicioinicio.microservicioinicio.model.InicioModel;
@Repository
public interface InicioRepository extends JpaRepository<InicioModel, Long>{

}
