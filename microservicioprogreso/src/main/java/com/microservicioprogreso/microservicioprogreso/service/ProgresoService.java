package com.microservicioprogreso.microservicioprogreso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicioprogreso.microservicioprogreso.model.ProgresoModel;
import com.microservicioprogreso.microservicioprogreso.repository.ProgresoRepository;
@Service
public class ProgresoService {

@Autowired 
private ProgresoRepository progresoRepository;
    //GET
    public List<ProgresoModel> obtenerListadoProgreso(){
        return progresoRepository.findAll();
    }
    //GET
    public ProgresoModel buscarProgresoporId(Long id){
        return progresoRepository.findById(id).orElseThrow(() -> new RuntimeException("Rol No encontrado"));
    }
    //POST
    public ProgresoModel guardarProgreso(ProgresoModel progresoNuevo){
        return progresoRepository.save(progresoNuevo);
    }

    //PUT
    public ProgresoModel actualizarProgreso(Long idProgreso, ProgresoModel progresoActualizado){
        ProgresoModel progresoActual = buscarProgresoporId(idProgreso);
        progresoActual.setIdProgreso(idProgreso);
        //Para ver si hay algun dato en la table
        if (progresoActual.getNombreCurso()!= null) {
            progresoActual.setNombreCurso(progresoActualizado.getNombreCurso());
        }

        return progresoRepository.save(progresoActual);
    }
    //DELETE
    public String borrarProgreso(Long id){
        ProgresoModel progreso = buscarProgresoporId(id);
        progresoRepository.deleteById(progreso.getIdProgreso());
        return "Progreso borrado";
    }


}
