package com.example.demo.Controller;

import com.example.demo.Entity.Contacto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/contacto")
public class ContactoController {

    @GetMapping("/")
    public String crud(Model model) {
        String valorfinal = "./contacto/vistalistar";
        try {
            model.addAttribute("contactos", Contacto.obtenerContactos());
        } catch (Exception ex) {
            Logger.getLogger(ContactoController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }

    @GetMapping("/add")
    public String greetingForm(Model model) {
        model.addAttribute("contacto", new Contacto());
        return "./contacto/add";
    }

    @PostMapping("/add")
    public String greetingForm(@ModelAttribute Contacto contacto, Model model) { 
        String valorfinal = "redirect:/contacto/";
        try {
            Contacto.crearContacto(contacto.getNombre(), contacto.getTelefono(), contacto.getEmail());
            try {
                model.addAttribute("contactos", Contacto.obtenerContactos());
            } catch (Exception ex) {
                Logger.getLogger(ContactoController.class.getName()).log(Level.SEVERE, null, ex);
                valorfinal = "error";
            }
        } catch (Exception e) {
            valorfinal = "error";
        }
        return valorfinal;
    }

    @GetMapping("/editar")
    public String modificar(@RequestParam("id") Long id, Model model) {
        String valorfinal = "./contacto/editar";
        try {
            model.addAttribute("contacto", Contacto.buscarContacto(id));
        } catch (Exception ex) {
            Logger.getLogger(ContactoController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }

    @PostMapping("/editar")
    public String modificarBBDD(@ModelAttribute Contacto contacto, Model model) {
        String valorfinal = "redirect:/contacto/";
        try {
            Contacto.actualizarContacto(contacto);
            model.addAttribute("contactos", Contacto.obtenerContactos());
        } catch (Exception ex) {
            Logger.getLogger(ContactoController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }

    @GetMapping("/eliminar")
    public String SubmitB (@RequestParam("id") Long id, Model model){
        String valorfinal="redirect:/contacto/";
        try {
            Contacto.eliminarContacto(id);
            model.addAttribute("contactos", Contacto.obtenerContactos());
        } catch (Exception ex) {
            valorfinal="error";
        }
        return valorfinal;
    }
}
