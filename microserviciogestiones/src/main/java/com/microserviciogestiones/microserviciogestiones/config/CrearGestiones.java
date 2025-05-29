package com.microserviciogestiones.microserviciogestiones.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microserviciogestiones.microserviciogestiones.model.GestionesModel;
import com.microserviciogestiones.microserviciogestiones.repository.GestionesRepository;
import com.microserviciogestiones.microserviciogestiones.service.GestionesService;

@Configuration
public class CrearGestiones {

    @Autowired
    private GestionesService gestionesService;

    @Bean
    CommandLineRunner initDataBase(GestionesRepository gestionesRepository) {

        return args -> {
            if (gestionesRepository.count() == 0) {
                gestionesService.guardarGestion(new GestionesModel(
                    null,
                    "12345678-9",
                    "Gabriel",
                    "Vidal",
                    "Muñoz"
                ));

                gestionesService.guardarGestion(new GestionesModel(
                    null,
                    "22222222-2",
                    "Raul",
                    "Fernandez",
                    "Salinas"
                ));

                gestionesService.guardarGestion(new GestionesModel(
                    null,
                    "33333333-3",
                    "Joaquin",
                    "Gaete",
                    "López"
                ));

                gestionesService.guardarGestion(new GestionesModel(
                    null,
                    "44444444-4",
                    "Simon",
                    "Muñoz",
                    "Reyes"
                ));

                System.out.println("Datos de usuarios cargados correctamente.");
            }
        };
    }
}
