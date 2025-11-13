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
@Table(name = "detalle_cotizacion")
public class DetalleCotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle;

    @ManyToOne
    @JoinColumn(name = "id_mueble")
    private Mueble mueble;

    @ManyToOne
    @JoinColumn(name = "id_variacion")
    private Variacion variacion; 

    private Integer cantidad;
    private Double subtotal; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cotizacion")
    @JsonIgnore
    private Cotizacion cotizacion;

    public DetalleCotizacion() {}

    public Long getId_detalle() { return id_detalle; }
    public void setId_detalle(Long id_detalle) { this.id_detalle = id_detalle; }

    public Mueble getMueble() { return mueble; }
    public void setMueble(Mueble mueble) { this.mueble = mueble; }

    public Variacion getVariacion() { return variacion; }
    public void setVariacion(Variacion variacion) { this.variacion = variacion; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }

    public Cotizacion getCotizacion() { return cotizacion; }
    public void setCotizacion(Cotizacion cotizacion) { this.cotizacion = cotizacion; }
}