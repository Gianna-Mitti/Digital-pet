package com.proyecto.DigitalPet.servicios;

import com.proyecto.DigitalPet.entidades.Usuario;
import com.proyecto.DigitalPet.errores.ErrorServicio;
import com.proyecto.DigitalPet.repositorios.UsuarioRepo;
import java.util.List;
import java.util.Optional;
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
    public Usuario registrar(String nombre, String apellido, String mail, Long tel, String clave) throws ErrorServicio {
        validator(nombre, apellido, mail, clave);
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setTel(tel);
        usuario.setMail(mail);
        String encriptada = new BCryptPasswordEncoder().encode(clave);
        usuario.setClave(encriptada);
        return usuarioRepo.save(usuario);
    }

    @Transactional
    public Usuario modificar(String id, String nombre, String apellido, String mail, Long tel, String clave) throws ErrorServicio {
        Optional<Usuario> op = usuarioRepo.findById(id);

        if (op.isPresent()) {
            Usuario usuario = op.get();
            if (clave.equals(usuario.getClave())) {
                validator(nombre, apellido, mail, clave);

                usuario.setNombre(nombre);
                usuario.setApellido(apellido);
                usuario.setTel(tel);
                usuario.setMail(mail);
                String encriptada = new BCryptPasswordEncoder().encode(clave);
                usuario.setClave(encriptada);

                return usuarioRepo.save(usuario);
            }else{
                throw new ErrorServicio("La clave es incorrecta");
            }
        } else {
            throw new ErrorServicio("No se ha modificado correctamente");
        }
    }
    @Transactional
    public Usuario modificarClave (String id, String claveNueva, String claveAnterior) throws ErrorServicio {
        Optional<Usuario> op = usuarioRepo.findById(id);
         if (op.isPresent()) {
            Usuario usuario = op.get();
             validarClave(claveAnterior);
            if (claveAnterior.equals(usuario.getClave())) {
                validarClave(claveNueva);
                String encriptada = new BCryptPasswordEncoder().encode(claveNueva);
                usuario.setClave(encriptada);
                return usuarioRepo.save(usuario);
            }else{
                throw new ErrorServicio("La clave es incorrecta");
            }
         }else{
             throw new ErrorServicio("No se ha modificado correctamente");
         }
    }
    @Transactional
    public void eliminar(String id) {
        usuarioRepo.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return usuarioRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario getOne(String id) {
        return usuarioRepo.getById(id);

    }

    public void validarClave(String clave) throws ErrorServicio {
if (clave == null || clave.trim().isEmpty() || clave.length() <= 6) {
            throw new ErrorServicio("La clave del usuario no puede estar vacia, y debe tener mas de 6 digitos.");
        }
}

    public void validator(String nombre, String apellido, String mail, String clave) throws ErrorServicio {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ErrorServicio("El nombre del usuario no puede estar vacio.");
        }

        if (apellido == null || apellido.trim().isEmpty()) {
            throw new ErrorServicio("El apellido del usuario no puede estar vacio.");
        }

        if (mail == null || mail.trim().isEmpty()) {
            throw new ErrorServicio("El mail del usuario no puede estar vacio.");
        }

        if (usuarioRepo.buscarxMail(mail) != null) {
            throw new ErrorServicio("El mail ya esta registrado en la base de datos.");
        }

        if (clave == null || clave.trim().isEmpty() || clave.length() <= 6) {
            throw new ErrorServicio("La clave del usuario no puede estar vacia, y debe tener mas de 6 digitos.");
        }
    }

}