package com.microservicioevaluaciones.microservicioevaluaciones.controller;
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

import com.microservicioevaluaciones.microservicioevaluaciones.model.EvaluacionModel;
import com.microservicioevaluaciones.microservicioevaluaciones.service.EvaluacionService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/evaluacion")
public class EvaluacionController {
    @Autowired 
    private EvaluacionService evaluacionService;



    @GetMapping()
    public ResponseEntity<List<EvaluacionModel>> obtenerEvaluaciones() {
        List<EvaluacionModel> exitenciaEvaluaciones = evaluacionService.obtenerListadoEvaluaciones();
        if (exitenciaEvaluaciones.isEmpty()){
            return ResponseEntity.noContent().build();
        } 
        return ResponseEntity.ok(exitenciaEvaluaciones);
    }
    //METODO NO ENCUENTRA evaluacionES
    @GetMapping("/{id}")
    public ResponseEntity<?>obtenerEvaluacionesId(@PathVariable Long id) {
        try {EvaluacionModel evaluacion = evaluacionService.buscarEvaluacionporId(id);
            return ResponseEntity.ok(evaluacion);    
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    



    @PostMapping("crear")
    public ResponseEntity<?> guardarEvaluacion(@RequestBody EvaluacionModel evaluacionNuevo){
        try {EvaluacionModel evaluacion = evaluacionService.guardarEvaluacionConDatosAsignacion(evaluacionNuevo);
            return ResponseEntity.status(HttpStatus.CREATED).body(evaluacion);
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body("Error al crear nueva evaluacion. " + e.getMessage());          
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?>actualizarEvaluacionId(@PathVariable Long id, @RequestBody @Valid EvaluacionModel evaluacionActualizado){
        try {EvaluacionModel evaluacion = evaluacionService.actualizarEvaluacion(id, evaluacionActualizado);
            return ResponseEntity.ok(evaluacion);
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar el evaluacion"+e.getMessage());    
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEvaluacionId(@PathVariable Long id) {
        try {
            evaluacionService.borrarEvaluacion(id);
            return ResponseEntity.ok("Evaluación eliminada correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error al eliminar la evaluación: " + e.getMessage());
        }
    }       
}

