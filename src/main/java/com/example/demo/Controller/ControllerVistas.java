package com.example.demo.Controller;

import com.example.demo.Entity.Usuarios;
import com.example.demo.Service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/usuariosV")
public class ControllerVistas {
    private final UsuarioService usuarioController = new UsuarioService();

    @GetMapping("/")
    public String crud(Model model) {
        String valorfinal = "usuariosV/vistalistar";
        try {
            model.addAttribute("usuarios", usuarioController.obtenerUsuarios());
        } catch (Exception ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }

    @GetMapping("/add")
    public String grettingForm(Model model) {
        model.addAttribute("usuario", new Usuarios());
        return "usuariosV/add";
    }

    @PostMapping("/add")
    public String greetingForm(@ModelAttribute Usuarios usuario, Model model) {
        String valorfinal = "redirect:/usuariosV/";
        try {
            usuarioController.crearUsuario(usuario);
            model.addAttribute("usuarios", usuarioController.obtenerUsuarios());
        } catch (SQLException ex) {
            Logger.getLogger(ControllerVistas.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }

    @GetMapping("/editar")
    public String modificar(@RequestParam("cod_usuario") int id, Model model) {
        String valorfinal = "usuariosV/editar";
        try {
            model.addAttribute("usuario", usuarioController.buscar(id));
        } catch (SQLException ex) {
            Logger.getLogger(ControllerVistas.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }

    @PostMapping("/editar")
    public String modificarBBDD(@ModelAttribute Usuarios usuario, Model model) {
        String valorfinal = "redirect:/usuariosV/";
        try {
            UsuarioService.actualizarUsuario(usuario);
            model.addAttribute("usuarios", usuarioController.obtenerUsuarios());
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }

    @GetMapping("/eliminar")
    public String eliminar(@RequestParam("cod_usuario") int id, Model model) {
        String valorfinal = "redirect:/usuariosV/";
        try {
            usuarioController.eliminarUsuario(id);
            model.addAttribute("usuarios", usuarioController.obtenerUsuarios());
        } catch (SQLException ex) {
            valorfinal = "error";
        }
        return valorfinal;
    }
}
