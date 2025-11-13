package com.example.Ev2.entidadMueble;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "variacion")
public class Variacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_variacion;

    private String nombre;
    private Double precio_extra; 

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mueble")
    @JsonIgnore
    private Mueble mueble;

    public Variacion() {}

    public Variacion(String nombre, Double precio_extra, Mueble mueble) {
        this.nombre = nombre;
        this.precio_extra = precio_extra;
        this.mueble = mueble;
    }
    
    public Long getId_variacion() { return id_variacion; }
    public void setId_variacion(Long id_variacion) { this.id_variacion = id_variacion; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Double getPrecio_extra() { return precio_extra; }
    public void setPrecio_extra(Double precio_extra) { this.precio_extra = precio_extra; }

    public Mueble getMueble() { return mueble; }
    public void setMueble(Mueble mueble) { this.mueble = mueble; }
}