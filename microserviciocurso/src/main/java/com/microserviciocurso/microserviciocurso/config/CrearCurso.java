package com.microserviciocurso.microserviciocurso.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microserviciocurso.microserviciocurso.model.CursoModel;
import com.microserviciocurso.microserviciocurso.repository.CursoRepository;
import com.microserviciocurso.microserviciocurso.service.CursoService;

import org.springframework.boot.CommandLineRunner;

@Configuration
public class CrearCurso {

    @Autowired
    private CursoService cursoService;

    @Bean
    CommandLineRunner initDataBase(CursoRepository cursoRepository) {
        return args -> {
            if (cursoRepository.count() == 0) {

                cursoService.guardarCurso(new CursoModel(
                    null,
                    "Matemáticas Aplicadas",
                    "Curso de fundamentos matemáticos para ingeniería",
                    "40",
                    "ACTIVO"
                ));

                cursoService.guardarCurso(new CursoModel(
                    null,
                    "Programación en Java",
                    "Introducción al desarrollo backend con Spring Boot",
                    "35",
                    "ACTIVO"
                ));

                cursoService.guardarCurso(new CursoModel(
                    null,
                    "Diseño UX/UI",
                    "Principios y herramientas para diseño de interfaces de usuario",
                    "30",
                    "INACTIVO"
                ));

                cursoService.guardarCurso(new CursoModel(
                    null,
                    "Redes de Computadores",
                    "Configuración de redes y protocolos de comunicación",
                    "25",
                    "ACTIVO"
                ));

                System.out.println("Datos de cursos cargados correctamente.");
            }
        };
    }
}
