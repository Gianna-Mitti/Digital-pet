/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.DigitalPet.repositorios;

import com.proyecto.DigitalPet.entidades.Mascota;
import com.proyecto.DigitalPet.entidades.Vacuna;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotaRepo extends JpaRepository<Mascota, String> {

    @Query("SELECT m FROM Mascota m WHERE m.id = :id")
    public List<Mascota> buscarxId(@Param("id") String id);

    @Query("SELECT m FROM Mascota m WHERE m.usuario.id = :id")
    public List<Mascota> findPetsByUser(@Param("id") String id);

    @Query("SELECT m.vacAplicadas FROM Mascota m WHERE m.id = :id")
    public List<Vacuna> listVacAp(@Param("id") String id);

    @Query("SELECT m.vacPendientes FROM Mascota m WHERE m.id = :id")
    public List<Vacuna> listVacPend(@Param("id") String id);
}
