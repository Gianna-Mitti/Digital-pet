package com.proyecto.DigitalPet.controllers;

import com.proyecto.DigitalPet.entidades.Mascota;
import com.proyecto.DigitalPet.entidades.Usuario;
import com.proyecto.DigitalPet.entidades.Vacuna;
import com.proyecto.DigitalPet.errores.ErrorServicio;
import com.proyecto.DigitalPet.servicios.MascotaServ;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpSession;
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
    public String lista(ModelMap model, HttpSession session) throws ErrorServicio {

        Usuario u = (Usuario) session.getAttribute("usuariosesion");

        try{
            List<Mascota> listaM = mascotaServ.listarMascotas(u.getId());
            model.addAttribute("mascotas", listaM);
        } catch (Exception e) {
            model.put("error", e.getMessage());
        }


        return "list-mascota.html";
    }
    ///////////PREGUNTAR
    @GetMapping("/form-mascota")
    public String registrar() {
        return "form-mascota.html";
    }

    @PostMapping("/form-mascota")
    public String registrar(ModelMap model, HttpSession session, @RequestParam String nombre, @RequestParam String fechaNacS, @RequestParam String sexo, @RequestParam String especie) throws Exception {
        
        Usuario u = (Usuario) session.getAttribute("usuariosesion");

        LocalDate fechaNac = LocalDate.parse(fechaNacS); 

        try{
            mascotaServ.crear(u.getId(), nombre, fechaNac, sexo, especie);
            model.put("exito", "La mascota ha sido registrada exitosamente");
            return "redirect:/mascota/list-mascotas";
        } catch (Exception e) {
            model.put("error", e.getMessage());
            return "form-mascota.html";
        }
    }
    
    @GetMapping("/form-mascota-vac/{id}")
    public String cargarVac(ModelMap model, @PathVariable String id) throws Exception {
        
        try{
            Mascota m = mascotaServ.buscarMxId(id);
            model.put("mascota", m);
        } catch (Exception e) {
            model.put("error", e.getMessage());
        }
        
        return "form-mascota-vac.html";
    }

    @PostMapping("form-mascota-vac/{id}")
    public String cargarVac(ModelMap model, @PathVariable String id, HttpSession session, @RequestParam(value="vacAplicada") List<Vacuna> vacAplicadas) throws Exception {
        try{
            Usuario u = (Usuario) session.getAttribute("usuariosesion");
            mascotaServ.cargarVacunas(u.getId(), id, vacAplicadas);
            model.put("exito", "Las vacunas han sido cargadas exitosamente.");
            return "redirect:/mascota/list-vacunas/{id}";
        } catch (Exception e) {
            System.out.println("--------------------");
            System.out.println("error"+e.getMessage());
            model.put("error", e.getMessage());
            return "redirect:/mascota/form-mascota-vac/{id}";
        }
    }

    @GetMapping("/baja/{id}")
    public String baja(@PathVariable String id, HttpSession session, ModelMap model) {

        try {
            
            Usuario u = (Usuario) session.getAttribute("usuariosesion");
            mascotaServ.eliminar(id, u.getId());
            return "redirect:/mascota/list-mascotas";
        } catch (ErrorServicio e) {
            model.put("error", e.getMessage());
            return "redirect:/mascota/list-mascotas";
        }
    }
    
        @GetMapping("/alta/{id}")
    public String alta(@PathVariable String id, HttpSession session, ModelMap model) {

        try {
            
            Usuario u = (Usuario) session.getAttribute("usuariosesion");
            mascotaServ.habilitar(id, u.getId());
            return "redirect:/mascota/list-mascotas";
        } catch (ErrorServicio e) {
            model.put("error", e.getMessage());
            return "redirect:/mascota/list-mascotas";
        }
    }

    @GetMapping("/modificar/{id}")
    public String editar(ModelMap model, @PathVariable String id) throws Exception {

        model.put("mascota", mascotaServ.buscarMxId(id));
        return "form-mascota-modif.html";
    }
    
    @PostMapping("/modificar/{id}")
    public String editar(ModelMap model, @PathVariable String id, HttpSession session, @RequestParam String nombre, @RequestParam String fechaNacS, @RequestParam String sexo, @RequestParam String especie) throws Exception {

        LocalDate fechaNac = LocalDate.parse(fechaNacS); 

        try {
            
            Usuario u = (Usuario) session.getAttribute("usuariosesion");
            mascotaServ.editar(u.getId(), id, nombre, fechaNac, sexo, especie);
            return "redirect:/mascota/list-mascotas";
            
        } catch (ErrorServicio e) {
            model.put("error", e.getMessage());
            model.put("mascota", mascotaServ.buscarMxId(id));
            return "form-mascota-modif.html";
        }
    }
    
    @GetMapping("/list-vacunas/{id}")
    public String listarVacAp(@PathVariable String id, ModelMap model) throws ErrorServicio {

        model.put("mascota", mascotaServ.buscarMxId(id));
        
        return "list-vacunas.html";
    }
}
