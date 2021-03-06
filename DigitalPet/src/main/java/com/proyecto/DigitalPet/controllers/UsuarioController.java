package com.proyecto.DigitalPet.controllers;

import com.proyecto.DigitalPet.entidades.Usuario;
import com.proyecto.DigitalPet.servicios.UsuarioServ;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {
@Autowired
private UsuarioServ usuarioServ;

    @GetMapping("/perfil/{id}")
    public String lista(@PathVariable String id, ModelMap modelo) {

        Usuario usuario = usuarioServ.getOne(id);

        modelo.addAttribute("usuario", usuario);

        return "perfil.html";
    }

    @Transactional
    @GetMapping("/registro")
    public String registrar(){
        
        return "form-usuario.html";
    }
    
    @PostMapping("/registro")      
    public String registrar (ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail,  @RequestParam(required = false) Long tel, @RequestParam String clave){
      try{
          usuarioServ.registrar(nombre, apellido, mail, tel, clave);
          modelo.put("exito", "Registro exitoso");
          return "index.html";

        }catch (Exception e){
            modelo.put("error", e.getMessage());
            System.out.println(e.getMessage());
                    
            return "form-usuario.html";
        }
    } 
    
    @GetMapping("/modificar/{id}")
    public String modificar (HttpSession sesion, @PathVariable String id, ModelMap modelo){
        
        try{
            Usuario u = (Usuario) sesion.getAttribute("usuariosesion");
            modelo.put("usuario", u);
        } catch(Exception e) {  
        }
            
        return "form-usuario-modif.html";
    }
 
    @PostMapping("/modificar/{id}")
    public String modificar (ModelMap modelo, @PathVariable String id, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail,  @RequestParam(required = false) Long tel, @RequestParam String clave, HttpSession sesion){
        
        if(usuarioServ.getOne(id) != null) {
         try{
            Usuario u = usuarioServ.modificar(id, nombre, apellido, mail, tel, clave);
            sesion.setAttribute("usuariosesion", u);
            modelo.put("exito", "Modific?? sus datos satisfactoriamente.");
            return "redirect:/usuario/perfil/{id}";
            
        }catch (Exception e){
            modelo.put("error", e.getMessage());
            modelo.put("usuario", usuarioServ.getOne(id));
            return "form-usuario-modif.html";
        }   
        }
        return null;
    }
    
    @GetMapping("/modificarclave/{id}")
    public String modificarclave (@PathVariable String id, ModelMap modelo){
        modelo.put("usuario", usuarioServ.getOne(id));
        
        return "form-usuario-clave.html";
    }
 
    @PostMapping("/modificarclave/{id}")
    public String modificarclave (ModelMap modelo, @PathVariable String id, @RequestParam String claveNueva, @RequestParam String claveAnterior ){
        try{
            usuarioServ.modificarClave(id, claveNueva, claveAnterior);
            modelo.put("exito", "Modific?? su clave satisfactoriamente.");
            return "redirect:/usuario/perfil/{id}";
            
        }catch (Exception e){
            modelo.put("error", e.getMessage());
            return "form-usuario-clave.html";
        }
    }
    }
    
