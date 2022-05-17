package com.proyecto.DigitalPet.repositorios;

import com.proyecto.DigitalPet.entidades.Foto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepo extends JpaRepository<Foto, String> {
    
//@Query("SELECT f FROM Foto WHERE f.id = :id")
//public Foto buscarxId(@Param("id") String id);
}
