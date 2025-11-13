package com.example.Ev2.entidadMueble;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CotizacionService {

    @Autowired
    private CotizacionRepository cotizacionRepository;

    @Autowired
    private MuebleRepository muebleRepository;

    @Autowired
    private VariacionRepository variacionRepository;

    public Cotizacion crearCotizacion(List<DetalleSolicitud> solicitudDetalles) {
        Cotizacion cotizacion = new Cotizacion();
        double totalAcumulado = 0.0;

        for (DetalleSolicitud item : solicitudDetalles) {
            Mueble mueble = muebleRepository.findById(item.muebleId)
                    .orElseThrow(() -> new RuntimeException("Mueble no encontrado"));

            DetalleCotizacion detalle = new DetalleCotizacion();
            detalle.setMueble(mueble);
            detalle.setCantidad(item.cantidad);

            double precioUnitario = mueble.getPrecio_base();

            if (item.variacionId != null) {
                Variacion variacion = variacionRepository.findById(item.variacionId)
                        .orElseThrow(() -> new RuntimeException("Variación no encontrada"));
                detalle.setVariacion(variacion);
                precioUnitario += variacion.getPrecio_extra();
            }

            detalle.setSubtotal(precioUnitario * item.cantidad);
            cotizacion.agregarDetalle(detalle);
            totalAcumulado += detalle.getSubtotal();
        }

        cotizacion.setTotal_venta(totalAcumulado);
        cotizacion.setEstado("CREADA");
        return cotizacionRepository.save(cotizacion);
    }

    @Transactional
    public Cotizacion confirmarVenta(Long idCotizacion) {
        Cotizacion cotizacion = cotizacionRepository.findById(idCotizacion)
                .orElseThrow(() -> new RuntimeException("Cotización no encontrada"));

        if ("VENDIDA".equals(cotizacion.getEstado())) {
            throw new RuntimeException("Esta cotización ya fue vendida.");
        }
        for (DetalleCotizacion detalle : cotizacion.getDetalles()) {
            Mueble mueble = detalle.getMueble();
            int cantidadSolicitada = detalle.getCantidad();

            if (mueble.getStock() < cantidadSolicitada) {
                throw new RuntimeException("Stock insuficiente de: " + mueble.getNombre_mueble());
            }

            mueble.setStock(mueble.getStock() - cantidadSolicitada);
            muebleRepository.save(mueble);
        }

        cotizacion.setEstado("VENDIDA");
        return cotizacionRepository.save(cotizacion);
    }
    
    public static class DetalleSolicitud {
        public Long muebleId;
        public Long variacionId;
        public Integer cantidad;
    }
}