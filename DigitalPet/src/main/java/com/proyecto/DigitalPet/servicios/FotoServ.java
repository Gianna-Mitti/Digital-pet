package com.proyecto.DigitalPet.servicios;

import com.proyecto.DigitalPet.entidades.Foto;
import com.proyecto.DigitalPet.repositorios.FotoRepo;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FotoServ {

@Autowired
private FotoRepo fotoRepo;

public Foto save(MultipartFile archivo) throws Exception{
    try{
        if (archivo != null) {
            Foto foto = new Foto();
            foto.setMime(archivo.getContentType());
            foto.setNombre(archivo.getName());
            foto.setContent(archivo.getBytes());
            return fotoRepo.save(foto);
        }
    } catch(Exception e) {
        System.out.println(e.getMessage());
    }
    return null;
}

public Foto actualizar(String idFoto, MultipartFile archivo) {
    if(archivo != null) {
        try{
            Foto foto = new Foto();
            
            if(idFoto != null) {
                Optional<Foto> rta = fotoRepo.findById(idFoto);
                if(rta.isPresent()) {
                    foto = rta.get();
                }
            }
            foto.setMime(archivo.getContentType());
            foto.setNombre(archivo.getName());
            foto.setContent(archivo.getBytes());
            return fotoRepo.save(foto);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    return null;
}
}
