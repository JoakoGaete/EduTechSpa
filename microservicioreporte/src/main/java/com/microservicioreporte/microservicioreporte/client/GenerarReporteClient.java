package com.microservicioreporte.microservicioreporte.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.microservicioreporte.microservicioreporte.dto.GenerarReporteDTO;

@Component
public class GenerarReporteClient {
    private final RestTemplate restTemplate = new RestTemplate();

    public GenerarReporteDTO obtenerReportePorId(Long idReporte) {
        // Asegúrate de que esta URL coincida con la que expone tu microservicio de UsuarioDTO
        String url = "http://localhost:8086/api/generarReportes/" + idReporte;
        return restTemplate.getForObject(url, GenerarReporteDTO.class);
    }
}