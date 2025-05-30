package com.microserviciocrear.microserviciocrear.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cuentas")
public class CrearModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    
    private String nombre;
    private String apellido;
    private String correo;
    private String password;
    private String rol;

    public CrearModel(String correo, String password){
        this.correo = correo;
        this.password = password;
    }
}
