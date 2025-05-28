package com.microserviciocrear.microserviciocrear.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microserviciocrear.microserviciocrear.model.CrearModel;
import com.microserviciocrear.microserviciocrear.repository.CrearRepository;


@Service
public class CrearService {
//GET PUT POST DELETE
@Autowired 
private CrearRepository crearRepository;

@Autowired 
private PasswordEncoder passwordEncoder;
    //GET
    public List<CrearModel> obtenerListadoCrear(){
        return crearRepository.findAll();
    }
    //GET
    public CrearModel buscarCrearporId(Long id){
        return crearRepository.findById(id).orElseThrow(() -> new RuntimeException("Cuenta creada no encontrada"));
    }
    //POST
    public CrearModel registrarUsuario(CrearModel modeloCrear){
        //Validar si existe usuario
        if (crearRepository.findByCorreo(modeloCrear.getCorreo()).isPresent()){
            throw new RuntimeException("Usuario ya existe");
        }

        String passwordEncriptada = passwordEncoder.encode(modeloCrear.getPassword());

        CrearModel nuevo = new CrearModel(modeloCrear.getCorreo(), passwordEncriptada);

        modeloCrear.setPassword(passwordEncriptada);
        return crearRepository.save(modeloCrear);
    }

    public CrearModel buscarUsuarioPorCorreo(CrearModel datosUsuario) {
        return crearRepository.findByCorreo(datosUsuario.getCorreo()).orElseThrow(() -> new RuntimeException("Correo no encontrado o no coincide."));
    }

    // public CrearModel registrarUsuario(String correo, String passwordPlana){
    //     //Validar si existe usuario
    //     if (crearRepository.findByCorreo(correo).isPresent()){
    //         throw new RuntimeException("Usuario ya existe");
    //     }

    //     String passwordEncriptada = passwordEncoder.encode(passwordPlana);

    //     CrearModel nuevo = new CrearModel(correo, passwordEncriptada);

    //     return crearRepository.save(nuevo);
    // }

    //PUT
    public CrearModel actualizarCrear(Long id, CrearModel crearActualizado){
        CrearModel crearActual = buscarCrearporId(id);
        crearActual.setId(id);
        //Para ver si hay algun dato en la table
        if (crearActual.getNombre()!= null) {
            crearActual.setNombre(crearActualizado.getNombre());
        }

        return crearRepository.save(crearActual);
    }
    //DELETE
    public String borrarCrear(Long id){
        CrearModel crear = buscarCrearporId(id);
        crearRepository.deleteById(crear.getId());
        return "Cuenta borrada";
    }

}