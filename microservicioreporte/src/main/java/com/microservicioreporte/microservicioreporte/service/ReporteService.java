package com.microservicioreporte.microservicioreporte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicioreporte.microservicioreporte.client.GenerarReporteClient;
import com.microservicioreporte.microservicioreporte.dto.GenerarReporteDTO;
import com.microservicioreporte.microservicioreporte.model.ReporteModel;
import com.microservicioreporte.microservicioreporte.repository.ReporteRepository;

@Service
public class ReporteService {
  @Autowired
  private GenerarReporteClient generarReporteClient;

  @Autowired
  private ReporteRepository reporteRepository;

  public List<ReporteModel> listarReportes() {
    return reporteRepository.findAll();
  }

  public ReporteModel buscarReportePorId(Long id) {
    return reporteRepository.findById(id).orElseThrow(() -> new RuntimeException("No se ha encontrado el id de reporte en la base de datos."));
  }


  
  public GenerarReporteDTO verificarReportePorIdExistente(Long idReporte){
      GenerarReporteDTO datosReporte = generarReporteClient.obtenerReportePorId(idReporte);
      if (datosReporte == null){
        throw new RuntimeException("Reporte no encontrado en la base de datos");
      }

    return datosReporte;
  }



public ReporteModel guardarReporteConDatosGenerados(Long id, ReporteModel solucionReporte){
    ReporteModel reporteActual = buscarReportePorId(id);
    GenerarReporteDTO verificarReporte = verificarReportePorIdExistente(id);

    reporteActual.setIdReporte(verificarReporte.getIdReporte());
    reporteActual.setRutUsuario(verificarReporte.getRutUsuario());
    reporteActual.setNombreUsuario(verificarReporte.getNombreUsuario());
    reporteActual.setProblemaReportado(verificarReporte.getProblemaReportado());
    reporteActual.setFechaReporte(verificarReporte.getFechaReporte());

    // Solo se actualizan campos del soporte si vienen en el body
    if (solucionReporte.getRutSoporte() != null) {
        reporteActual.setRutSoporte(solucionReporte.getRutSoporte());
    }
    if (solucionReporte.getSolucionReporte() != null) {
        reporteActual.setSolucionReporte(solucionReporte.getSolucionReporte());
    }
    if (solucionReporte.getEstadoSolucion() != null) {
        reporteActual.setEstadoSolucion(solucionReporte.getEstadoSolucion());
    }
    if (solucionReporte.getFechaCerradoReporte() != null) {
        reporteActual.setFechaCerradoReporte(solucionReporte.getFechaCerradoReporte());
    }

    return reporteRepository.save(reporteActual);
}


  public String borrarReportePorId(Long id) {
    reporteRepository.deleteById(id);
    return "Reporte Eliminado por existo";
  }

  public ReporteModel guardarReporte(ReporteModel nuevoReporte) {
    nuevoReporte.setRutSoporte(null);
    nuevoReporte.setSolucionReporte(null);
    nuevoReporte.setEstadoSolucion(null);
    nuevoReporte.setFechaCerradoReporte(null);

    return reporteRepository.save(nuevoReporte);
  }

  //LO que hace este metodo es tomar el reporte creado por el usuario y solo poder modificar los campos
  //requeridos por el del soporte,  no puede tocar la info como rut del usuario etc.
  public ReporteModel actualizarReportePorId(Long id, ReporteModel reporteActualizado) {
    ReporteModel reporteActual = buscarReportePorId(id);

    if (reporteActualizado.getRutSoporte() != null) {
        reporteActual.setRutSoporte(reporteActualizado.getRutSoporte());
    }

    if (reporteActualizado.getSolucionReporte() != null) {
        reporteActual.setSolucionReporte(reporteActualizado.getSolucionReporte());
    }

    if (reporteActualizado.getEstadoSolucion() != null) {
        reporteActual.setEstadoSolucion(reporteActualizado.getEstadoSolucion());
    }

    if (reporteActualizado.getFechaCerradoReporte() != null) {
        reporteActual.setFechaCerradoReporte(reporteActualizado.getFechaCerradoReporte());
    }


    return reporteRepository.save(reporteActual);
  }

  public List<ReporteModel> buscarReportePorRutUsuario(Long rutUsuario) {
    return reporteRepository.reportePorRutUsuario(rutUsuario);
  }
  
}