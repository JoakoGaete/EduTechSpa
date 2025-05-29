package com.microserviciocurso.microserviciocurso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microserviciocurso.microserviciocurso.model.CursoModel;
import com.microserviciocurso.microserviciocurso.service.CursoService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("api/curso")
public class CursoController {

    @Autowired 
    private CursoService cursoService;

    // GET - Listar todos los cursos
    @GetMapping
    public ResponseEntity<List<CursoModel>> obtenerCursos() {
        List<CursoModel> cursos = cursoService.obtenerListadoCursos();
        if (cursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cursos);
    }

    // GET - Obtener curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCursoPorId(@PathVariable Long id) {
        try {
            CursoModel curso = cursoService.buscarCursoPorId(id);
            return ResponseEntity.ok(curso);
        } catch (RuntimeException e) {
            // Aquí idealmente lanzar una excepción personalizada y manejar con ControllerAdvice
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    

    // POST - Crear nuevo curso
    @PostMapping
    public ResponseEntity<?> guardarCurso(@RequestBody @Valid CursoModel cursoNuevo) {
        try {
            CursoModel curso = cursoService.guardarCurso(cursoNuevo);
            return ResponseEntity.status(HttpStatus.CREATED).body(curso);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // PUT - Actualizar curso existente
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCurso(@PathVariable Long id, @RequestBody @Valid CursoModel cursoActualizado) {
        try {
            CursoModel curso = cursoService.actualizarCurso(id, cursoActualizado);
            return ResponseEntity.ok(curso);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body("Error al actualizar el curso: " + e.getMessage());
        }
    }

    // DELETE - Eliminar curso por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCurso(@PathVariable Long id) {
        try {
            cursoService.borrarCurso(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body("Error al eliminar el curso: " + e.getMessage());
        }
    }
}
