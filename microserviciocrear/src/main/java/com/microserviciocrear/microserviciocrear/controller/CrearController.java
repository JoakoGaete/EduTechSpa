package com.microserviciocrear.microserviciocrear.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microserviciocrear.microserviciocrear.config.AppConfig;
import com.microserviciocrear.microserviciocrear.model.CrearModel;
import com.microserviciocrear.microserviciocrear.service.CrearService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/registro")
public class CrearController {
    @Autowired 
    private CrearService crearService;

    @Autowired 
    private PasswordEncoder passwordEncoder;

    @GetMapping()
    public ResponseEntity<List<CrearModel>> obtenerCrear() {
        List<CrearModel> exitenciaCrear = crearService.obtenerListadoCrear();
        if (exitenciaCrear.isEmpty()){
            return ResponseEntity.noContent().build();
        } 
        return ResponseEntity.ok(exitenciaCrear);
    }
    //METODO NO ENCUENTRA Crear
    @GetMapping("/{id}")
    public ResponseEntity<?>obtenerCrearId(@PathVariable Long id) {
        try {CrearModel rol = crearService.buscarCrearporId(id);
            return ResponseEntity.ok(rol);    
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //POST SOLO PARA COMPARAR Y VERIFICAR LA INFORMACION DE LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> obtenerDatosUsuario(@RequestBody CrearModel login) {
        try {
            CrearModel usuario = crearService.buscarUsuarioPorCorreo(login);
            if (!passwordEncoder.matches(login.getPassword(), usuario.getPassword())) {
                throw new RuntimeException("Contraseña incorrecta");
            }
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @PostMapping
    public ResponseEntity<?> registrarUsuario(@RequestBody CrearModel request) {
        try {
            CrearModel nuevoUsuario = crearService.registrarUsuario(request);
            return ResponseEntity.ok("Usuario registrado con éxito: " + nuevoUsuario.getCorreo());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?>actualizarCrearId(@PathVariable Long id, @RequestBody @Valid CrearModel crearActualizado){
        try {CrearModel crear = crearService.actualizarCrear(id, crearActualizado);
            return ResponseEntity.ok(crear);
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar la cuenta"+e.getMessage());    
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCrearId(@PathVariable Long id) {
        try {
            crearService.borrarCrear(id);
            return ResponseEntity.ok("Cuenta eliminada correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error al eliminar la cuenta: " + e.getMessage());
        }
    }        
}