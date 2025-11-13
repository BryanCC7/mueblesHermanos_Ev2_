package com.example.Ev2.entidadMueble;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "mueble")
public class Mueble {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_mueble;

    private String nombre_mueble;
    private String tipo;
    private Double precio_base;
    private Integer stock;
    private String estado;
    private String tamano;
    private String material;

    @OneToMany(mappedBy = "mueble", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Variacion> variaciones = new ArrayList<>();

    public Double getPrecio_final() {
        Double total = this.precio_base;
        if (variaciones != null) {
            for (Variacion v : variaciones) {
                total += v.getPrecio_extra();
            }
        }
        return total;
    }
    
    public Long getId_mueble() { return id_mueble; }
    public void setId_mueble(Long id_mueble) { this.id_mueble = id_mueble; }

    public String getNombre_mueble() { return nombre_mueble; }
    public void setNombre_mueble(String nombre_mueble) { this.nombre_mueble = nombre_mueble; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Double getPrecio_base() { return precio_base; }
    public void setPrecio_base(Double precio_base) { this.precio_base = precio_base; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getTamano() { return tamano; }
    public void setTamano(String tamano) { this.tamano = tamano; }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    public List<Variacion> getVariaciones() { return variaciones; }
    public void setVariaciones(List<Variacion> variaciones) { this.variaciones = variaciones; }
    
    public void agregarVariacion(Variacion variacion) {
        variaciones.add(variacion);
        variacion.setMueble(this);
    }
}