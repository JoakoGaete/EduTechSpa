package com.microserviciogenerar.microserviciogenerar.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microserviciogenerar.microserviciogenerar.model.GenerarModel;
import java.util.Optional;
import java.util.Date;


@Repository
public interface GenerarRepository extends JpaRepository<GenerarModel, Long> {
    Optional<GenerarModel> findByNombreUsuario(String nombreUsuario);
}

