package com.microservicioasignacion.microservicioasignacion.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.microservicioasignacion.microservicioasignacion.client.CursoClient;
import com.microservicioasignacion.microservicioasignacion.dto.CursoDTO;
import com.microservicioasignacion.microservicioasignacion.model.AsignacionModel;
import com.microservicioasignacion.microservicioasignacion.model.Profesor;
import com.microservicioasignacion.microservicioasignacion.repository.AsignacionRepository;
import com.microservicioasignacion.microservicioasignacion.repository.Profe;

@Service
public class AsignacionService {

    @Autowired
    private CursoClient cursoClient;
    @Autowired 
    private AsignacionRepository asignacionRepository;
    @Autowired
    private Profe profesorRepository;

    //GET PUT POST DELETE
    public List<AsignacionModel> listadoAsignaciones() {
        return asignacionRepository.findAll();
    }

    //GET
    public AsignacionModel buscarAsignacionporId(Long idAsignacion){
        return asignacionRepository.findById(idAsignacion).orElseThrow(() -> new RuntimeException("Asignacion no encontrado"));
    }

    //Verificar Curso ID si existe en la base de datos
    public CursoDTO verificarCursoExistentePorId(Long idCurso) {
        CursoDTO datosCurso = cursoClient.obtenerCursoPorId(idCurso);

        if (datosCurso == null) {
            throw new RuntimeException("Curso no encontrado en la base de datos");
        }
        return datosCurso;
    }

    //Verificar que exista el profesor
    public Profesor buscarProfesorExistente(String rutProfesor) {
        return profesorRepository.findByRutProfesor(rutProfesor).orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
    }

    //Guardar Asignacion con validacion de Cursos Existentes
    public AsignacionModel guardarAsignacionConDatosCurso(AsignacionModel nuevaAsignacion) {
        CursoDTO vertificarCurso = verificarCursoExistentePorId(nuevaAsignacion.getIdCurso());
        Profesor verificarProfesor = buscarProfesorExistente(nuevaAsignacion.getRutProfesor());
        nuevaAsignacion.setNombreCurso(vertificarCurso.getNombreCurso());
        nuevaAsignacion.setNombreProfesor(verificarProfesor.getNombreProfesor());
        nuevaAsignacion.setApellidoProfesor(verificarProfesor.getApellidoProfesor());
        return asignacionRepository.save(nuevaAsignacion);
    }

    //PUT
    public AsignacionModel actualizarAsignacion(Long idAsignacion, AsignacionModel asignacionActualizada){
        AsignacionModel asignacionActual = buscarAsignacionporId(idAsignacion);
        CursoDTO datosCurso = verificarCursoExistentePorId(asignacionActualizada.getIdCurso());
        Profesor datosProfesor = buscarProfesorExistente(asignacionActualizada.getRutProfesor());
        asignacionActual.setIdAsignacion(idAsignacion);
        asignacionActual.setIdCurso(datosCurso.getIdCurso());
        asignacionActual.setNombreCurso(datosCurso.getNombreCurso());
        asignacionActual.setRutProfesor(datosProfesor.getRutProfesor());
        asignacionActual.setNombreProfesor(datosProfesor.getNombreProfesor());
        asignacionActual.setApellidoProfesor(datosProfesor.getApellidoProfesor());
        return asignacionRepository.save(asignacionActual);
    }
    //DELETE
    public String borrarasignaciones(Long idAsignacion){
        AsignacionModel asignacion = buscarAsignacionporId(idAsignacion);
        asignacionRepository.deleteById(asignacion.getIdAsignacion());
        return "asignacion borrada";
    }
}

