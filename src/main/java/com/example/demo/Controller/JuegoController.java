package com.example.demo.Controller;

import com.example.demo.Entity.Juego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/juegos")
public class JuegoController {

    @Autowired
    private JuegoController juegoController;

    @GetMapping("/")
    public String getAllJuegos(Model model) {
        String valorfinal = "./juegos/listar";
        try {
            model.addAttribute("juegos", juegoController.obtenerJuegos());
        } catch (Exception ex) {
            Logger.getLogger(JuegoController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }

    @GetMapping("/add")
    public String greetingForm(Model model) {
        model.addAttribute("juego", new Juego());
        return "./juegos/add";
    }

    @PostMapping("/add")
    public String greetingForm(@ModelAttribute Juego juego, Model model) {
        String valorfinal = "redirect:/juegos/";
        try {
            juegoController.crearJuego(juego);
            try {
                model.addAttribute("juegos", juegoController.obtenerJuegos());
            } catch (Exception ex) {
                Logger.getLogger(JuegoController.class.getName()).log(Level.SEVERE, null, ex);
                valorfinal = "error";
            }
        } catch (Exception e) {
            valorfinal = "error";
        }
        return valorfinal;
    }

    @GetMapping("/editar")
    public String modificar(@RequestParam("id") Long id, Model model) {
        String valorfinal = "./juegos/editar";
        try {
            model.addAttribute("juego", juegoController.buscarJuego(id));
        } catch (Exception ex) {
            Logger.getLogger(JuegoController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }

    @PostMapping("/editar")
    public String modificarBBDD(@ModelAttribute Juego juego, Model model) {
        String valorfinal = "redirect:/juegos/";
        try {
            juegoController.actualizarJuego(juego);
            model.addAttribute("juegos", juegoController.obtenerJuegos());
        } catch (Exception ex) {
            Logger.getLogger(JuegoController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }

    @GetMapping("/eliminar")
    public String SubmitB (@RequestParam("id") Long id, Model model){
        String valorfinal = "redirect:/juegos/";
        try {
            juegoController.eliminarJuego(id);
            model.addAttribute("juegos", juegoController.obtenerJuegos());
        } catch (Exception ex) {
            valorfinal = "error";
        }
        return valorfinal;
    }
}
