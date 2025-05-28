package com.microserviciocrear.microserviciocrear.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microserviciocrear.microserviciocrear.model.CrearModel;
import com.microserviciocrear.microserviciocrear.repository.CrearRepository;
import com.microserviciocrear.microserviciocrear.service.CrearService;

@Configuration
public class CrearDatos {
    @Autowired
    private CrearService crearService;

    @Bean
    CommandLineRunner initDataBase(CrearRepository crearRepository) {

        return args -> {
            if (crearRepository.count() == 0) {
                crearService.registrarUsuario(new CrearModel(
                    null,
                    "Gabriel",
                    "Vidal",
                    "ga.vidal@duocuc.cl",
                    "password123",
                    "ADMIN"
                ));

                crearService.registrarUsuario(new CrearModel(
                    null,
                    "Raul",
                    "Fernandez",
                    "ra.fernandez@duocuc.cl",
                    "password456",
                    "SOPORTE"
                ));

                crearService.registrarUsuario(new CrearModel(
                    null,
                    "Joaquin",
                    "Gaete",
                    "jo.gaete@duocuc.cl",
                    "password456",
                    "USUARIO"
                ));
                
                crearService.registrarUsuario(new CrearModel(
                    null,
                    "Simon",
                    "Muñoz",
                    "si.munoz@duocuc.cl",
                    "password456",
                    "PROFESOR"
                ));                

                System.out.println("Datos de usuarios cargados correctamente.");
            }
        };
    }
}
