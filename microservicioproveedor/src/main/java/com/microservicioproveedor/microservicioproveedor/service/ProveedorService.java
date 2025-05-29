package com.microservicioproveedor.microservicioproveedor.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microservicioproveedor.microservicioproveedor.model.ProveedorModel;
import com.microservicioproveedor.microservicioproveedor.repository.ProveedorRepository;

@Service
public class ProveedorService {
@Autowired
private ProveedorRepository proveedorRepository;

    public List<ProveedorModel> obtenerListadoProveedores(){
        return proveedorRepository.findAll();
    }

    public ProveedorModel buscarProveedorporId(Long id){
        return proveedorRepository.findById(id).orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
    }

    public ProveedorModel guardarProveedor(ProveedorModel proveedorNuevo){
        return proveedorRepository.save(proveedorNuevo);
    }

    public ProveedorModel actualizarProveedor(Long id, ProveedorModel proveedorActualizado) {
        ProveedorModel proveedorActual = buscarProveedorporId(id);
        if (proveedorActualizado.getNombre_proveedor() != null) {
            proveedorActual.setNombre_proveedor(proveedorActualizado.getNombre_proveedor());
        }
        if (proveedorActualizado.getTipo_servicio() != null) {
            proveedorActual.setTipo_servicio(proveedorActualizado.getTipo_servicio());
        }
        if (proveedorActualizado.getCorreo_contacto() != null) {
            proveedorActual.setCorreo_contacto(proveedorActualizado.getCorreo_contacto());
        }
        if (proveedorActualizado.getFecha_inicio_contrato() != null) {
            proveedorActual.setFecha_inicio_contrato(proveedorActualizado.getFecha_inicio_contrato());
        }
        return proveedorRepository.save(proveedorActual);
    }    

    public String borrarProveedor(Long id){
        ProveedorModel proveedor = buscarProveedorporId(id);
        proveedorRepository.deleteById(proveedor.getId());
        return "Proveedor borrado";
        }

    }


