package com.example.Ev2.entidadMueble;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/muebles")
public class MuebleController {

    @Autowired
    private MuebleService muebleService;

    @PostMapping
    public Mueble crearMueble(@RequestBody Mueble mueble) {
        return muebleService.crearMueble(mueble);
    }

    @GetMapping
    public List<Mueble> listarMuebles() {
        return muebleService.listarMuebles();
    }

    @GetMapping("/{id}")
    public Optional<Mueble> obtenerMueblePorId(@PathVariable Long id) {
        return muebleService.obtenerMueblePorId(id);
    }

    @PutMapping("/{id}")
    public Mueble actualizarMueble(@PathVariable Long id, @RequestBody Mueble mueble) {
        return muebleService.actualizarMueble(id, mueble);
    }

    @PutMapping("/{id}/desactivar")
    public Mueble desactivarMueble(@PathVariable Long id) {
        return muebleService.desactivarMueble(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarMueble(@PathVariable Long id) {
        muebleService.eliminarMueble(id);
    }

    @PostMapping("/{id}/variaciones")
    public Mueble agregarVariacion(@PathVariable Long id, @RequestBody Variacion variacion) {
        return muebleService.agregarVariacion(id, variacion);
    }
}
