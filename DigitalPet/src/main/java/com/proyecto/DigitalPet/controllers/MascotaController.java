package com.proyecto.DigitalPet.controllers;

import com.proyecto.DigitalPet.entidades.Mascota;
import com.proyecto.DigitalPet.entidades.Vacuna;
import com.proyecto.DigitalPet.enums.Especie;
import com.proyecto.DigitalPet.errores.ErrorServicio;
import com.proyecto.DigitalPet.servicios.MascotaServ;
import java.time.LocalDate;
import java.util.ArrayList;
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
    
    @GetMapping("/list-mascotas/{id}")
    public String lista(ModelMap modelo) {

        List<Mascota> listaM = mascotaServ.findAll();

        modelo.addAttribute("mascotas", listaM);

        return "list-mascota.html";
    }
    ///////////PREGUNTAR
    @GetMapping("/form-mascota/{idU}")
    public String registrar() {
        return "form-mascota.html";
    }

@PostMapping("/form-mascota/{idU}")
public String registrar(ModelMap model, @PathVariable String idU, @RequestParam String nombre, @RequestParam String fechaNacS, @RequestParam String sexo, @RequestParam String especie) throws Exception {
    
    LocalDate fechaNac = LocalDate.parse(fechaNacS); 

    try{
        mascotaServ.crear(idU, nombre, fechaNac, sexo, especie);
        model.put("exito", "La mascota ha sido registrada exitosamente");
        return "list-mascota.html";
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

    @GetMapping("/baja/{id}/{idU}")
    public String baja(@PathVariable String id, @PathVariable String idU, ModelMap model) {

        try {
            mascotaServ.eliminar(id, idU);
            return "redirect:/mascota/list-mascotas/{id}";
        } catch (Exception e) {
            model.put("error", e.getMessage());
            return "redirect:/mascota/list-mascotas/{id}";
        }
    }
    
        @GetMapping("/alta/{id}/{idU}")
    public String alta(@PathVariable String id, @PathVariable String idU, ModelMap model) {

        try {
            mascotaServ.habilitar(id, idU);
            return "redirect:/mascota/list-mascotas/{id}";
        } catch (Exception e) {
            model.put("error", e.getMessage());
            return "redirect:/mascota/list-mascotas/{id}";
        }
    }

    @GetMapping("/modificar/{id}/{idU}")
    public String editar(ModelMap model, @PathVariable String id, @PathVariable String idU) throws Exception {

        model.put("mascota", mascotaServ.buscarMxId(id));
        return "form-mascota-modif.html";
    }
    
    @PostMapping("/modificar/{id}/{idU}")
    public String editar(ModelMap model, @PathVariable String id, @PathVariable String idU, @RequestParam String nombre, @RequestParam String fechaNacS, @RequestParam String sexo, @RequestParam String especie) throws Exception {

        LocalDate fechaNac = LocalDate.parse(fechaNacS); 

        try {
            mascotaServ.editar(idU, id, nombre, fechaNac, sexo, especie);
            return "redirect:/mascota/list-mascotas/{id}";
            
        } catch (Exception e) {
            model.put("error", e.getMessage());
            model.put("mascota", mascotaServ.buscarMxId(id));
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
