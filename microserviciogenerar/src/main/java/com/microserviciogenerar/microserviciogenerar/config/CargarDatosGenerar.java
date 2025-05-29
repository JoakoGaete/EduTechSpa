package com.microserviciogenerar.microserviciogenerar.config;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microserviciogenerar.microserviciogenerar.model.GenerarModel;
import com.microserviciogenerar.microserviciogenerar.repository.GenerarRepository;
import com.microserviciogenerar.microserviciogenerar.service.GenerarService;

@Configuration
public class CargarDatosGenerar {

    @Autowired
    private GenerarService generarService;

    @Bean
    CommandLineRunner initGenerarDatos(GenerarRepository generarRepository) {
        return args -> {
            if (generarRepository.count() == 0) {
                generarService.guardarReporteConDatosUsuario(new GenerarModel(
                    null,               
                    "333",                
                    null,                      
                    null,                     
                    "El sistema no responde",   
                    null                         
                ));

                generarService.guardarReporteConDatosUsuario(new GenerarModel(
                    null,
                    "222",
                    null,
                    null,
                    "No puedo acceder al sistema",
                    null
                ));

                generarService.guardarReporteConDatosUsuario(new GenerarModel(
                    null,
                    "123",
                    null,
                    null,
                    "Error al guardar los cambios",
                    null
                ));

                System.out.println("Datos de generar cargados correctamente.");
            }
        };
    }
}
