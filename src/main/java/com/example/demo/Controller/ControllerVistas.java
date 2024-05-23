package com.example.demo.Controller;

import com.example.demo.Entity.Usuario;
import com.example.demo.Service.UsuarioController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/usuario")
public class ControllerVistas {
    UsuarioController usuarioController = new UsuarioController();
    @GetMapping("/")
    public String crud(Model model) {
        String valorfinal = "./usuario/vistalistar";
        try {
            model.addAttribute("usuarios", usuarioController.obtenerUsuarios());
        } catch (Exception ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }
    @GetMapping("/add")
    public String grettingForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "./usuario/add";
    }
    @PostMapping("/add")
    public String greetingForm(@ModelAttribute Usuario usuario, Model model) {
        String valorfinal = "redirect:/usuario/";
        try {
            usuarioController.crearUsuario(usuario);
            try {
                model.addAttribute("usuarios", usuarioController.obtenerUsuarios());
            } catch (SQLException ex) {
                Logger.getLogger(ControllerVistas.class.getName()).log(Level.SEVERE, null, ex);
                valorfinal = "error";
            }
        } catch (SQLException e) {
            valorfinal = "error";
        }
        return valorfinal;
    }
    @GetMapping("/editar")
    public String modificar(@RequestParam("cod_usuario") int id, Model model) {
        String valorfinal = "./usuario/editar";
        try {
            model.addAttribute("usuario", usuarioController.buscar(id));
        } catch (SQLException ex) {
            Logger.getLogger(ControllerVistas.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }
    @PostMapping("/editar")
    public String modificarBBDD(@ModelAttribute Usuario usuario, Model model) {
        String valorfinal = "redirect:/usuario/";
        try {
            UsuarioController.actualizarUsuario(usuario);
            model.addAttribute("tareas", usuarioController.obtenerUsuarios());
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            valorfinal = "error";
        }
        return valorfinal;
    }
    // Lógica para eliminar una entidad
    // Implementar método y ruta correspondiente si deseas tener funcionalidad de eliminación
    @GetMapping("/eliminar")
    public String SubmitB (@RequestParam("cod_usuario") int id, Model model){
        String valorfinal="redirect:/usuario/";
        try {
            model.addAttribute("clientes",usuarioController.eliminarUsuario(id));
        } catch (SQLException ex) {
            valorfinal="error";
        }
        return valorfinal;
    }
}
