package com.proyecto.DigitalPet.entidades;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

    @Entity
public class Vacuna {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
private String id;
private String tipoVac;
private LocalDate fechaAplicacion;
private String edadAplicacion;
private LocalDate reAplicacion;
private Boolean refuerzo;

    public Vacuna() {
    }

    public Vacuna(String id, String tipoVac, LocalDate fechaAplicacion, String edadAplicacion, LocalDate reAplicacion, Boolean refuerzo) {
        this.id = id;
        this.tipoVac = tipoVac;
        this.fechaAplicacion = fechaAplicacion;
        this.edadAplicacion = edadAplicacion;
        this.reAplicacion = reAplicacion;
        this.refuerzo = refuerzo;
    }

    public String getId() {
        return id;
    }

    public LocalDate getReAplicacion() {
        return reAplicacion;
    }

    public void setReAplicacion(LocalDate reAplicacion) {
        this.reAplicacion = reAplicacion;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoVac() {
        return tipoVac;
    }

    public void setTipoVac(String tipoVac) {
        this.tipoVac = tipoVac;
    }

    public LocalDate getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(LocalDate fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    public String getEdadAplicacion() {
        return edadAplicacion;
    }

    public void setEdadAplicacion(String edadAplicacion) {
        this.edadAplicacion = edadAplicacion;
    }

    public Boolean getRefuerzo() {
        return refuerzo;
    }

    public void setRefuerzo(Boolean refuerzo) {
        this.refuerzo = refuerzo;
    }
    }