package com.microservicioproveedor.microservicioproveedor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicioproveedor.microservicioproveedor.model.ProveedorModel;
import com.microservicioproveedor.microservicioproveedor.service.ProveedorService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/proveedor")
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;
    //METODOS HTTP
    @GetMapping()
    public ResponseEntity<List<ProveedorModel>> obtenerProveedores() {
        List<ProveedorModel> exitenciaProveedor = proveedorService.obtenerListadoProveedores();
        if (exitenciaProveedor.isEmpty()){
            return ResponseEntity.noContent().build();
        } 
        return ResponseEntity.ok(exitenciaProveedor);
    }
    //METODO NO ENCUENTRA ProveedorMo
    @GetMapping("/{id}") //acá los {} dicen que la id puede tomar cualquier numero
    public ResponseEntity<?> obtenerProveedorModel (@PathVariable Long id) {  //El path variable le dice toma una variable identificada en el atributo Long id y se lo comunica al get mapping
        try {ProveedorModel proveedor = proveedorService.buscarProveedorporId(id);
            return ResponseEntity.ok(proveedor);    
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<?> guardarRol(@RequestBody @Valid ProveedorModel proveedorNuevo){
        try {
            ProveedorModel proveedor = proveedorService.guardarProveedor(proveedorNuevo);
            return ResponseEntity.status(HttpStatus.CREATED).body(proveedor);
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());          
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?>actualizarProveedorModel(@PathVariable Long id, @RequestBody @Valid ProveedorModel proveedorActulizado){
        try {
            ProveedorModel proveedor = proveedorService.actualizarProveedor(id, proveedorActulizado);
            return ResponseEntity.ok(proveedor);
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar el proveedor"+e.getMessage());    
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProveedorModel(@PathVariable Long id) {
        try {
            proveedorService.borrarProveedor(id);
            return ResponseEntity.ok("Proveedor eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error al eliminar el proveedor: " + e.getMessage());
        }
    }
        
}    
