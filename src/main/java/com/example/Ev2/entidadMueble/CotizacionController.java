package com.example.Ev2.entidadMueble;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cotizaciones")
public class CotizacionController {

    @Autowired
    private CotizacionService cotizacionService;

    @PostMapping
    public Cotizacion crear(@RequestBody List<CotizacionService.DetalleSolicitud> solicitud) {
        return cotizacionService.crearCotizacion(solicitud);
    }

    @PostMapping("/{id}/confirmar")
    public ResponseEntity<?> confirmarVenta(@PathVariable Long id) {
        try {
            Cotizacion venta = cotizacionService.confirmarVenta(id);
            return ResponseEntity.ok(venta);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}