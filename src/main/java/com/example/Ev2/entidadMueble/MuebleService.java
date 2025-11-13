package com.example.Ev2.entidadMueble;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MuebleService {

    @Autowired
    private MuebleRepository muebleRepository;

    public Mueble crearMueble(Mueble mueble) {
        return muebleRepository.save(mueble);
    }

    public List<Mueble> listarMuebles() {
        return muebleRepository.findAll();
    }

    public Optional<Mueble> obtenerMueblePorId(Long id) {
        return muebleRepository.findById(id);
    }

    public Mueble actualizarMueble(Long id, Mueble datosMueble) {
        return muebleRepository.findById(id).map(mueble -> {
            mueble.setNombre_mueble(datosMueble.getNombre_mueble());
            mueble.setTipo(datosMueble.getTipo());
            mueble.setPrecio_base(datosMueble.getPrecio_base());
            mueble.setStock(datosMueble.getStock());
            mueble.setEstado(datosMueble.getEstado());
            mueble.setTamano(datosMueble.getTamano());
            mueble.setMaterial(datosMueble.getMaterial());
            return muebleRepository.save(mueble);
        }).orElseGet(() -> {
            datosMueble.setId_mueble(id);
            return muebleRepository.save(datosMueble);
        });
    }

    public Mueble desactivarMueble(Long id) {
        return muebleRepository.findById(id).map(mueble -> {
            mueble.setEstado("Inactivo");
            return muebleRepository.save(mueble);
        }).orElse(null);
    }

    public void eliminarMueble(Long id) {
        muebleRepository.deleteById(id);
    }

    public Mueble agregarVariacion(Long idMueble, Variacion variacion) {
    return muebleRepository.findById(idMueble).map(mueble -> {
        mueble.agregarVariacion(variacion);
        return muebleRepository.save(mueble);
    }).orElse(null);
}
}
