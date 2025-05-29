package com.microserviciousuario.microserviciousuario.controller;

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

import com.microserviciousuario.microserviciousuario.model.UsuarioModel;
import com.microserviciousuario.microserviciousuario.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    // Obtener lista de usuarios
    @GetMapping()
    public ResponseEntity<List<UsuarioModel>> obtenerUsuarios() {
        List<UsuarioModel> existenciaUsuarios = usuarioService.obtenerListadoUsuarios();
        if (existenciaUsuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(existenciaUsuarios);
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Long id) {
        try {
            UsuarioModel usuario = usuarioService.buscarUsuarioPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Obtener usuario por RUT
    @GetMapping("/rut/{rutUsuario}")
    public ResponseEntity<?> obtenerUsuarioPorRut(@PathVariable String rutUsuario) {
        try {
            UsuarioModel usuario = usuarioService.buscarUsuarioPorRut(rutUsuario);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Crear nuevo usuario
    @PostMapping()
    public ResponseEntity<?> guardarUsuario(@RequestBody @Valid UsuarioModel usuarioNuevo) {
        try {
            UsuarioModel usuario = usuarioService.guardarUsuario(usuarioNuevo);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Actualizar usuario por ID
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody @Valid UsuarioModel usuarioActualizado) {
        try {
            UsuarioModel usuario = usuarioService.actualizarUsuario(id, usuarioActualizado);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar el usuario: " + e.getMessage());
        }
    }

    // Eliminar usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        try {
            usuarioService.borrarUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al eliminar el usuario: " + e.getMessage());
        }
    }
}
