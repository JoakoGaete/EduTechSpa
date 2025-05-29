package com.microserviciousuario.microserviciousuario.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microserviciousuario.microserviciousuario.model.UsuarioModel;
import com.microserviciousuario.microserviciousuario.repository.UsuarioRepository;
import com.microserviciousuario.microserviciousuario.service.UsuarioService;

@Configuration
public class CrearUsuario {
    @Autowired
    private UsuarioService usuarioService;

    @Bean
    CommandLineRunner initDataBase(UsuarioRepository usuarioRepository) {

        return args -> {
            if (usuarioRepository.count() == 0) {
                usuarioService.guardarUsuario(new UsuarioModel(
                    null,
                    "123",
                    "Gabriel",
                    "Vidal",
                    "Muñoz",
                    "Calle Falsa 123",
                    "DEBITO"
                ));

                usuarioService.guardarUsuario(new UsuarioModel(
                    null,
                    "222",
                    "Raul",
                    "Fernandez",
                    "Salinas",
                    "Av. Siempre Viva 742",
                    "CREDITO"
                ));

                usuarioService.guardarUsuario(new UsuarioModel(
                    null,
                    "333",
                    "Joaquin",
                    "Gaete",
                    "López",
                    "Pasaje Los Andes 145",
                    "DEBITO"
                ));

                usuarioService.guardarUsuario(new UsuarioModel(
                    null,
                    "444",
                    "Simon",
                    "Muñoz",
                    "Reyes",
                    "Calle Sur 789",
                    "CREDITO"
                ));

                System.out.println("Datos de usuarios cargados correctamente.");
            }
        };
    }
}

