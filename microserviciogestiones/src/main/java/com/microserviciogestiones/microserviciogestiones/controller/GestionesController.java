package com.microserviciogestiones.microserviciogestiones.controller;

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

import com.microserviciogestiones.microserviciogestiones.model.GestionesModel;
import com.microserviciogestiones.microserviciogestiones.service.GestionesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/gestion")
public class GestionesController {
    @Autowired 
    private GestionesService gestionesService;

    @GetMapping()
    public ResponseEntity<List<GestionesModel>> obtenerGestion() {
        List<GestionesModel> exitenciaCompra = gestionesService.obtenerListadoGestiones();
        if (exitenciaCompra.isEmpty()){
            return ResponseEntity.noContent().build();
        } 
        return ResponseEntity.ok(exitenciaCompra);
    }
    //METODO NO ENCUENTRA gestion
    @GetMapping("/{id}")
    public ResponseEntity<?>obtenerGestionId(@PathVariable Long id) {
        try {GestionesModel gestion = gestionesService.buscarGestionporId(id);
            return ResponseEntity.ok(gestion);    
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<?> guardarGestion(@RequestBody @Valid GestionesModel compraNuevo){
        try {GestionesModel rol = gestionesService.guardarGestion(compraNuevo);
            return ResponseEntity.status(HttpStatus.CREATED).body(rol);
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());          
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?>actualizarGestionid(@PathVariable Long id, @RequestBody @Valid GestionesModel gestionActualizado){
        try {GestionesModel compra = gestionesService.actualizarGestion(id, gestionActualizado);
            return ResponseEntity.ok(compra);
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar la gesiton"+e.getMessage());    
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarGestionId(@PathVariable Long id) {
        try {
            gestionesService.borrarGestion(id);
            return ResponseEntity.ok("Gestion eliminada correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error al eliminar la gestion: " + e.getMessage());
        }
    }        
}