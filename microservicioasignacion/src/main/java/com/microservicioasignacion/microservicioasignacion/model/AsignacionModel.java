package com.microservicioasignacion.microservicioasignacion.model;

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
@Table(name = "asignacion_curso")
public class AsignacionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAsignacion;

    private Long idCurso;
    
    private String nombreCurso;
    private String rutProfesor;
    private String nombreProfesor;
    private String apellidoProfesor;

}
