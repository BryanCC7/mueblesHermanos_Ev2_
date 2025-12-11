package com.example.Ev2.entidadMueble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class tiendaController {

    @Autowired private MuebleService muebleService;
    @Autowired private CotizacionRepository cotizacionRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("muebles", muebleService.listarMuebles());
        return "cliente_catalogo"; 
    }

    @GetMapping("/admin/muebles")
    public String adminMuebles(Model model) {
        model.addAttribute("muebles", muebleService.listarMuebles());
        return "admin_muebles";
    }

    @GetMapping("/admin/ventas")
    public String adminVentas(Model model) {
        model.addAttribute("ventas", cotizacionRepository.findAll());
        return "admin_ventas";
    }

}