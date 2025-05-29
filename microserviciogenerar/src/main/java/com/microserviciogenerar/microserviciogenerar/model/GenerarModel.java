package com.microserviciogenerar.microserviciogenerar.model;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tabla_reportes_usuario")
public class GenerarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReporte;

    private String rutUsuario;
    private String nombreUsuario;
    private String apellidoUsuario;

    private String problemaReportado;
    
    @Column(updatable = false)
    private LocalDateTime fechaReporte;
    
    @PrePersist
    protected void inicializacionFecha() {
        this.fechaReporte = LocalDateTime.now();
    }

}
