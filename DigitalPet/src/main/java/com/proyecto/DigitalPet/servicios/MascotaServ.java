package com.proyecto.DigitalPet.servicios;

import com.proyecto.DigitalPet.entidades.Mascota;
import com.proyecto.DigitalPet.entidades.Usuario;
import com.proyecto.DigitalPet.entidades.Vacuna;
import com.proyecto.DigitalPet.enums.Especie;
import com.proyecto.DigitalPet.errores.ErrorServicio;
import com.proyecto.DigitalPet.repositorios.MascotaRepo;
import com.proyecto.DigitalPet.repositorios.UsuarioRepo;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MascotaServ {

    @Autowired
    private MascotaRepo mascotaRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private VacunaServ vacunaServ;

    public void validator(String nombre, Date fechaNac, String sexo) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede estar vacío.");
        }
        if (fechaNac == null) {
            throw new ErrorServicio("La fecha de nacimiento no puede estar vacía.");
        }
        if (sexo == null || sexo.isEmpty()) {
            throw new ErrorServicio("El sexo de la mascota no puede estar vacío.");
        }
    }

    public void validator2(ArrayList<Vacuna> vacAplicadas) throws ErrorServicio {
        if (vacAplicadas == null || vacAplicadas.isEmpty()) {
            throw new ErrorServicio("El campo vacunas aplicadas no puede estar vacío.");
        }
    }

    public Mascota crear(String idUsuario, String nombre, Date fechaNac, String sexo, Especie especie) throws ErrorServicio {

        Optional<Usuario> rta = usuarioRepo.findById(idUsuario);

        if (rta.isPresent()) {
            Usuario usuario = rta.get();

            validator(nombre, fechaNac, sexo);

            Mascota mascota = new Mascota();

            mascota.setNombre(nombre);
            mascota.setFechaNac(fechaNac);
            mascota.setSexo(sexo);
            mascota.setEspecie(especie);

            switch (mascota.getEspecie().toString()) {
                case "CANINO":
                    mascota.setVacPendientes(vacunaServ.vacCanino());
                    break;
                case "FELINO":
                    mascota.setVacPendientes(vacunaServ.vacFelino());
                    break;
            }

            return mascotaRepo.save(mascota);
        } else {
            throw new ErrorServicio("El usuario ingresado no se econtró en nuestra lista de usuarios registrados.");
        }
    }

    public Mascota cargarVacunas(String idUsuario, String idMascota, ArrayList<Vacuna> vacAplicadas) throws ErrorServicio {
        Optional<Usuario> rta = usuarioRepo.findById(idUsuario);      

        Optional<Mascota> rta2 = mascotaRepo.findPetByUser(idMascota, idUsuario);

        if (rta2.isPresent() && rta.isPresent()) {
            Mascota mascota = rta2.get();

            int i = 0;
            Vacuna vacAplicada;
            Vacuna aux;

            Iterator<Vacuna> it = mascota.getVacPendientes().iterator();
            while (it.hasNext()) {
                vacAplicada = vacAplicadas.get(i);
                aux = it.next();
                if (aux.getTipoVac().equals(vacAplicada.getTipoVac()) && aux.getRefuerzo().toString().equals("FALSE")) {
                    it.remove();
                }
                i++;
            }

            return mascotaRepo.save(mascota);
        } else {
            throw new ErrorServicio("No se pudo registrar la mascota correctamente.");
        }
    }

}
