package com.microservicioasignacion.microservicioasignacion.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.microservicioasignacion.microservicioasignacion.dto.CursoDTO;

@Component
public class CursoClient {
    private final RestTemplate restTemplate = new RestTemplate();

    public CursoDTO obtenerCursoPorId(Long idCurso) {
        // Asegúrate de que esta URL coincida con la que expone tu microservicio de Curso
        String url = "http://localhost:8082/api/curso/" + idCurso;
        return restTemplate.getForObject(url, CursoDTO.class);
    }

    public CursoDTO obtenerCursoPorNombre(String nombreCurso){
        String url = "http://localhost:8082/api/curso/" + nombreCurso;
        return restTemplate.getForObject(url, CursoDTO.class);
    }
    
}
