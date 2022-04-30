package com.proyecto.DigitalPet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping("/index")
    public String index(@RequestParam(required = false) String login, ModelMap model) {
        if (login != null) {
            model.put("exito", "Ha ingresado exitosamente.");
        } else {
            model.put("error", "Error al ingresar.");
        }
        return "perfil.html";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model) {
        if (error != null) {
            model.put("error", "Usuario/clave incorrectos.");
            return "login.html";
        }
        if (logout != null) {
            model.put("exito", "Ha salido de manera segura.");
            return "index.html";
        }
        return "index.html";
    }
}
