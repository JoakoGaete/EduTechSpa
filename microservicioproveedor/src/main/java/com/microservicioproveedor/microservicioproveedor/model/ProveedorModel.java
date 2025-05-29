package com.microservicioproveedor.microservicioproveedor.model;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tabla_proveedores")
public class ProveedorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "El campo no puede estar vacio.")
    @Column(name =  "nombre_proveedor",nullable = false)
    private String nombre_proveedor;
    @NotNull(message = "El campo no puede estar vacio.")
    @Column(name =  "tipo_servicio",nullable = false)
    private String tipo_servicio;
    @NotNull(message = "El campo no puede estar vacio.")
    @Column(name =  "correo_contacto",nullable = false)
    private String correo_contacto;
    @NotNull(message = "El campo no puede estar vacio.")
    @Column(name =  "fecha_inicio_contrato",nullable = false) 
    private Date fecha_inicio_contrato;

}
