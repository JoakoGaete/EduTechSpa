package com.microserviciousuario.microserviciousuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microserviciousuario.microserviciousuario.model.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel,Long> {
    Optional<UsuarioModel> findByRutUsuario(String rutUsuario);
}


