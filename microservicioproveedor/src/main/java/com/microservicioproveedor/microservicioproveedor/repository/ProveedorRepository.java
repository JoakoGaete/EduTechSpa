package com.microservicioproveedor.microservicioproveedor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservicioproveedor.microservicioproveedor.model.ProveedorModel;
@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorModel,Long>{
}
