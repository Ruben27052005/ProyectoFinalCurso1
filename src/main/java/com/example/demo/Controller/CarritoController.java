package com.example.demo.Controller;

import com.example.demo.Entity.Carrito;
import com.example.demo.Service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/carritos")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    private static final Logger LOGGER = Logger.getLogger(CarritoController.class.getName());

    @GetMapping("/")
    public String getAllCarritos(Model model) {
        String valorfinal = "./carritos/vistalistar";
        try {
            model.addAttribute("carritos", carritoService.obtenerCarritos());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving carritos", ex);
            valorfinal = "error";
        }
        return valorfinal;
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("carrito", new Carrito());
        return "./carritos/add";
    }

    @PostMapping("/add")
    public String addCarrito(@ModelAttribute Carrito carrito, Model model) {
        String valorfinal = "redirect:/carritos/";
        try {
            carritoService.crearCarrito(carrito);
            model.addAttribute("carritos", carritoService.obtenerCarritos());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error adding carrito", ex);
            valorfinal = "error";
        }
        return valorfinal;
    }

    @GetMapping("/editar")
    public String editForm(@RequestParam("id") Long id, Model model) {
        String valorfinal = "./carritos/editar";
        try {
            model.addAttribute("carrito", carritoService.buscarCarrito(id));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving carrito for edit", ex);
            valorfinal = "error";
        }
        return valorfinal;
    }

    @PostMapping("/editar")
    public String updateCarrito(@ModelAttribute Carrito carrito, Model model) {
        String valorfinal = "redirect:/carritos/";
        try {
            carritoService.actualizarCarrito(carrito);
            model.addAttribute("carritos", carritoService.obtenerCarritos());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error updating carrito", ex);
            valorfinal = "error";
        }
        return valorfinal;
    }

    @GetMapping("/eliminar")
    public String deleteCarrito(@RequestParam("id") Long id, Model model) {
        String valorfinal = "redirect:/carritos/";
        try {
            carritoService.eliminarCarrito(id);
            model.addAttribute("carritos", carritoService.obtenerCarritos());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error deleting carrito", ex);
            valorfinal = "error";
        }
        return valorfinal;
    }
}
