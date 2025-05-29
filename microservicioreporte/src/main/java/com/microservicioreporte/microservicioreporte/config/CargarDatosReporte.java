package com.microservicioreporte.microservicioreporte.config;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microservicioreporte.microservicioreporte.model.ReporteModel;
import com.microservicioreporte.microservicioreporte.repository.ReporteRepository;
import com.microservicioreporte.microservicioreporte.service.ReporteService;

@Configuration
public class CargarDatosReporte {


    @Autowired
    private ReporteService reporteService;

    @Bean
    CommandLineRunner initReportes(ReporteRepository reporteRepository) {
        return args -> {
            if (reporteRepository.count() == 0) {
                reporteService.guardarReporte(new ReporteModel(
                    null,                       // id (autogenerado)
                    null,                       // rutSoporte
                    null,                       // solucionReporte
                    null,                       // estadoSolucion
                    null,                       // fechaCerradoReporte
                    1L,                      // idReporte (viene de microservicio "generar")
                    "12345678-9",               // rutUsuario
                    "Juan Pérez",               // nombreUsuario
                    "No enciende el equipo",    // problemaReporte
                    LocalDateTime.now()         // fechaReporte
                ));

                reporteService.guardarReporte(new ReporteModel(
                    null,
                    null,
                    null,
                    null,
                    null,
                    2L,
                    "98765432-1",
                    "Ana Gómez",
                    "Pantalla azul al iniciar",
                    LocalDateTime.now()
                ));

                System.out.println("Datos de reportes cargados correctamente.");
            }
        };
    }
}
