package com.proyecto.DigitalPet.servicios;

import com.proyecto.DigitalPet.entidades.Foto;
import com.proyecto.DigitalPet.entidades.Usuario;
import com.proyecto.DigitalPet.enums.Role;
import com.proyecto.DigitalPet.errores.ErrorServicio;
import com.proyecto.DigitalPet.repositorios.UsuarioRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Usuario
 */
@Service
public class UsuarioServ implements UserDetailsService {

    @Autowired
    private UsuarioRepo usuarioRepo;
    
    @Autowired
    private NotificacionServ notificacionServ;
    
    @Autowired
    private FotoServ fotoServ;

    @Transactional
    public Usuario registrar(String nombre, String apellido, String mail, Long tel, String clave, MultipartFile archivo) throws ErrorServicio, Exception {
        validator(nombre, apellido, mail, clave);
        Usuario usuario = new Usuario();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setTel(tel);
        usuario.setMail(mail);
        usuario.setClave(encoder.encode(clave));
        usuario.setRole(Role.USER);
        
        Foto foto = fotoServ.save(archivo);
        usuario.setFoto(foto);
        
        notificacionServ.notificacionRegistro("¡Gracias por unirte a nuestra comunidad! En nuestra página vas a poder llevar el control de las vacunas aplicadas y pendientes de tu mascota.", " ¡Bienvenidx a DigitalPet!", usuario.getMail());
        return usuarioRepo.save(usuario);
    }

    @Transactional
    public Usuario modificar(String id, String nombre, String apellido, String mail, Long tel, String clave, MultipartFile archivo) throws ErrorServicio {
        Optional<Usuario> op = usuarioRepo.findById(id);
        Usuario usuario = op.get();
        
        validatorMod(nombre, apellido, mail, clave);

            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setTel(tel);
            usuario.setMail(mail);
            usuario.setRole(Role.USER);
            
            String idFoto = null;
            if(usuario.getFoto() != null) {
                idFoto = usuario.getFoto().getId();
            }
            Foto foto = fotoServ.actualizar(idFoto, archivo);
            usuario.setFoto(foto);
            
            return usuarioRepo.save(usuario);
    }

    @Transactional
    public Usuario modificarClave(String id, String claveNueva, String claveAnterior) throws ErrorServicio {
        Optional<Usuario> op = usuarioRepo.findById(id);
        if (op.isPresent()) {
            Usuario usuario = op.get();
            validarClave(claveAnterior);
            if (claveAnterior != claveNueva) {
                validarClave(claveNueva);
                String encriptada = new BCryptPasswordEncoder().encode(claveNueva);
                usuario.setClave(encriptada);
                return usuarioRepo.save(usuario);
            } else {
                throw new ErrorServicio("La clave es incorrecta.");
            }
        } else {
            throw new ErrorServicio("La clave no se ha modificado correctamente.");
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
        if (clave == null || clave.trim().isEmpty() || clave.length() < 6) {
            throw new ErrorServicio("La clave del usuario no puede estar vacía, y debe contener más de 6 caracteres.");
        }
    }

    public void validator(String nombre, String apellido, String mail, String clave) throws ErrorServicio {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ErrorServicio("El nombre del usuario no puede estar vacío.");
        }

        if (apellido == null || apellido.trim().isEmpty()) {
            throw new ErrorServicio("El apellido del usuario no puede estar vacío.");
        }

        if (mail == null || mail.trim().isEmpty()) {
            throw new ErrorServicio("El mail del usuario no puede estar vacío.");
        }

        if (usuarioRepo.buscarxMail(mail) != null) {
            throw new ErrorServicio("Ya existe un usuario registrado con ese e-mail.");
        }

        if (clave == null || clave.trim().isEmpty() || clave.length() < 6) {
            throw new ErrorServicio("La clave del usuario no puede estar vacía, y debe contener más de 6 caracteres.");
        }
    }

        public void validatorMod(String nombre, String apellido, String mail, String clave) throws ErrorServicio {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ErrorServicio("El nombre del usuario no puede estar vacío.");
        }

        if (apellido == null || apellido.trim().isEmpty()) {
            throw new ErrorServicio("El apellido del usuario no puede estar vacío.");
        }

        if (mail == null || mail.trim().isEmpty()) {
            throw new ErrorServicio("El mail del usuario no puede estar vacío.");
        }

        if (clave == null || clave.trim().isEmpty() || clave.length() < 6) {
            throw new ErrorServicio("La clave del usuario no puede estar vacía, y debe contener más de 6 caracteres.");
        }
    }
    
    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Usuario entidad = (Usuario) usuarioRepo.buscarxMail(mail);

        if (entidad != null) {
            List<GrantedAuthority> permisos = new ArrayList<>();

            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_" + entidad.getRole());
            permisos.add(p1);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosesion", entidad);

            User user = new User(entidad.getMail(), entidad.getClave(), permisos);
            return user;
        } else {
            return null;
        }
    }

}
