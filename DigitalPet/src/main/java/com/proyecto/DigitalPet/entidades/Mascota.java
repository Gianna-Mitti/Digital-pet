package com.proyecto.DigitalPet.entidades;

import com.proyecto.DigitalPet.enums.Especie;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Mascota {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    private LocalDate fechaNac;
    private String sexo;
    @Enumerated(EnumType.STRING)
    private Especie especie;
//private Raza raza;
    private Boolean alta;
    
    @OneToMany(cascade={CascadeType.ALL}, orphanRemoval=true)
    private List<Vacuna> vacAplicadas;
    
    @OneToMany(cascade={CascadeType.ALL}, orphanRemoval=true)
    private List<Vacuna> vacPendientes;
    
    @ManyToOne
    private Usuario usuario;

    public Mascota() {
    }

    public Mascota(String id, String nombre, LocalDate fechaNac, String sexo, Especie especie, Boolean alta, ArrayList<Vacuna> vacAplicadas, ArrayList<Vacuna> vacPendientes, Usuario usuario) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.sexo = sexo;
        this.especie = especie;
        this.alta = alta;
        this.vacAplicadas = vacAplicadas;
        this.vacPendientes = vacPendientes;
        this.usuario = usuario;
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

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
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

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public List<Vacuna> getVacAplicadas() {
        return vacAplicadas;
    }

    public void setVacAplicadas(ArrayList<Vacuna> vacAplicadas) {
        this.vacAplicadas = vacAplicadas;
    }

    public List<Vacuna> getVacPendientes() {
        return vacPendientes;
    }

    public void setVacPendientes(ArrayList<Vacuna> vacPendientes) {
        this.vacPendientes = vacPendientes;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }



}
