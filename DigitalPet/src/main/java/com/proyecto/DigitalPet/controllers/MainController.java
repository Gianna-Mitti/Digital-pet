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
    public String index(@RequestParam(required = false) String login, @RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model) {
        if (login != null) {
            model.put("exito", "Ha ingresado exitosamente. \n Para llevar un control más eficiente de las vacunas de tu/s mascota/s, te sugerimos ingresar con preiodicidad en la sección vacunas de ésta plataforma.");
        }        
        if (error != null) {
            model.put("error", "Usuario/clave incorrectos.");
        }
        if (logout != null) {
            model.put("exito", "Ha salido de manera segura.");
        }
        return "index.html";
    }

    @GetMapping("/recursos")
    public String recursos() {
        return "recursos.html";
    }
}
