package com.proyecto.DigitalPet.servicios;

import com.proyecto.DigitalPet.entidades.Vacuna;
import com.proyecto.DigitalPet.errores.ErrorServicio;
import com.proyecto.DigitalPet.repositorios.MascotaRepo;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MascotaServ {

@Autowired
    private MascotaRepo mascotaRepo;

    public void validator(String nombre, Date fechaNac, String sexo, ArrayList<Vacuna> vacAplicadas) throws ErrorServicio{
     
        if(nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede estar vacío.");
        }
        if(fechaNac == null) {
            throw new ErrorServicio("La fecha de nacimiento no puede estar vacía.");
        }
        if(sexo == null || sexo.isEmpty()) {
            throw new ErrorServicio("El sexo de la mascota no puede estar vacío.");
        }
        if(vacAplicadas == null || vacAplicadas.isEmpty()) {
            throw new ErrorServicio("El campo vacunas aplicadas no puede estar vacío.");
        }
    }
}
