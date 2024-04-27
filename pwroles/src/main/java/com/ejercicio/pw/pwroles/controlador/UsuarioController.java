package com.ejercicio.pw.pwroles.controlador;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ejercicio.pw.pwroles.service.UsuarioService;
import com.ejercicio.pw.pwroles.usuario.Usuario;


@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/ver/{id}")
    public String verDetallesDelUsuario(@PathVariable(value = "id") Long id, Map<String, Object> modelo, RedirectAttributes flash) {
        Usuario usuario = usuarioService.findOne(id);
        if (usuario == null) {
            flash.addFlashAttribute("error", "El usuario no existe en la base de datos");
            return "redirect:/listar";
        }

        modelo.put("usuario", usuario);
        modelo.put("titulo", "Detalles del usuario " + usuario.getNombre());
        return "ver";
    }



    @GetMapping("/form")
    public String mostrarFormularioDeRegistrarCliente(Map<String, Object> modelo) {
        Usuario usuario = new Usuario();
        modelo.put("empleado", usuario);
        modelo.put("titulo", "Registro de usuario");
        return "form";
    }

    @PostMapping("/form")
    public String guardarUsuario(@Valid Usuario usuario, BindingResult result, Model modelo, RedirectAttributes flash, SessionStatus status) {
        if (result.hasErrors()) {
            modelo.addAttribute("titulo", "Registro de usuario");
            return "form";
        }

        String mensaje = (usuario.getId() != null) ? "El usuario ha sido editado con éxito" : "Empleado registrado con éxito";

        usuarioService.save(usuario);
        status.setComplete();
        flash.addFlashAttribute("success", mensaje);
        return "redirect:/listar";
    }

    @GetMapping("/form/{id}")
    public String editarUsuario(@PathVariable(value = "id") Long id, Map<String, Object> modelo, RedirectAttributes flash) {
    	Usuario usuario = null;
        if (id > 0) {
        	usuario = usuarioService.findOne(id);
            if (usuario == null) {
                flash.addFlashAttribute("error", "ElID del usuario no existe en la base de datos");
                return "redirect:/listar";
            }
        } else {
            flash.addFlashAttribute("error", "El ID del usuario no puede ser cero");
            return "redirect:/listar";
        }

        modelo.put("usuario", usuario);
        modelo.put("titulo", "Edición de usuario");
        return "form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
        	usuarioService.delete(id);
            flash.addFlashAttribute("success", "Usuario eliminado con éxito");
        }
        return "redirect:/listar";
    }

    

}                		