package com.microservicioevaluaciones.microservicioevaluaciones.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicioevaluaciones.microservicioevaluaciones.client.AsignacionClient;
import com.microservicioevaluaciones.microservicioevaluaciones.dto.AsignacionDTO;
import com.microservicioevaluaciones.microservicioevaluaciones.model.Contenido;
import com.microservicioevaluaciones.microservicioevaluaciones.model.EvaluacionModel;
import com.microservicioevaluaciones.microservicioevaluaciones.repository.ContenidoRepository;
import com.microservicioevaluaciones.microservicioevaluaciones.repository.EvaluacionRepository;

@Service
public class EvaluacionService {
    @Autowired 
    private AsignacionClient asignacionClient;
    @Autowired 
    private EvaluacionRepository evaluacionRepository;
    @Autowired
    private ContenidoRepository contenidoRepository;
    //GET
    public List<EvaluacionModel> obtenerListadoEvaluaciones(){
        return evaluacionRepository.findAll();
    }


    //verificar que Asignacion ID existe en la base de datos
    public EvaluacionModel buscarEvaluacionporId(Long idEvaluacion){
        return evaluacionRepository.findById(idEvaluacion).orElseThrow(() -> new RuntimeException("Evaluacion no encontrada"));
    }


    public AsignacionDTO verificarAsignacionExistentePorId(Long idAsignacion){
        AsignacionDTO datosAsignacion = asignacionClient.obtenerAsignacionPorId(idAsignacion);
        
        if (datosAsignacion == null){
            throw new RuntimeException("Asignacion no encontrada en la base de datos");
        }
        return datosAsignacion;
        
    }

    //Verifica que existe algun contenido cargado
    public Contenido buscarContenidoExistente(Long idContenido){
        return contenidoRepository.findById(idContenido).orElseThrow(()-> new RuntimeException("Contenido no encontrado"));
    }

    public EvaluacionModel guardarEvaluacionConDatosAsignacion(EvaluacionModel nuevaEvaluacion){
        AsignacionDTO verificarAsignacion = verificarAsignacionExistentePorId(nuevaEvaluacion.getIdAsignacion());
        Contenido verificarContenido = buscarContenidoExistente(nuevaEvaluacion.getIdContenido());
        nuevaEvaluacion.setNombreCurso(verificarAsignacion.getNombreCurso());
        nuevaEvaluacion.setNombreProfesor(verificarAsignacion.getNombreProfesor());
        nuevaEvaluacion.setApellidoProfesor(verificarAsignacion.getApellidoProfesor());
       //nuevaEvaluacion.setIdContenido(verificarContenido.getIdContenido());
        nuevaEvaluacion.setTipoEvaluacion(verificarContenido.getTipoEvaluacion());
        nuevaEvaluacion.setPuntajeMaximo(verificarContenido.getPuntajeMaximo());
        return evaluacionRepository.save(nuevaEvaluacion);
    }

    public EvaluacionModel actualizarEvaluacion(Long idEvaluacion, EvaluacionModel evaluacionActualizada){
        EvaluacionModel evaluacionActual = buscarEvaluacionporId(idEvaluacion);
        AsignacionDTO datoAsignacion = verificarAsignacionExistentePorId(evaluacionActualizada.getIdAsignacion());
        Contenido datoContenido = buscarContenidoExistente(evaluacionActualizada.getIdContenido());
        evaluacionActual.setIdEvaluacion(idEvaluacion);
        evaluacionActual.setIdAsignacion(datoAsignacion.getIdAsignacion());
        evaluacionActual.setNombreCurso(datoAsignacion.getNombreCurso());
        evaluacionActual.setNombreProfesor(datoAsignacion.getNombreProfesor());
        evaluacionActual.setApellidoProfesor(datoAsignacion.getApellidoProfesor());    
        evaluacionActual.setTipoEvaluacion(datoContenido.getTipoEvaluacion());
        evaluacionActual.setPuntajeMaximo(datoContenido.getPuntajeMaximo());
        return evaluacionRepository.save(evaluacionActual);
    }


    //DELETE
    public String borrarEvaluacion(Long idEvaluacion){
        EvaluacionModel evaluacion = buscarEvaluacionporId(idEvaluacion);
        evaluacionRepository.deleteById(evaluacion.getIdEvaluacion());
        return "Evaluacion borrada";
    }
}

