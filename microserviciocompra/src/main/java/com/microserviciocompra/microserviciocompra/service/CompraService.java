package com.microserviciocompra.microserviciocompra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microserviciocompra.microserviciocompra.model.CompraModel;
import com.microserviciocompra.microserviciocompra.repository.CompraRepository;
@Service
public class CompraService {
    //GET PUT POST DELETE
@Autowired 
private CompraRepository compraRepository;
    //GET
    public List<CompraModel> obtenerListadoCompra(){
        return compraRepository.findAll();
    }
    //GET
    public CompraModel buscarCompraporId(Long id){
        return compraRepository.findById(id).orElseThrow(() -> new RuntimeException("Compra no encontrada"));
    }
    //POST
    public CompraModel guardarCompra(CompraModel compraNuevo){
        return compraRepository.save(compraNuevo);
    }

    //PUT
    public CompraModel actualizarCompra(Long id, CompraModel compraActualizado){
        CompraModel compraActual = buscarCompraporId(id);
        compraActual.setId(id);
        //Para ver si hay algun dato en la table
        if (compraActual.getCurso()!= null) {
            compraActual.setCurso(compraActualizado.getCurso());
        }

        return compraRepository.save(compraActual);
    }
    //DELETE
    public String borrarCompra(Long id){
        CompraModel compra = buscarCompraporId(id);
        compraRepository.deleteById(compra.getId());
        return "compra borrada";
    }

}


