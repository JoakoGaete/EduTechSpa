package com.microservicioprogreso.microservicioprogreso.model;

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
@Table(name = "tabla_progreso")
public class ProgresoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProgreso;
    private String rutUsuario;
    private String nombreCurso;
    private int clasesCompletadas;
    private int clasesTotales;
}
