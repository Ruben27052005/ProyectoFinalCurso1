package com.example.demo.Controller;

import com.example.demo.Entity.Carrito;
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
    private CarritoController carritoController;

    @GetMapping("/")
    public String getAllCarritos(Model model) {
        String valorfinal = "./carritos/vistalistar";
        try {
            model.addAttribute("carritos", carritoController.obtenerCarritos());
        } catch (Exception ex) {
            Logger.getLogger(CarritoController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }

    @GetMapping("/add")
    public String grettingForm(Model model) {
        model.addAttribute("carrito", new Carrito());
        return "./carritos/add";
    }

    @PostMapping("/add")
    public String greetingForm(@ModelAttribute Carrito carrito, Model model) {
        String valorfinal = "redirect:/carritos/";
        try {
            carritoController.crearCarrito(carrito);
            try {
                model.addAttribute("carritos", carritoController.obtenerCarritos());
            } catch (Exception ex) {
                Logger.getLogger(CarritoController.class.getName()).log(Level.SEVERE, null, ex);
                valorfinal = "error";
            }
        } catch (Exception e) {
            valorfinal = "error";
        }
        return valorfinal;
    }

    @GetMapping("/editar")
    public String modificar(@RequestParam("cod_carrito") int id, Model model) {
        String valorfinal = "./carritos/editar";
        try {
            model.addAttribute("carrito", carritoController.buscar(id));
        } catch (Exception ex) {
            Logger.getLogger(CarritoController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }

    @PostMapping("/editar")
    public String modificarBBDD(@ModelAttribute Carrito carrito, Model model) {
        String valorfinal = "redirect:/carritos/";
        try {
            CarritoController.actualizarCarrito(carrito);
            model.addAttribute("carritos", carritoController.obtenerCarritos());
        } catch (Exception ex) {
            Logger.getLogger(CarritoController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }

    // Lógica para eliminar una entidad
    // Implementar método y ruta correspondiente si deseas tener funcionalidad de eliminación
    @GetMapping("/eliminar")
    public String SubmitB (@RequestParam("cod_carrito") int id, Model model){
        String valorfinal="redirect:/carritos/";
        try {
            model.addAttribute("carritos",carritoController.eliminarCarrito(id));
        } catch (Exception ex) {
            valorfinal="error";
        }
        return valorfinal;
    }
}
