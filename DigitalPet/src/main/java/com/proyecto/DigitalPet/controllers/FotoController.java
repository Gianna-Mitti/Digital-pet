package com.proyecto.DigitalPet.controllers;

import com.proyecto.DigitalPet.entidades.Usuario;
import com.proyecto.DigitalPet.errores.ErrorServicio;
import com.proyecto.DigitalPet.servicios.UsuarioServ;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/foto")
public class FotoController {

    @Autowired
private UsuarioServ us;

    @GetMapping("/usuario/{id}")
public ResponseEntity<byte[]> fotoUsuario(@PathVariable String id) throws Exception{
    try{
    Usuario u = us.getOne(id);
    if(u.getFoto() == null) {
        throw new Exception("El usuario no tiene una imagen asignada.");
    }
    
    byte[] foto = u.getFoto().getContent();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.IMAGE_JPEG);
    
    return new ResponseEntity<> (foto, headers, HttpStatus.OK);
} catch(ErrorServicio es) {
    Logger.getLogger(FotoController.class.getName()).log(Level.SEVERE, null, es);
    return new ResponseEntity<> (HttpStatus.NOT_FOUND);
}
}
}