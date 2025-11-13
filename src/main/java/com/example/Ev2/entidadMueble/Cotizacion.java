package com.example.Ev2.entidadMueble;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "cotizacion")
public class Cotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cotizacion;

    private LocalDateTime fecha_creacion;
    private Double total_venta;
    
    private String estado;

    @OneToMany(mappedBy = "cotizacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleCotizacion> detalles = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.fecha_creacion = LocalDateTime.now();
        if (this.estado == null) this.estado = "CREADA";
    }

    public void agregarDetalle(DetalleCotizacion detalle) {
        detalles.add(detalle);
        detalle.setCotizacion(this);
    }

    public Long getId_cotizacion() { return id_cotizacion; }
    public void setId_cotizacion(Long id_cotizacion) { this.id_cotizacion = id_cotizacion; }

    public LocalDateTime getFecha_creacion() { return fecha_creacion; }
    public void setFecha_creacion(LocalDateTime fecha_creacion) { this.fecha_creacion = fecha_creacion; }

    public Double getTotal_venta() { return total_venta; }
    public void setTotal_venta(Double total_venta) { this.total_venta = total_venta; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public List<DetalleCotizacion> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleCotizacion> detalles) { this.detalles = detalles; }
}