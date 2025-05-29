package com.microservicioreporte.microservicioreporte.model;
import java.sql.Date;
import java.time.LocalDateTime;

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
@Table(name = "tabla_estado_reportes")
public class ReporteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSolucion; //Id de la solucion del reporte creado
    private String rutSoporte; 
    private String solucionReporte; 
    private String estadoSolucion;
    private Date fechaCerradoReporte;
    //Estos datos vienen del microservicio de GENERAR
    private Long idReporte;
    private String rutUsuario;
    private String nombreUsuario;
    private String problemaReportado; 
    private LocalDateTime fechaReporte;
}

