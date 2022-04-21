package com.proyecto.DigitalPet.entidades;

import com.proyecto.DigitalPet.enums.Especie;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Mascota {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
private String id;
private String nombre;
private Date fechaNac;
private String sexo;
    @Enumerated(EnumType.STRING)
private Especie especie;
//private Raza raza;
private ArrayList<Vacuna> vacAplicadas;
private ArrayList<Vacuna> vacPendientes;
private Usuario usuario;

    public Mascota() {
    }

    public Mascota(String id, String nombre, Date fechaNac, String sexo, Especie especie, ArrayList<Vacuna> vacAplicadas, ArrayList<Vacuna> vacPendientes, Usuario usuario) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.sexo = sexo;
        this.especie = especie;
        this.vacAplicadas = vacAplicadas;
        this.vacPendientes = vacPendientes;
        this.usuario = usuario;
    }
}
