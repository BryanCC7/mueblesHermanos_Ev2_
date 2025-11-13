package com.example.Ev2;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Ev2.entidadMueble.Mueble;
import com.example.Ev2.entidadMueble.MuebleRepository;
import com.example.Ev2.entidadMueble.MuebleService;

@ExtendWith(MockitoExtension.class)
class MuebleServiceTest {

    @Mock
    private MuebleRepository muebleRepository;

    @InjectMocks
    private MuebleService muebleService;

    @Test
    void crearMueble_DeberiaGuardarEnRepositorio() {
        Mueble nuevo = new Mueble();
        nuevo.setNombre_mueble("Mesa");

        when(muebleRepository.save(nuevo)).thenReturn(nuevo);

        Mueble guardado = muebleService.crearMueble(nuevo);

        assertNotNull(guardado);
        verify(muebleRepository).save(nuevo);
    }

    @Test
    void listarMuebles_DeberiaRetornarLista() {
        when(muebleRepository.findAll()).thenReturn(Arrays.asList(new Mueble(), new Mueble()));

        List<Mueble> lista = muebleService.listarMuebles();

        assertEquals(2, lista.size());
        verify(muebleRepository).findAll();
    }

    @Test
    void actualizarMueble_SiExiste_DeberiaActualizarValores() {
        Long id = 1L;
        Mueble existente = new Mueble();
        existente.setNombre_mueble("Viejo");
        
        Mueble nuevosDatos = new Mueble();
        nuevosDatos.setNombre_mueble("Nuevo");

        when(muebleRepository.findById(id)).thenReturn(Optional.of(existente));
        when(muebleRepository.save(existente)).thenReturn(existente);

        Mueble resultado = muebleService.actualizarMueble(id, nuevosDatos);

        assertEquals("Nuevo", resultado.getNombre_mueble());
        verify(muebleRepository).save(existente);
    }
}