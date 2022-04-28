package com.proyecto.DigitalPet.controllers;

import com.proyecto.DigitalPet.entidades.Mascota;
import com.proyecto.DigitalPet.entidades.Vacuna;
import com.proyecto.DigitalPet.enums.Especie;
import com.proyecto.DigitalPet.errores.ErrorServicio;
import com.proyecto.DigitalPet.servicios.MascotaServ;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mascota")
public class MascotaController {

    @Autowired
    private MascotaServ mascotaServ;
    
    @GetMapping("/list-mascotas")
    public String lista(ModelMap modelo) {

        List<Mascota> listaM = mascotaServ.findAll();

        modelo.addAttribute("mascotas", listaM);

        return "perfil.html";
    }
    ///////////PREGUNTAR
    @GetMapping("/form-mascota/{id}")
    public String registrar() {
        return "form-mascota.html";
    }

@PostMapping("/form-mascota/{id}")
public String registrar(ModelMap model, @PathVariable String idU, @RequestParam String nombre, @RequestParam Date fechaNac, @RequestParam String sexo, @RequestParam Especie especie) throws Exception {
    
    try{
        mascotaServ.crear(idU, nombre, fechaNac, sexo, especie);
        model.put("exito", "La mascota ha sido registrada exitosamente");
        return "redirect:/form-mascota-vac.html";
    } catch (Exception e) {
//        e.printStackTrace();
        model.put("error", e.getMessage());
        return "form-mascota.html";
    }
}
    
    @GetMapping("/form-mascota-vac/{id}")
    public String cargarVac(@PathVariable String idMascota, ModelMap model) throws ErrorServicio {
        model.put("mascota", mascotaServ.buscarMxId(idMascota));
        return "form-mascota-vac.html";
    }

    @PostMapping("form-mascota-vac/{id}")
    public String cargarVac(@PathVariable String idUsuario, @PathVariable String idMascota, @RequestParam ArrayList<Vacuna> vacAplicadas, ModelMap model) throws ErrorServicio {
        try{
            mascotaServ.cargarVacunas(idUsuario, idMascota, vacAplicadas);
            model.put("exito", "Las vacunas han sido cargadas exitosamente.");
            return "perfil.html";
        } catch (Exception e) {
            model.put("error", e.getMessage());
            return "from-mascota-vac.html";
        }
    }

    @GetMapping("/baja/{id}")
    public String baja(@PathVariable String id, @PathVariable String idU, ModelMap model) {

        try {
            mascotaServ.eliminar(id, idU);
            return "redirect:/perfil";
        } catch (Exception e) {
            model.put("error", e.getMessage());
            return "redirect:/perfil";
        }
    }
    
        @GetMapping("/alta/{id}")
    public String alta(@PathVariable String id, @PathVariable String idU, ModelMap model) {

        try {
            mascotaServ.habilitar(id, idU);
            return "redirect:/perfil";
        } catch (Exception e) {
            model.put("error", e.getMessage());
            return "redirect:/perfil";
        }
    }

    @GetMapping("/modificar/{id}")
    public String editar(@PathVariable String idMascota, ModelMap model) throws Exception {

        model.put("mascota", mascotaServ.buscarMxId(idMascota));
        return "form-mascota-modif.html";
    }
    
    @PostMapping("/modificar/{id}")
    public String editar(@PathVariable String idUsuario, @PathVariable String idMascota, @RequestParam String nombre, @RequestParam Date fechaNac, @RequestParam String sexo, @RequestParam Especie especie, ModelMap modelo) throws Exception {

        try {
            mascotaServ.editar(idUsuario, idMascota, nombre, fechaNac, sexo, especie);
            return "redirect:/perfil";
            
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            modelo.put("mascota", mascotaServ.buscarMxId(idMascota));
            return "form-mascota-modif.html";
        }
    }
    
        @GetMapping("/list-vacunas/{id}")
    public String listarVacAp(@PathVariable String id, ModelMap modelo) throws ErrorServicio {

        List<Vacuna> vacAp = mascotaServ.listarVacAp(id);
        ///////////////////REVISAR
        modelo.addAttribute("vacunasAp", vacAp);

        List<Vacuna> vacPend = mascotaServ.listarVacPend(id);
        ///////////////////REVISAR
        modelo.addAttribute("vacunasPend", vacPend);
        
        return "perfil.html";
    }
}
