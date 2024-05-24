package com.example.demo.Controller;

import com.example.demo.Entity.Juego;
import com.example.demo.Service.JuegoService;
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
    private JuegoService juegoService;

    private static final Logger LOGGER = Logger.getLogger(JuegoController.class.getName());

    @GetMapping("/")
    public String getAllJuegos(Model model) {
        try {
            model.addAttribute("juegos", juegoService.obtenerJuegos());
            return "./juegos/listar";
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving games", ex);
            return "error";
        }
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("juego", new Juego());
        return "./juegos/add";
    }

    @PostMapping("/add")
    public String addJuego(@ModelAttribute Juego juego, Model model) {
        try {
            juegoService.crearJuego(juego);
            model.addAttribute("juegos", juegoService.obtenerJuegos());
            return "redirect:/juegos/";
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error adding game", ex);
            return "error";
        }
    }

    @GetMapping("/editar")
    public String editForm(@RequestParam("id") Long id, Model model) {
        try {
            model.addAttribute("juego", juegoService.buscarJuego(id));
            return "./juegos/editar";
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving game for edit", ex);
            return "error";
        }
    }

    @PostMapping("/editar")
    public String updateJuego(@ModelAttribute Juego juego, Model model) {
        try {
            juegoService.actualizarJuego(juego);
            model.addAttribute("juegos", juegoService.obtenerJuegos());
            return "redirect:/juegos/";
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error updating game", ex);
            return "error";
        }
    }

    @GetMapping("/eliminar")
    public String deleteJuego(@RequestParam("id") Long id, Model model) {
        try {
            juegoService.eliminarJuego(id);
            model.addAttribute("juegos", juegoService.obtenerJuegos());
            return "redirect:/juegos/";
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error deleting game", ex);
            return "error";
        }
    }
}
