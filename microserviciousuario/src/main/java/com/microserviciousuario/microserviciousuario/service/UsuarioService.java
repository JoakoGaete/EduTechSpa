package com.microserviciousuario.microserviciousuario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microserviciousuario.microserviciousuario.model.UsuarioModel;
import com.microserviciousuario.microserviciousuario.repository.UsuarioRepository;


@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioModel> obtenerListadoUsuarios(){
        return usuarioRepository.findAll();
    }

    public UsuarioModel buscarUsuarioPorId(Long id_rut){
        return usuarioRepository.findById(id_rut).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public UsuarioModel guardarUsuario(UsuarioModel usuarioNuevo){
        return usuarioRepository.save(usuarioNuevo);
    }


    
    public UsuarioModel actualizarUsuario(Long id, UsuarioModel usuarioActualizado) {
    UsuarioModel usuarioActual = buscarUsuarioPorId(id);

    if (usuarioActualizado.getNombres() != null) {
        usuarioActual.setNombres(usuarioActualizado.getNombres());
    }
    if (usuarioActualizado.getApellidoPaterno() != null) {
        usuarioActual.setApellidoPaterno(usuarioActualizado.getApellidoPaterno());
    }
    if (usuarioActualizado.getApellidoMaterno() != null) {
        usuarioActual.setApellidoMaterno(usuarioActualizado.getApellidoMaterno());
    }
    if (usuarioActualizado.getDireccion() != null) {
        usuarioActual.setDireccion(usuarioActualizado.getDireccion());
    }
    if (usuarioActualizado.getFormaPago() != null) {
        usuarioActual.setFormaPago(usuarioActualizado.getFormaPago());
    }

    return usuarioActual;

    }    

    public UsuarioModel buscarUsuarioPorRut(String rutUsuario) {
        return usuarioRepository.findByRutUsuario(rutUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public String borrarUsuario(Long id){
        UsuarioModel usuario = buscarUsuarioPorId(id);
        usuarioRepository.deleteById(usuario.getId());
        return "Usuario borrado";
        }
}
