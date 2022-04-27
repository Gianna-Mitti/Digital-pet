package com.proyecto.DigitalPet.servicios;

import com.proyecto.DigitalPet.entidades.Vacuna;
import com.proyecto.DigitalPet.repositorios.VacunaRepo;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VacunaServ {

@Autowired
    private VacunaRepo vacunaRepo;

    public ArrayList<Vacuna> vacCanino() {
        Vacuna vac = new Vacuna();
        ArrayList<Vacuna> vacunas = new ArrayList();
        
            vac.setTipoVac("Parvovirus");
            vac.setEdadAplicacion("6-8sem");
            vac.setRefuerzo(Boolean.FALSE);
            vacunas.add(vac);
            
            vac.setTipoVac("Polivalente");
            vac.setEdadAplicacion("8-10sem");
            vac.setRefuerzo(Boolean.FALSE);
            vacunas.add(vac);
            
            vac.setTipoVac("Polivalente");
            vac.setEdadAplicacion("12-14sem");
            vac.setRefuerzo(Boolean.FALSE);
            vacunas.add(vac);
            
            vac.setTipoVac("Traqueobronquitis");
            vac.setEdadAplicacion("16-18sem");
            vac.setRefuerzo(Boolean.FALSE);
            vacunas.add(vac);
            
            vac.setTipoVac("Antirrábica");
            vac.setEdadAplicacion("6-8sem");
            vac.setRefuerzo(Boolean.TRUE);
            vacunas.add(vac);
            
        return vacunas;
    }

    public ArrayList<Vacuna> vacFelino() {
        Vacuna vac = new Vacuna();
        ArrayList<Vacuna> vacunas = new ArrayList();
        
            vac.setTipoVac("Clamidiosis");
            vac.setEdadAplicacion("9sem");
            vac.setRefuerzo(Boolean.FALSE);
            vacunas.add(vac);
            
            vac.setTipoVac("Triple");
            vac.setEdadAplicacion("8-9sem");
            vac.setRefuerzo(Boolean.TRUE);
            vacunas.add(vac);
            
            vac.setTipoVac("Leucemia");
            vac.setEdadAplicacion("9sem");
            vac.setRefuerzo(Boolean.FALSE);
            vacunas.add(vac);
            
            vac.setTipoVac("Coronavirus");
            vac.setEdadAplicacion("16sem");
            vac.setRefuerzo(Boolean.FALSE);
            vacunas.add(vac);
            
            vac.setTipoVac("Antigripal");
            vac.setEdadAplicacion("2m");
            vac.setRefuerzo(Boolean.FALSE);
            vacunas.add(vac);
            
            vac.setTipoVac("Moquillo");
            vac.setEdadAplicacion("2m");
            vac.setRefuerzo(Boolean.FALSE);
            vacunas.add(vac);
            
            vac.setTipoVac("Antirrábica");
            vac.setEdadAplicacion("4-6m");
            vac.setRefuerzo(Boolean.FALSE);
            vacunas.add(vac);
            
        return vacunas;
    }
    
}
