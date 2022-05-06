package com.proyecto.DigitalPet.servicios;

import com.proyecto.DigitalPet.entidades.Mascota;
import com.proyecto.DigitalPet.entidades.Vacuna;
import com.proyecto.DigitalPet.repositorios.MascotaRepo;
import com.proyecto.DigitalPet.repositorios.VacunaRepo;
import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VacunaServ {

@Autowired
    private VacunaRepo vacunaRepo;
@Autowired
    private MascotaRepo mascotaRepo;

    public ArrayList<Vacuna> vacCanino(Mascota m) {
        Vacuna vac = new Vacuna();
        ArrayList<Vacuna> vacunas = new ArrayList<Vacuna>();
        
            vac.setTipoVac("Parvovirus");
            vac.setEdadAplicacion("6-8sem");
            vac.setFechaAplicacion(m.getFechaNac().plusWeeks(7));
            vac.setRefuerzo(Boolean.FALSE);
            vacunas.add(vac);
            
            vac.setTipoVac("Polivalente");
            vac.setEdadAplicacion("8-10sem");
            vac.setFechaAplicacion(m.getFechaNac().plusWeeks(9));
            vac.setRefuerzo(Boolean.FALSE);
            vacunas.add(vac);
            
            vac.setTipoVac("Polivalente");
            vac.setEdadAplicacion("12-14sem");
            vac.setFechaAplicacion(m.getFechaNac().plusWeeks(13));
            vac.setRefuerzo(Boolean.FALSE);
            vacunas.add(vac);
            
            vac.setTipoVac("Traqueobronquitis");
            vac.setEdadAplicacion("16-18sem");
            vac.setFechaAplicacion(m.getFechaNac().plusWeeks(17));
            vac.setRefuerzo(Boolean.FALSE);
            vacunas.add(vac);
            
            vac.setTipoVac("Antirrábica");
            vac.setEdadAplicacion("6-8sem");
            vac.setFechaAplicacion(m.getFechaNac().plusWeeks(7));
            vac.setRefuerzo(Boolean.TRUE);
            vac.setReAplicacion(vac.getFechaAplicacion().plusYears(1));
            vacunas.add(vac);
            
        return vacunas;
    }

    public ArrayList<Vacuna> vacFelino(Mascota m) {
        Vacuna vac = new Vacuna();
        ArrayList<Vacuna> vacunas = new ArrayList<Vacuna>();
        
            vac.setTipoVac("Clamidiosis");
            vac.setEdadAplicacion("9sem");
            vac.setFechaAplicacion(m.getFechaNac().plusWeeks(9));
            vac.setRefuerzo(Boolean.FALSE);
            vacunas.add(vac);
            
            vac.setTipoVac("Triple");
            vac.setEdadAplicacion("8-9sem");
            vac.setFechaAplicacion(m.getFechaNac().plusWeeks(8));
            vac.setRefuerzo(Boolean.TRUE);
            vac.setReAplicacion(vac.getFechaAplicacion().plusDays(15));
            vacunas.add(vac);
            
            vac.setTipoVac("Leucemia");
            vac.setEdadAplicacion("9sem");
            vac.setFechaAplicacion(m.getFechaNac().plusWeeks(9));
            vac.setRefuerzo(Boolean.TRUE);
            vac.setReAplicacion(vac.getFechaAplicacion().plusYears(1));
            vacunas.add(vac);
            
            vac.setTipoVac("Coronavirus");
            vac.setEdadAplicacion("16sem");
            vac.setFechaAplicacion(m.getFechaNac().plusWeeks(16));
            vac.setRefuerzo(Boolean.FALSE);
            vacunas.add(vac);
            
            vac.setTipoVac("Antigripal");
            vac.setEdadAplicacion("2m");
            vac.setFechaAplicacion(m.getFechaNac().plusMonths(2));
            vac.setRefuerzo(Boolean.FALSE);
            vacunas.add(vac);
            
            vac.setTipoVac("Moquillo");
            vac.setEdadAplicacion("2m");
            vac.setFechaAplicacion(m.getFechaNac().plusMonths(2));
            vac.setRefuerzo(Boolean.FALSE);
            vacunas.add(vac);
            
            vac.setTipoVac("Antirrábica");
            vac.setEdadAplicacion("4-6m");
            vac.setFechaAplicacion(m.getFechaNac().plusMonths(5));
            vac.setRefuerzo(Boolean.FALSE);
            vacunas.add(vac);
            
        return vacunas;
    }
    
}
