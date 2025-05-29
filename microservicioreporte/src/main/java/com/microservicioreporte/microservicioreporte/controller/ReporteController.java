package com.microservicioreporte.microservicioreporte.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicioreporte.microservicioreporte.dto.GenerarReporteDTO;
import com.microservicioreporte.microservicioreporte.model.ReporteModel;
import com.microservicioreporte.microservicioreporte.service.ReporteService;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {
  @Autowired
  private ReporteService reporteService;
  @GetMapping()
  public ResponseEntity<List<ReporteModel>> listaDeReportes() {
    List<ReporteModel> reportesExistentes = reporteService.listarReportes();

    if (reportesExistentes.isEmpty()) {
      return ResponseEntity.noContent().build();
    } 
    return ResponseEntity.ok(reportesExistentes);
  }
  @GetMapping("/{id}")
  public ResponseEntity<?> buscarReporteId(@PathVariable Long id) {
    try {
      ReporteModel reporte = reporteService.buscarReportePorId(id);
      return ResponseEntity.ok(reporte);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }
  @GetMapping("/{rutUsuario}")
  public ResponseEntity<List<ReporteModel>> reportePortRutUsuario(@PathVariable Long rutUsuario) {
    List<ReporteModel> listaReportesPorRutUsuario = reporteService.buscarReportePorRutUsuario(rutUsuario);
    if(listaReportesPorRutUsuario.isEmpty()) {
      return ResponseEntity.noContent().build();
    } 

    return ResponseEntity.ok(listaReportesPorRutUsuario);
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<String> eliminarReporte(@PathVariable Long id) {
      try {
          String resultado = reporteService.borrarReportePorId(id);
          return ResponseEntity.ok(resultado); // Devuelve el mensaje de éxito
      } catch (RuntimeException e) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // Mensaje de error
      }
  }

  @PostMapping("/sin-solucion")
  public ResponseEntity<?> crearDesdeGenerar(@RequestBody GenerarReporteDTO dto) {
    ReporteModel reporte = new ReporteModel();

    reporte.setIdReporte(dto.getIdReporte()); 
    reporte.setRutUsuario(dto.getRutUsuario());
    reporte.setNombreUsuario(dto.getNombreUsuario());
    reporte.setProblemaReportado(dto.getProblemaReportado());
    reporte.setFechaReporte(dto.getFechaReporte());

    return ResponseEntity.status(HttpStatus.CREATED).body(reporteService.guardarReporte(reporte));
}

  //acaaaaaaaaaaaaaaaaa
  @PostMapping()
  public ResponseEntity<?> crearNuevoReporte(@RequestBody ReporteModel reporteNuevo) {
    try {
      ReporteModel reporteGuardado = reporteService.guardarReporte(reporteNuevo);
      return ResponseEntity.status(HttpStatus.CREATED).body(reporteGuardado);
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body("Error al crear el reporte: " + e.getMessage());
    }
  }

  
//Acá es el metodo que le suministro los datos del microservicio y le doy una solucion al reporte
  @PutMapping("/{id}")
  public ResponseEntity<?> actualizarReporte(
    @PathVariable Long id, 
    @RequestBody ReporteModel reporteActualizado) {
      try {
        ReporteModel reporteActual = reporteService.guardarReporteConDatosGenerados(id, reporteActualizado);
        return ResponseEntity.ok(reporteActual);
      } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body("Error al actualizar el reporte: " + e.getMessage());
      }
  }
}
