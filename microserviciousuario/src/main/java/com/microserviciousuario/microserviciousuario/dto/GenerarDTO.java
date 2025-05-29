package com.microserviciousuario.microserviciousuario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor 
public class GenerarDTO {
    private Long id;
    private String rutUsuario;
    private String apellidoPaterno;
    private String apellidoMaterno;

}
