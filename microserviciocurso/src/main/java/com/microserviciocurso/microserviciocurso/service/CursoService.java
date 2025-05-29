package com.microserviciocurso.microserviciocurso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microserviciocurso.microserviciocurso.model.CursoModel;
import com.microserviciocurso.microserviciocurso.repository.CursoRepository;


@Service
public class CursoService {

@Autowired
private CursoRepository cursoRepository;

    // GET - Listar todos los cursos
    public List<CursoModel> obtenerListadoCursos() {
        return cursoRepository.findAll();
    }

    // GET - Buscar curso por ID
    public CursoModel buscarCursoPorId(Long id) {
        return cursoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }

    // POST - Guardar nuevo curso
    public CursoModel guardarCurso(CursoModel cursoNuevo) {
        return cursoRepository.save(cursoNuevo);
    }

    public CursoModel buscarCursoPorNombre(String nombreCurso) {
        return cursoRepository.findByNombreCurso(nombreCurso).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }

    // PUT - Actualizar curso
    public CursoModel actualizarCurso(Long id, CursoModel cursoActualizado) {
        CursoModel cursoActual = buscarCursoPorId(id);

        if (cursoActualizado.getNombreCurso() != null) {
            cursoActual.setNombreCurso(cursoActualizado.getNombreCurso());
        }
        if (cursoActualizado.getDescripcion() != null) {
            cursoActual.setDescripcion(cursoActualizado.getDescripcion());
        }
        if (cursoActualizado.getCantidad() != null) {
            cursoActual.setCantidad(cursoActualizado.getCantidad());
        }
        if (cursoActualizado.getEstado() != null) {
            cursoActual.setEstado(cursoActualizado.getEstado());
        }

        return cursoRepository.save(cursoActual);
    }

    // DELETE - Eliminar curso por ID
    public String borrarCurso(Long id) {
        CursoModel curso = buscarCursoPorId(id);
        cursoRepository.deleteById(curso.getIdCurso());
        return "Curso borrado";
    }
}

