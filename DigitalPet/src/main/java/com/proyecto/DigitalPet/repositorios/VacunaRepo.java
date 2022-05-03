/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.DigitalPet.repositorios;

import com.proyecto.DigitalPet.entidades.Vacuna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VacunaRepo extends JpaRepository<Vacuna, String> {
    
@Query("SELECT v FROM Vacuna v WHERE v.id = :id")
    public Vacuna buscarxId(@Param("id")String id);
}