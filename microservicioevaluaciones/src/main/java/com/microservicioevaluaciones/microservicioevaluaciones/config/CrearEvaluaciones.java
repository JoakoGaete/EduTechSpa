package com.microservicioevaluaciones.microservicioevaluaciones.config;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microservicioevaluaciones.microservicioevaluaciones.model.Contenido;
import com.microservicioevaluaciones.microservicioevaluaciones.model.EvaluacionModel;
import com.microservicioevaluaciones.microservicioevaluaciones.repository.ContenidoRepository;
import com.microservicioevaluaciones.microservicioevaluaciones.repository.EvaluacionRepository;
import com.microservicioevaluaciones.microservicioevaluaciones.service.EvaluacionService;



@Configuration
public class CrearEvaluaciones {

    @Bean
    CommandLineRunner initData(ContenidoRepository contenidoRepository,EvaluacionRepository evaluacionRepository,EvaluacionService evaluacionService) {
        return args -> {
            if (contenidoRepository.count() == 0) {
                contenidoRepository.save(new Contenido(null, "prueba", "50"));
                contenidoRepository.save(new Contenido(null, "tarea", "75"));
                contenidoRepository.save(new Contenido(null, "examen", "100"));
                contenidoRepository.save(new Contenido(null, "informe", "120"));
                contenidoRepository.save(new Contenido(null, "prueba", "80"));
                contenidoRepository.save(new Contenido(null, "tarea", "95"));
                contenidoRepository.save(new Contenido(null, "examen", "150"));
                contenidoRepository.save(new Contenido(null, "informe", "170"));
                contenidoRepository.save(new Contenido(null, "prueba", "200"));
                contenidoRepository.save(new Contenido(null, "tarea", "110"));
                System.out.println("10 contenidos creados correctamente.");
            }

            if (evaluacionRepository.count() == 0) {
                evaluacionService.guardarEvaluacionConDatosAsignacion(new EvaluacionModel(null, 1L, 1L, null, null, null, "prueba", "50"));
                evaluacionService.guardarEvaluacionConDatosAsignacion(new EvaluacionModel(null, 2L, 2L, null, null, null, "tarea", "75"));
                evaluacionService.guardarEvaluacionConDatosAsignacion(new EvaluacionModel(null, 3L, 3L, null, null, null, "examen", "100"));
                evaluacionService.guardarEvaluacionConDatosAsignacion(new EvaluacionModel(null, 4L, 4L, null, null, null, "informe", "120"));
                evaluacionService.guardarEvaluacionConDatosAsignacion(new EvaluacionModel(null, 5L, 4L, null, null, null, "prueba", "80"));
                evaluacionService.guardarEvaluacionConDatosAsignacion(new EvaluacionModel(null, 6L, 3L, null, null, null, "tarea", "95"));
                evaluacionService.guardarEvaluacionConDatosAsignacion(new EvaluacionModel(null, 7L, 2L, null, null, null, "examen", "150"));
                evaluacionService.guardarEvaluacionConDatosAsignacion(new EvaluacionModel(null, 8L, 1L, null, null, null, "informe", "170"));
                evaluacionService.guardarEvaluacionConDatosAsignacion(new EvaluacionModel(null, 9L, 2L, null, null, null, "prueba", "200"));
                evaluacionService.guardarEvaluacionConDatosAsignacion(new EvaluacionModel(null, 10L, 3L, null, null, null, "tarea", "110"));
                System.out.println("10 evaluaciones creadas correctamente.");
            }
        };
    }
}



        