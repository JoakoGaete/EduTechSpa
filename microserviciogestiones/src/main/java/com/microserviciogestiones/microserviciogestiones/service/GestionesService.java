package com.microserviciogestiones.microserviciogestiones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microserviciogestiones.microserviciogestiones.model.GestionesModel;
import com.microserviciogestiones.microserviciogestiones.repository.GestionesRepository;

@Service
public class GestionesService {
@Autowired 
private GestionesRepository gestionesRepository;
    //GET
    public List<GestionesModel> obtenerListadoGestiones(){
        return gestionesRepository.findAll();
    }
    //GET
    public GestionesModel buscarGestionporId(Long id){
        return gestionesRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
    //POST
    public GestionesModel guardarGestion(GestionesModel gestionNuevo){
        return gestionesRepository.save(gestionNuevo);
    }

    //PUT
    public GestionesModel actualizarGestion(Long id, GestionesModel gestionActualizado){
        GestionesModel gestionActual = buscarGestionporId(id);
        gestionActual.setId(id);
        //Para ver si hay algun dato en la table
        if (gestionActual.getRutUsuario()!= null) {
            gestionActual.setRutUsuario(gestionActualizado.getRutUsuario());
        }

        return gestionesRepository.save(gestionActual);
    }
    //DELETE
    public String borrarGestion(Long id){
        GestionesModel gestion = buscarGestionporId(id);
        gestionesRepository.deleteById(gestion.getId());
        return "Gestion borrada";
    }

}

