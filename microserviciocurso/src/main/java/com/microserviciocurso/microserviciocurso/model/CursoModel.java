package com.microserviciocurso.microserviciocurso.model;

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
@Table(name = "tabla_cursos")
public class CursoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCurso;
    private String nombreCurso;
    private String descripcion;
    private String cantidad;
    private String estado;
    
    public CursoModel(Long idCurso, String nombreCurso){
        this.idCurso = idCurso;
        this.nombreCurso = nombreCurso;
    }

}
