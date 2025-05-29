package com.microservicioprogreso.microservicioprogreso.controller;

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

import com.microservicioprogreso.microservicioprogreso.model.ProgresoModel;
import com.microservicioprogreso.microservicioprogreso.service.ProgresoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/progreso")
public class ProgresoController {
    @Autowired
    private ProgresoService progresoService;

    @GetMapping()
    public ResponseEntity<List<ProgresoModel>> obtenerListadoProgreso() {
        List<ProgresoModel> exitenciaProgreso = progresoService.obtenerListadoProgreso();
        if (exitenciaProgreso.isEmpty()){
            return ResponseEntity.noContent().build();
        } 
        return ResponseEntity.ok(exitenciaProgreso);
    }
    //METODO NO ENCUENTRA ROLES
    @GetMapping("/{id}")
    public ResponseEntity<?>obtenerProgresoPorId(@PathVariable Long id) {
        try {ProgresoModel rol = progresoService.buscarProgresoporId(id);
            return ResponseEntity.ok(rol);    
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<?> guardarProgreso(@RequestBody @Valid ProgresoModel progresoNuevo){
        try {ProgresoModel rol = progresoService.guardarProgreso(progresoNuevo);
            return ResponseEntity.status(HttpStatus.CREATED).body(rol);
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());          
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?>actualizarProgresoId(@PathVariable Long id, @RequestBody @Valid ProgresoModel progresoActualizado){
        try {ProgresoModel progreso = progresoService.actualizarProgreso(id, progresoActualizado);
            return ResponseEntity.ok(progreso);
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar el progreso"+e.getMessage());    
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProgresoId(@PathVariable Long id) {
        try {
            progresoService.borrarProgreso(id);
            return ResponseEntity.ok("Progreso eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error al eliminar el progreso: " + e.getMessage());
        }
    }        
}
