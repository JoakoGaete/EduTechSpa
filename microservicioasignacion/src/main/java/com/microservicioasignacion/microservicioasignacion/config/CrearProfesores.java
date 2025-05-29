// com.microservicioasignacion.microservicioasignacion.config.CrearProfesores

package com.microservicioasignacion.microservicioasignacion.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microservicioasignacion.microservicioasignacion.model.AsignacionModel;
import com.microservicioasignacion.microservicioasignacion.model.Profesor;
import com.microservicioasignacion.microservicioasignacion.repository.AsignacionRepository;
import com.microservicioasignacion.microservicioasignacion.repository.Profe;
import com.microservicioasignacion.microservicioasignacion.service.AsignacionService;

@Configuration
public class CrearProfesores {

    @Bean
    CommandLineRunner initData(Profe profesorRepository, AsignacionRepository asignacionRepository, AsignacionService asignacionService) {
        return args -> {
            if (profesorRepository.count() == 0) {
                profesorRepository.save(new Profesor(null, "12345678-9", "Laura", "Pérez"));
                profesorRepository.save(new Profesor(null, "23456789-0", "Miguel", "Soto"));
                profesorRepository.save(new Profesor(null, "34567890-1", "Natalia", "Herrera"));
                profesorRepository.save(new Profesor(null, "45678901-2", "Rodrigo", "Muñoz"));
                profesorRepository.save(new Profesor(null, "56789012-3", "Valentina", "Morales"));

                System.out.println("Profesores creados correctamente.");
            }

            if (asignacionRepository.count() == 0) {
                asignacionService.guardarAsignacionConDatosCurso(new AsignacionModel(null, 1L, null, "12345678-9", null, null));
                asignacionService.guardarAsignacionConDatosCurso(new AsignacionModel(null, 2L, null, "23456789-0", null, null));
                asignacionService.guardarAsignacionConDatosCurso(new AsignacionModel(null, 3L, null, "34567890-1", null, null));
                asignacionService.guardarAsignacionConDatosCurso(new AsignacionModel(null, 4L, null, "45678901-2", null, null));
                asignacionService.guardarAsignacionConDatosCurso(new AsignacionModel(null, 1L, null, "56789012-3", null, null));

                System.out.println("Asignaciones creadas correctamente.");
            }
        };
    }
}    