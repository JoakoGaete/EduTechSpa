package com.microservicioevaluaciones.microservicioevaluaciones.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.microservicioevaluaciones.microservicioevaluaciones.dto.AsignacionDTO;

@Component
public class AsignacionClient {
    private final RestTemplate restTemplate = new RestTemplate();

    public AsignacionDTO obtenerAsignacionPorId(Long idAsignacion){
        String url = "http://localhost:8083/api/asignacion/" + idAsignacion;
        return restTemplate.getForObject(url, AsignacionDTO.class);

    }
    
    public AsignacionDTO obtenerCursoPorNombre(String nombreCurso){
        String url = "http://localhost:8082/api/curso/" + nombreCurso;
        return restTemplate.getForObject(url, AsignacionDTO.class);
    }
}
