package com.microservicioevaluaciones.microservicioevaluaciones.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tabla_evaluacion")
public class EvaluacionModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvaluacion;

    private Long idContenido;
    private Long idAsignacion;
    private String nombreCurso;          // Identificador único de la evaluación
    private String nombreProfesor;              // Nombre del profe
    private String apellidoProfesor;            // Apellido del profe
    private String tipoEvaluacion;                // Tipo de evaluación (por ejemplo: "prueba", "examen", "tarea")            // ID del curso asociad             // Descripción breve de la evaluacion
    private String puntajeMaximo;       // Puntaje máximo posible       // ID del estudiante evaluado

}


