package com.proyecto.DigitalPet.servicios;

import com.proyecto.DigitalPet.entidades.Vacuna;
import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class VacunaServ {

    public ArrayList<Vacuna> vacCanino(LocalDate fechaNac) {

        ArrayList<Vacuna> vacunas = new ArrayList<Vacuna>();
        Vacuna vac = new Vacuna();
        vac.setTipoVac("Parvovirus");
        vac.setEdadAplicacion("6-8sem");
        vac.setFechaAplicacion(fechaNac.plusWeeks(7));
        vac.setRefuerzo(Boolean.FALSE);
        vacunas.add(vac);

        Vacuna vac1 = new Vacuna();
        vac1.setTipoVac("Polivalente");
        vac1.setEdadAplicacion("8-10sem");
        vac1.setFechaAplicacion(fechaNac.plusWeeks(9));
        vac1.setRefuerzo(Boolean.TRUE);
        vac1.setReAplicacion(30);
        vacunas.add(vac1);

        // Vacuna vac3 = new Vacuna();
        // vac3.setTipoVac("Polivalente 2º dósis");
        // vac3.setEdadAplicacion("12-14sem");
        // vac3.setFechaAplicacion(vac2.getFechaAplicacion().plusMonths(1));
        // vac3.setRefuerzo(Boolean.FALSE);
        // vacunas.add(vac3);
        Vacuna vac2 = new Vacuna();
        vac2.setTipoVac("Traqueobronquitis");
        vac2.setEdadAplicacion("16-18sem");
        vac2.setFechaAplicacion(fechaNac.plusWeeks(17));
        vac2.setRefuerzo(Boolean.FALSE);
        vacunas.add(vac2);

        Vacuna vac3 = new Vacuna();
        vac3.setTipoVac("Antirrábica");
        vac3.setEdadAplicacion("A partir de 6-8sem");
        vac3.setFechaAplicacion(fechaNac.plusWeeks(7));
        vac3.setRefuerzo(Boolean.TRUE);
        vac3.setReAplicacion(360);
        vacunas.add(vac3);

        return vacunas;
    }

    public ArrayList<Vacuna> vacFelino(LocalDate fechaNac) {

        ArrayList<Vacuna> vacunas = new ArrayList<Vacuna>();

        Vacuna vac = new Vacuna();
        vac.setTipoVac("Triple");
        vac.setEdadAplicacion("8-9sem");
        vac.setFechaAplicacion(fechaNac.plusWeeks(8));
        vac.setRefuerzo(Boolean.TRUE);
        vac.setReAplicacion(15);
        vacunas.add(vac);

        Vacuna vac1 = new Vacuna();
        vac1.setTipoVac("Clamidiosis");
        vac1.setEdadAplicacion("9sem");
        vac1.setFechaAplicacion(fechaNac.plusWeeks(9));
        vac1.setRefuerzo(Boolean.FALSE);
        vacunas.add(vac1);
        
        Vacuna vac2 = new Vacuna();
        vac2.setTipoVac("Leucemia");
        vac2.setEdadAplicacion("A partir de 9sem");
        vac2.setFechaAplicacion(fechaNac.plusWeeks(9));
        vac2.setRefuerzo(Boolean.TRUE);
        vac2.setReAplicacion(360);
        vacunas.add(vac2);

        // Vacuna vac2 = new Vacuna();
        // vac2.setTipoVac("Triple 2º dósis");
        // vac2.setEdadAplicacion("10-11sem");
        // vac2.setFechaAplicacion(vac1.getFechaAplicacion().plusDays(15));
        // vac2.setRefuerzo(Boolean.FALSE);
        // vacunas.add(vac2);
        Vacuna vac3 = new Vacuna();
        vac3.setTipoVac("Coronavirus");
        vac3.setEdadAplicacion("16sem");
        vac3.setFechaAplicacion(fechaNac.plusWeeks(16));
        vac3.setRefuerzo(Boolean.FALSE);
        vacunas.add(vac3);

        Vacuna vac4 = new Vacuna();
        vac4.setTipoVac("Antigripal");
        vac4.setEdadAplicacion("2m");
        vac4.setFechaAplicacion(fechaNac.plusMonths(2));
        vac4.setRefuerzo(Boolean.FALSE);
        vacunas.add(vac4);

        Vacuna vac5 = new Vacuna();
        vac5.setTipoVac("Moquillo");
        vac5.setEdadAplicacion("2m");
        vac5.setFechaAplicacion(fechaNac.plusMonths(2));
        vac5.setRefuerzo(Boolean.FALSE);
        vacunas.add(vac5);

        Vacuna vac6 = new Vacuna();
        vac6.setTipoVac("Antirrábica");
        vac6.setEdadAplicacion("4-6m");
        vac6.setFechaAplicacion(fechaNac.plusMonths(5));
        vac6.setRefuerzo(Boolean.TRUE);
        vac6.setReAplicacion(360);
        vacunas.add(vac6);

        return vacunas;
    }

}
