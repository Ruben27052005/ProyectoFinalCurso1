package com.example.demo.Controller;

import com.example.demo.Entity.Juego;
import com.example.demo.Entity.JuegoCarrito;
import com.example.demo.Service.JuegoCarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/juego_carrito")
public class JuegoCarritoController {
    @Autowired
    private JuegoCarritoService juegoCarritoService;

    @GetMapping("/")
    public String mostrarJuegoCarrito(Model model) {
        String vista = "./juego_carrito/listar";
        try {
            model.addAttribute("juegoCarritos", juegoCarritoService.obtenerJuegoCarrito());
        } catch (Exception ex) {
            Logger.getLogger(JuegoCarritoController.class.getName()).log(Level.SEVERE, null, ex);
            vista = "error";
        }
        return vista;
    }

    @GetMapping("/agregar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("juegoCarrito", new JuegoCarrito());
        return "./juego_carrito/agregar";
    }

    @PostMapping("/agregar")
    public String agregarJuegoAlCarrito(@ModelAttribute JuegoCarrito juegoCarrito, @RequestParam Long idJuego, Model model) {
        String vista = "redirect:/juego_carrito/";
        try {
            Juego juego = new Juego(); // Aquí debes obtener el juego por su id desde el servicio o el repositorio
            juegoCarrito.agregarJuego(juego);
            juegoCarritoService.actualizarJuegoCarrito(juegoCarrito);
            model.addAttribute("juegoCarritos", juegoCarritoService.obtenerJuegoCarrito());
        } catch (Exception ex) {
            Logger.getLogger(JuegoCarritoController.class.getName()).log(Level.SEVERE, null, ex);
            vista = "error";
        }
        return vista;
    }

    @GetMapping("/eliminar")
    public String eliminarJuegoDelCarrito(@RequestParam Long idJuego, Model model) {
        String vista = "redirect:/juego_carrito/";
        try {
            juegoCarritoService.eliminarJuegoCarrito(idJuego);
            model.addAttribute("juegoCarritos", juegoCarritoService.obtenerJuegoCarrito());
        } catch (Exception ex) {
            Logger.getLogger(JuegoCarritoController.class.getName()).log(Level.SEVERE, null, ex);
            vista = "error";
        }
        return vista;
    }
}
