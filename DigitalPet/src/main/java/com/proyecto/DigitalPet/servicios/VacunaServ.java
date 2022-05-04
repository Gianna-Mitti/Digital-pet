package com.proyecto.DigitalPet.servicios;

import com.proyecto.DigitalPet.entidades.Mascota;
import com.proyecto.DigitalPet.entidades.Vacuna;
import com.proyecto.DigitalPet.enums.Especie;
import com.proyecto.DigitalPet.repositorios.MascotaRepo;
import com.proyecto.DigitalPet.repositorios.VacunaRepo;
import java.time.LocalDate;
//import com.proyecto.DigitalPet.repositorios.VacunaRepo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VacunaServ {

    @Autowired
    private VacunaRepo vacunaRepo;
    @Autowired
    private MascotaRepo mascotaRepo;

    public ArrayList<Vacuna> vacCanino() {
        Vacuna vac = new Vacuna();
        Mascota m = new Mascota();
        LocalDate fechaVacunacion = m.getFechaNac();
        ArrayList<Vacuna> vacunas = new ArrayList<Vacuna>();

        vac.setTipoVac("Parvovirus");
        vac.setEdadAplicacion("6-8sem");
        vac.setFechaAplicacion(fechaVacunacion.plusWeeks(7));
        vac.setRefuerzo(Boolean.FALSE);
        vacunas.add(vac);

        vac.setTipoVac("Polivalente");
        vac.setEdadAplicacion("8-10sem");
        vac.setFechaAplicacion(fechaVacunacion.plusWeeks(9));
        vac.setRefuerzo(Boolean.FALSE);
        vacunas.add(vac);

        vac.setTipoVac("Polivalente");
        vac.setEdadAplicacion("12-14sem");
        vac.setFechaAplicacion(fechaVacunacion.plusWeeks(13));
        vac.setRefuerzo(Boolean.FALSE);
        vacunas.add(vac);

        vac.setTipoVac("Traqueobronquitis");
        vac.setEdadAplicacion("16-18sem");
        vac.setFechaAplicacion(fechaVacunacion.plusWeeks(17));
        vac.setRefuerzo(Boolean.FALSE);
        vacunas.add(vac);

        vac.setTipoVac("Antirrábica");
        vac.setEdadAplicacion("6-8sem");
        vac.setFechaAplicacion(fechaVacunacion.plusWeeks(7));
        vac.setRefuerzo(Boolean.TRUE);
        vac.setReAplicacion(vac.getFechaAplicacion().plusYears(1));
        vacunas.add(vac);

        return vacunas;
    }

    public ArrayList<Vacuna> vacFelino() {
        Vacuna vac = new Vacuna();
        Mascota m = new Mascota();
        LocalDate fechaVacunacion = m.getFechaNac();
        ArrayList<Vacuna> vacunas = new ArrayList<Vacuna>();

        vac.setTipoVac("Clamidiosis");
        vac.setEdadAplicacion("9sem");
        vac.setFechaAplicacion(fechaVacunacion.plusWeeks(9));
        vac.setRefuerzo(Boolean.FALSE);
        vacunas.add(vac);

        vac.setTipoVac("Triple");
        vac.setEdadAplicacion("8-9sem");
        vac.setFechaAplicacion(fechaVacunacion.plusWeeks(8));
        vac.setRefuerzo(Boolean.TRUE);
        vac.setReAplicacion(vac.getFechaAplicacion().plusDays(15));
        vacunas.add(vac);

        vac.setTipoVac("Leucemia");
        vac.setEdadAplicacion("9sem");
        vac.setFechaAplicacion(fechaVacunacion.plusWeeks(9));
        vac.setRefuerzo(Boolean.TRUE);
        vac.setReAplicacion(vac.getFechaAplicacion().plusYears(1));
        vacunas.add(vac);

        vac.setTipoVac("Coronavirus");
        vac.setEdadAplicacion("16sem");
        vac.setFechaAplicacion(fechaVacunacion.plusWeeks(16));
        vac.setRefuerzo(Boolean.FALSE);
        vacunas.add(vac);

        vac.setTipoVac("Antigripal");
        vac.setEdadAplicacion("2m");
        vac.setFechaAplicacion(fechaVacunacion.plusMonths(2));
        vac.setRefuerzo(Boolean.FALSE);
        vacunas.add(vac);

        vac.setTipoVac("Moquillo");
        vac.setEdadAplicacion("2m");
        vac.setFechaAplicacion(fechaVacunacion.plusMonths(2));
        vac.setRefuerzo(Boolean.FALSE);
        vacunas.add(vac);

        vac.setTipoVac("Antirrábica");
        vac.setEdadAplicacion("4-6m");
        vac.setFechaAplicacion(fechaVacunacion.plusMonths(5));
        vac.setRefuerzo(Boolean.FALSE);
        vacunas.add(vac);

        return vacunas;
    }

    public List<Vacuna> findAll(Especie especie) throws Exception {
        List<Vacuna> vacs;
        switch (especie.toString()) {
            case "FELINO":
                return vacs = vacFelino();

            case "CANINO":
                return vacs = vacCanino();
                
            default: 
                throw new Exception("Error serv. vac.");
        }
    }

}

