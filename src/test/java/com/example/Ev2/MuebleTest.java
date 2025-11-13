package com.example.Ev2;

import java.util.List;
 
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.Ev2.entidadMueble.Mueble;
import com.example.Ev2.entidadMueble.Variacion;

class MuebleTest {

    @Test
    void testCalculoPrecioConVariaciones() {
        Mueble mueble = new Mueble();
        mueble.setPrecio_base(1000.0);

        Variacion var1 = new Variacion("Pintura Premium", 200.0, mueble);
        Variacion var2 = new Variacion("Madera Roble", 300.0, mueble);

        mueble.setVariaciones(List.of(var1, var2));

        Double precioFinal = mueble.getPrecio_final();
        
        Assertions.assertEquals(1500.0, precioFinal, "El precio final debería ser la suma de base + variaciones");
    }

    @Test
    void testPrecioSinVariaciones() {
        Mueble mueble = new Mueble();
        mueble.setPrecio_base(1000.0);
        
        Assertions.assertEquals(1000.0, mueble.getPrecio_final(), "Si no hay variaciones, el precio final debe ser el base");
    }
}