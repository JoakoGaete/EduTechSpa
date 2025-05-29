package com.microserviciocompra.microserviciocompra.controller;

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

import com.microserviciocompra.microserviciocompra.model.CompraModel;
import com.microserviciocompra.microserviciocompra.service.CompraService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/compra")
public class CompraController {
    @Autowired 
    private CompraService compraService;

    @GetMapping()
    public ResponseEntity<List<CompraModel>> obtenerCompra() {
        List<CompraModel> exitenciaCompra = compraService.obtenerListadoCompra();
        if (exitenciaCompra.isEmpty()){
            return ResponseEntity.noContent().build();
        } 
        return ResponseEntity.ok(exitenciaCompra);
    }
    //METODO NO ENCUENTRA ROLES
    @GetMapping("/{id}")
    public ResponseEntity<?>obtenerCompraId(@PathVariable Long id) {
        try {CompraModel rol = compraService.buscarCompraporId(id);
            return ResponseEntity.ok(rol);    
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<?> guardarCompra(@RequestBody @Valid CompraModel compraNuevo){
        try {CompraModel rol = compraService.guardarCompra(compraNuevo);
            return ResponseEntity.status(HttpStatus.CREATED).body(rol);
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());          
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?>actualizarCompraId(@PathVariable Long id, @RequestBody @Valid CompraModel compraActualizado){
        try {CompraModel compra = compraService.actualizarCompra(id, compraActualizado);
            return ResponseEntity.ok(compra);
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar la compra"+e.getMessage());    
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCompraId(@PathVariable Long id) {
        try {
            compraService.borrarCompra(id);
            return ResponseEntity.ok("Compra eliminada correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error al eliminar la compra: " + e.getMessage());
        }
    }        
}
