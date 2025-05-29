package com.microservicioreporte.microservicioreporte.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.microservicioreporte.microservicioreporte.model.ReporteModel;

@Repository
public interface ReporteRepository extends JpaRepository<ReporteModel, Long>{
  @Query("SELECT tr FROM ReporteModel tr WHERE tr.rutUsuario = :rutUsuario")
  List<ReporteModel> reportePorRutUsuario(Long rutUsuario);
}

 