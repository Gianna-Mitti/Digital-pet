/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.DigitalPet.servicios;

import com.proyecto.DigitalPet.entidades.Usuario;
import com.proyecto.DigitalPet.errores.ErrorServicio;
import com.proyecto.DigitalPet.repositorios.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Usuario
 */
@Service
public class UsuarioServ {
    
    @Autowired
    private UsuarioRepo usuarioRepo;
    
     @Transactional
    public Usuario registrar(String nombre, String apellido, String mail, Long tel, String clave, String clave2) throws ErrorServicio {
        validator(nombre, apellido, mail, tel, clave, clave2, null);
        Usuario usuario=new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setMail(mail);
        String encriptada = new BCryptPasswordEncoder().encode(clave);
        usuario.setContra(encriptada);
        return usuarioRepo.save(usuario);
    }
    
    public void validator (String nombre, String apellido, String mail, Long tel, String clave, String clave2, String validar) throws ErrorServicio {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ErrorServicio("El nombre del usuario no puede estar vacio.");
        }

        if (apellido == null || apellido.trim().isEmpty()) {
            throw new ErrorServicio("El apellido del usuario no puede estar vacio.");
        }

        if (mail == null || mail.trim().isEmpty()) {
            throw new ErrorServicio("El mail del usuario no puede estar vacio.");
        }

        if (validar == null) {
            if (usuarioRepo.buscarxMail(mail) != null) {
                throw new ErrorServicio("Ya existe un usuario creado con ese email.");
            }
        }

        if (clave== null || clave.trim().isEmpty() || clave.length() <= 6) {
            throw new ErrorServicio("La clave del usuario no puede estar vacia, y debe tener mas de 6 digitos.");
        }

        if (!clave.equals(clave2)) {
            throw new ErrorServicio("Las contraseñas deben ser iguales.");
        }

    }
    
}
