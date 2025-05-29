package com.microserviciogenerar.microserviciogenerar.controller;

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

import com.microserviciogenerar.microserviciogenerar.model.GenerarModel;
import com.microserviciogenerar.microserviciogenerar.service.GenerarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/generarReportes")
public class GenerarController {

    @Autowired
    private GenerarService generarService;

    // Obtener todos los reportes
    @GetMapping()
    public ResponseEntity<List<GenerarModel>> obtenerReportes() {
        List<GenerarModel> listaReportes = generarService.obtenerListadoReportes();
        if (listaReportes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaReportes);
    }

    // Obtener reporte por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerReportePorId(@PathVariable Long id) {
        try {
            GenerarModel reporte = generarService.buscarReportePorId(id);
            return ResponseEntity.ok(reporte);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Reporte no encontrado: " + e.getMessage());
        }
    }

    // Crear nuevo reporte
    @PostMapping()
    public ResponseEntity<?> guardarReporte(@RequestBody @Valid GenerarModel reporteNuevo) {
        try {
            GenerarModel reporte = generarService.guardarReporteConDatosUsuario(reporteNuevo);
            return ResponseEntity.status(HttpStatus.CREATED).body(reporte);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al guardar reporte: " + e.getMessage());
        }
    }

    // Actualizar reporte existente
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarReporte(@PathVariable Long id, @RequestBody @Valid GenerarModel reporteActualizado) {
        try {
            GenerarModel reporte = generarService.actualizarReporte(id, reporteActualizado);
            return ResponseEntity.ok(reporte);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar el reporte: " + e.getMessage());
        }
    }

    // Eliminar reporte por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarReporte(@PathVariable Long id) {
        try {
            generarService.borrarReporte(id);
            return ResponseEntity.ok("Reporte eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error al eliminar el reporte: " + e.getMessage());
        }
    }
}

