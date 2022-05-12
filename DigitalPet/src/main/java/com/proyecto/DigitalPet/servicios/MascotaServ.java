package com.proyecto.DigitalPet.servicios;

import com.proyecto.DigitalPet.entidades.Mascota;
import com.proyecto.DigitalPet.entidades.Usuario;
import com.proyecto.DigitalPet.entidades.Vacuna;
import com.proyecto.DigitalPet.enums.Especie;
import com.proyecto.DigitalPet.errores.ErrorServicio;
import com.proyecto.DigitalPet.repositorios.MascotaRepo;
import com.proyecto.DigitalPet.repositorios.UsuarioRepo;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MascotaServ {

    @Autowired
    private MascotaRepo mascotaRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private VacunaServ vacunaServ;

    public void validator(String nombre, LocalDate fechaNac, String sexo) throws ErrorServicio {

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

    @Transactional
    public Mascota crear(String idUsuario, String nombre, LocalDate fechaNac, String sexo, String especie) throws ErrorServicio {

        Optional<Usuario> rta = usuarioRepo.findById(idUsuario);

        if (rta.isPresent()) {
            Usuario usuario = rta.get();

            validator(nombre, fechaNac, sexo);

            Mascota mascota = new Mascota();

            mascota.setNombre(nombre);
            mascota.setFechaNac(fechaNac);
            mascota.setSexo(sexo);
            mascota.setEspecie(Especie.valueOf(especie.toUpperCase()));
            mascota.setUsuario(usuario);
            mascota.setAlta(Boolean.TRUE);
            
            // Reformular en dos métodos, guarda dos veces la misma mascota

            switch (especie.toUpperCase()) {
                case "CANINO":
                    mascota.setVacPendientes(vacunaServ.vacCanino(fechaNac));
                    break;
                case "FELINO":
                    mascota.setVacPendientes(vacunaServ.vacFelino(fechaNac));
                    break;
            }

            return mascotaRepo.save(mascota);
        } else {
            throw new ErrorServicio("El usuario ingresado no se econtró en nuestra lista de usuarios registrados.");
        }
    }

    //El método "cargarVacunas", también sirve para MODIFICAR las vacunas.
    @Transactional
    public Mascota cargarVacunas(String idUsuario, String idMascota, List<String> fechasApS, List<Vacuna> vacAplicadas) throws ErrorServicio {
        Optional<Mascota> rta = mascotaRepo.findById(idMascota);

        if (rta.isPresent()) {
            Mascota mascota = rta.get();

            if (mascota.getUsuario().getId().equals(idUsuario)) {

                Vacuna auxPend;

                LocalDate fechaAp;
                Period edadAp;
                Integer edadApS;
                int i;
                
                Iterator<Vacuna> itPends = mascota.getVacPendientes().iterator();

                while(itPends.hasNext()) {
                    auxPend = itPends.next();
                    
                    i = 0;
                    for (Vacuna vacuna : vacAplicadas) {

                        fechaAp = LocalDate.parse(fechasApS.get(i));
                        i++;

                        if(auxPend.getId().equals(vacuna.getId())){

                            Vacuna vacAp = new Vacuna();
                            
                            vacAp.setTipoVac(vacuna.getTipoVac());
                            vacAp.setFechaAplicacion(fechaAp);
                            vacAp.setRefuerzo(vacuna.getRefuerzo());
                            vacAp.setReAplicacion(vacuna.getReAplicacion());

                            edadAp = Period.between(mascota.getFechaNac(), fechaAp);
                            edadApS = edadAp.getYears();
                            vacAp.setEdadAplicacion(edadApS.toString());


                            mascota.getVacAplicadas().add(vacAp);
                            
                            if (!auxPend.getRefuerzo()) {
                                itPends.remove();
                            } else if(auxPend.getReAplicacion() < 360){
                                auxPend.setFechaAplicacion(fechaAp.plusDays(auxPend.getReAplicacion()));
                                auxPend.setRefuerzo(Boolean.FALSE);
                            } else {
                                auxPend.setFechaAplicacion(fechaAp.plusDays(auxPend.getReAplicacion()));
                            }

                        }
                    }
                }

                return mascotaRepo.save(mascota);
            } else {
                throw new ErrorServicio("Usted no puede acceder a los datos de esta mascota.");
            }
        } else {
            throw new ErrorServicio("No se han podido cargar las vacunas de la mascota correctamente.");
        }
    }

    @Transactional
    public Mascota editar(String idUsuario, String idMascota, String nombre, LocalDate fechaNac, String sexo, String especie) throws ErrorServicio {
        Optional<Mascota> rta = mascotaRepo.findById(idMascota);

        if (rta.isPresent()) {
            validator(nombre, fechaNac, sexo);
            Mascota mascota = rta.get();

            if(mascota.getUsuario().getId().equals(idUsuario)) {

            mascota.setNombre(nombre);
            mascota.setFechaNac(fechaNac);
            mascota.setSexo(sexo);
            mascota.setEspecie(Especie.valueOf(especie.toUpperCase()));

            return mascotaRepo.save(mascota);
            } else {
                throw new ErrorServicio("Usted no puede acceder a los datos de esta mascota.");
            }
        } else {
            throw new ErrorServicio("No se encontró la mascota que está tratando de modificar.");
        }
    }

    @Transactional
    public Mascota eliminar(String id, String idUsuario) throws ErrorServicio {
        Optional<Mascota> rta = mascotaRepo.findById(id);

        if (rta.isPresent()) {
            Mascota mascota = rta.get();

            if (mascota.getUsuario().getId().equals(idUsuario)){

            mascota.setAlta(Boolean.FALSE);
            return mascotaRepo.save(mascota);
            } else {
                throw new ErrorServicio("Usted no tiene permisos suficientes.");
            }
        } else {
            throw new ErrorServicio("No se encontró la mascota que está intentando dar de baja.");
        }
    }

    @Transactional
    public Mascota habilitar(String id, String idUsuario) throws ErrorServicio {
        Optional<Mascota> rta = mascotaRepo.findById(id);

        if (rta.isPresent()) {
            Mascota mascota = rta.get();

            if (mascota.getUsuario().getId().equals(idUsuario)){

                mascota.setAlta(Boolean.TRUE);
            return mascotaRepo.save(mascota);
            } else {
                throw new ErrorServicio("Usted no tiene permisos suficientes.");
            }
        } else {
            throw new ErrorServicio("No se encontró la mascota que está intentando dar de alta.");
        }
    }
    
    
    @Transactional(readOnly = true)
    public Mascota buscarMxId(String id) throws ErrorServicio {
        Optional<Mascota> rta = mascotaRepo.findById(id);

        System.out.println(id);
        if (rta.isPresent()) {
            Mascota mascota = rta.get();
            return mascota;
        } else {
            throw new ErrorServicio("No se encontró la mascota.");
        }
    }

    
    @Transactional(readOnly = true)
    public List<Mascota> listarMascotas(String idU) throws ErrorServicio {
        if(idU != null) {
        List<Mascota> mascotas = mascotaRepo.findPetsByUser(idU);
        return mascotas;    
        } else {
            throw new ErrorServicio("No se encontraron mascotas asociadas a este usuario.");
        }
    }
    
        @Transactional(readOnly = true)
    public List<Vacuna> listarVacAp(String id) throws ErrorServicio {
        if(id != null) {
        List<Vacuna> vacs = mascotaRepo.listVacAp(id);
        return vacs;    
        } else {
            throw new ErrorServicio("No se encontró esta mascota.");
        }
    }
    
            @Transactional(readOnly = true)
    public List<Vacuna> listarVacPend(String id) throws ErrorServicio {
        if(id != null) {
        List<Vacuna> vacs = mascotaRepo.listVacPend(id);
        return vacs;    
        } else {
            throw new ErrorServicio("No se encontró esta mascota.");
        }
    }
}
