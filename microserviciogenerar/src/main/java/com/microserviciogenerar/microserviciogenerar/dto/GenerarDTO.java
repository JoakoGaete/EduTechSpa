package com.microserviciogenerar.microserviciogenerar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerarDTO {
    private Long idReporte;

    private String rutUsuario;
    private String nombreUsuario;

    private String problemaReportado;
    
    private LocalDateTime fechaReporte;
    
}


