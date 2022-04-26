package com.proyecto.DigitalPet.entidades;

import com.proyecto.DigitalPet.enums.Especie;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@OneToMany
private ArrayList<Vacuna> vacAplicadas;
@OneToMany
private ArrayList<Vacuna> vacPendientes;

    public Mascota() {
    }

    public Mascota(String id, String nombre, Date fechaNac, String sexo, Especie especie, ArrayList<Vacuna> vacAplicadas, ArrayList<Vacuna> vacPendientes) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.sexo = sexo;
        this.especie = especie;
        this.vacAplicadas = vacAplicadas;
        this.vacPendientes = vacPendientes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public ArrayList<Vacuna> getVacAplicadas() {
        return vacAplicadas;
    }

    public void setVacAplicadas(ArrayList<Vacuna> vacAplicadas) {
        this.vacAplicadas = vacAplicadas;
    }

    public ArrayList<Vacuna> getVacPendientes() {
        return vacPendientes;
    }

    public void setVacPendientes(ArrayList<Vacuna> vacPendientes) {
        this.vacPendientes = vacPendientes;
    }   
}
