package com.microservicioasignacion.microservicioasignacion.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservicioasignacion.microservicioasignacion.model.AsignacionModel;
import com.microservicioasignacion.microservicioasignacion.service.AsignacionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/asignacion")
public class AsignacionController {
    @Autowired 
    private AsignacionService asignacionService;

    @GetMapping
    public ResponseEntity<List<AsignacionModel>> obtenerAsignacion() {
        List<AsignacionModel> asignaciones = asignacionService.listadoAsignaciones();
        if (asignaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(asignaciones);
    }

    //METODO NO ENCUENTRA ROLES
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerAsignacionId(@PathVariable Long id) {
        try {AsignacionModel asignacion = asignacionService.buscarAsignacionporId(id);
            return ResponseEntity.ok(asignacion);    
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("crear")
    public ResponseEntity<?> crearNuevaAsignacion(@RequestBody AsignacionModel nuevaAsignacion) {
        try {
            AsignacionModel asignacion = asignacionService.guardarAsignacionConDatosCurso(nuevaAsignacion);
            return ResponseEntity.status(HttpStatus.CREATED).body(asignacion);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body("Error al crear nueva Asignación. " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>actualizarAsignacionId(@PathVariable Long id, @RequestBody @Valid AsignacionModel asignacionActualizado){
        try {AsignacionModel rol = asignacionService.actualizarAsignacion(id, asignacionActualizado);
            return ResponseEntity.ok(rol);
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar la asignacion"+e.getMessage());    
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>elminarAsignacionId(@PathVariable Long id){
        try {asignacionService.borrarasignaciones(id);
            return ResponseEntity.noContent().build();
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al eliminar la asignacion"+e.getMessage());    
        }
    }
        
}
