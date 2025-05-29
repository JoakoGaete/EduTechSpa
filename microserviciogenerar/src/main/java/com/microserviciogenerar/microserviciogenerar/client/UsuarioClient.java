package com.microserviciogenerar.microserviciogenerar.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.microserviciogenerar.microserviciogenerar.dto.UsuarioDTO;

@Component
public class UsuarioClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public UsuarioDTO buscarUsuarioPorRut(String usuarioRut) {
        // Asegúrate de que esta URL coincida con la que expone tu microservicio de UsuarioDTO
        String url = "http://localhost:8085/api/usuarios/rut/" + usuarioRut;
        return restTemplate.getForObject(url, UsuarioDTO.class);
    }

}