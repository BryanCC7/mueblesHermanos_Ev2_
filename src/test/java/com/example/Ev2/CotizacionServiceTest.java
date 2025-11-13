package com.example.Ev2;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Ev2.entidadMueble.Cotizacion;
import com.example.Ev2.entidadMueble.CotizacionRepository;
import com.example.Ev2.entidadMueble.CotizacionService;
import com.example.Ev2.entidadMueble.DetalleCotizacion;
import com.example.Ev2.entidadMueble.Mueble;
import com.example.Ev2.entidadMueble.MuebleRepository;

@ExtendWith(MockitoExtension.class)
class CotizacionServiceTest {

    @Mock
    private CotizacionRepository cotizacionRepository;

    @Mock
    private MuebleRepository muebleRepository;

    @InjectMocks
    private CotizacionService cotizacionService;

    @Test
    void confirmarVenta_ConStockSuficiente_DeberiaDescontarStockYVender() {
        Long idCotizacion = 1L;
        
        Mueble muebleMock = new Mueble();
        muebleMock.setId_mueble(100L);
        muebleMock.setStock(10); 
        muebleMock.setNombre_mueble("Silla");

        DetalleCotizacion detalle = new DetalleCotizacion();
        detalle.setMueble(muebleMock);
        detalle.setCantidad(2);

        Cotizacion cotizacionMock = new Cotizacion();
        cotizacionMock.setId_cotizacion(idCotizacion);
        cotizacionMock.setEstado("CREADA");
        cotizacionMock.setDetalles(List.of(detalle));

        when(cotizacionRepository.findById(idCotizacion)).thenReturn(Optional.of(cotizacionMock));
        when(cotizacionRepository.save(any(Cotizacion.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Cotizacion resultado = cotizacionService.confirmarVenta(idCotizacion);

        assertEquals("VENDIDA", resultado.getEstado());
        assertEquals(8, muebleMock.getStock());
        verify(muebleRepository, times(1)).save(muebleMock);
    }

    
    @Test
    void confirmarVenta_SinStock_DeberiaLanzarExcepcion() {
        Long idCotizacion = 1L;

        Mueble muebleMock = new Mueble();
        muebleMock.setStock(1);

        DetalleCotizacion detalle = new DetalleCotizacion();
        detalle.setMueble(muebleMock);
        detalle.setCantidad(5);

        Cotizacion cotizacionMock = new Cotizacion();
        cotizacionMock.setEstado("CREADA");
        cotizacionMock.setDetalles(List.of(detalle));

        when(cotizacionRepository.findById(idCotizacion)).thenReturn(Optional.of(cotizacionMock));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            cotizacionService.confirmarVenta(idCotizacion);
        });

        assertEquals("Stock insuficiente de: null", exception.getMessage());

        verify(muebleRepository, never()).save(any());
    }
}