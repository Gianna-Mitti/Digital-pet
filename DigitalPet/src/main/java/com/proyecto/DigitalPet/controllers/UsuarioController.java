package com.proyecto.DigitalPet.controllers;

import com.proyecto.DigitalPet.entidades.Usuario;
import com.proyecto.DigitalPet.servicios.UsuarioServ;
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

    @GetMapping("/list-usuario/{id}")
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
          System.out.println(clave);
          usuarioServ.registrar(nombre, apellido, mail, tel, clave);
          modelo.put("exito", "Registro exitoso");
          return "perfil.html";

        }catch (Exception e){
            modelo.put("error", "No se ha registrado correctamente");
            System.out.println(e.getMessage());
                    
            return "form-usuario.html";
        }
    } 
    
    @GetMapping("/modificar/{id}")
    public String modificar (@PathVariable String id, ModelMap modelo){
        modelo.put("usuario", usuarioServ.getOne(id));
            
        return "form-usuario-modif.html";
    }
 
    @PostMapping("/modificar/{id}")
    public String modificar (ModelMap modelo, @PathVariable String id, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail,  @RequestParam Long tel, @RequestParam String clave ){
        try{
            usuarioServ.modificar(id, nombre, apellido, mail, tel, clave);
            modelo.put("exito", "Modificacion exitosa");
            return "perfil.html";
            
        }catch (Exception e){
            modelo.put("error", "No se ha modificado correctamente");
            return "form-usuario-modif.html";
            
        }
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
            modelo.put("exito", "Modificacion exitosa");
            return "perfil.html";
            
        }catch (Exception e){
            modelo.put("error", "No se ha modificado correctamente");
            return "form-usuario-modific.html";
            
        }
    }
    }
    
