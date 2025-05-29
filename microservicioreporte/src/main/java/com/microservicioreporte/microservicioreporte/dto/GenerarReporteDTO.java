package com.microservicioreporte.microservicioreporte.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerarReporteDTO {

    private Long idReporte;
    private String rutUsuario;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String problemaReportado;
    private LocalDateTime fechaReporte;
}