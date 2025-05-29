package com.microserviciogenerar.microserviciogenerar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microserviciogenerar.microserviciogenerar.client.UsuarioClient;
import com.microserviciogenerar.microserviciogenerar.dto.GenerarDTO;
import com.microserviciogenerar.microserviciogenerar.dto.UsuarioDTO;
import com.microserviciogenerar.microserviciogenerar.model.GenerarModel;
import com.microserviciogenerar.microserviciogenerar.repository.GenerarRepository;

import org.springframework.web.client.RestTemplate;

@Service
public class GenerarService {

    @Autowired
    private GenerarRepository generarRepository;

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private RestTemplate restTemplate;

    public List<GenerarModel> obtenerListadoReportes(){
        return generarRepository.findAll();
    }

    public GenerarModel buscarReportePorId(Long id){
        return generarRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reporte no encontrado con id: " + id));
    }

    public GenerarModel guardarReporte(GenerarModel reporteNuevo){
        return generarRepository.save(reporteNuevo);
    }

    public UsuarioDTO verificarUsuarioPorRutExistente(String rutUsuario) {
        UsuarioDTO datosUsuario = usuarioClient.buscarUsuarioPorRut(rutUsuario);

        if (datosUsuario == null) {
            throw new RuntimeException("Usuario no encontrado en la base de datos");
        }
        return datosUsuario;

    }

    public GenerarModel guardarReporteConDatosUsuario(GenerarModel nuevoReporte) {
        UsuarioDTO verificarUsuario = verificarUsuarioPorRutExistente(nuevoReporte.getRutUsuario());
        nuevoReporte.setNombreUsuario(verificarUsuario.getNombres());
        nuevoReporte.setApellidoUsuario(verificarUsuario.getApellidoPaterno());
        GenerarModel guardado = generarRepository.save(nuevoReporte);

        GenerarDTO dtoEnvioDatosAReporte = new GenerarDTO(
            guardado.getIdReporte(),
            guardado.getNombreUsuario(),
            guardado.getRutUsuario(),
            guardado.getProblemaReportado(),
            guardado.getFechaReporte()
        );

        try {
            restTemplate.postForObject("http://localhost:8087/api/reportes/sin-solucion", dtoEnvioDatosAReporte, Void.class);
        } catch (RuntimeException e) {
            System.err.println("Error al enviar el reporte hacia el microservicio de Reportes. " + e.getMessage());
        }


        return guardado;
    }

    public GenerarModel actualizarReporte(Long id, GenerarModel reporteActualizado) {
        GenerarModel reporteActual = buscarReportePorId(id);

        // Actualizar solo campos no nulos para evitar sobrescrituras con null
        if (reporteActualizado.getNombreUsuario() != null) {
            reporteActual.setNombreUsuario(reporteActualizado.getNombreUsuario());
        }
        if (reporteActualizado.getApellidoUsuario() != null) {
            reporteActual.setApellidoUsuario(reporteActualizado.getApellidoUsuario());
        }
        if (reporteActualizado.getFechaReporte() != null) {
            reporteActual.setFechaReporte(reporteActualizado.getFechaReporte());
        }
        if (reporteActualizado.getProblemaReportado() != null) {
            reporteActual.setProblemaReportado(reporteActualizado.getProblemaReportado());
        }
        return generarRepository.save(reporteActual);
    }

    public String borrarReporte(Long id){
        GenerarModel reporte = buscarReportePorId(id);
        generarRepository.deleteById(reporte.getIdReporte());
        return "Reporte borrado correctamente";
    }
}
