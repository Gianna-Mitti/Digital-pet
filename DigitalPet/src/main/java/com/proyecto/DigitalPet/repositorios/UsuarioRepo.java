/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.DigitalPet.repositorios;

import com.proyecto.DigitalPet.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepo extends JpaRepository <Usuario, String> {
    
@Query("SELECT u FROM Usuario u WHERE u.id = :id")
    public Usuario buscarxId(@Param("id")String id);
    
@Query("SELECT u FROM Usuario u WHERE u.mail = :mail")
    public Usuario buscarxMail(@Param("mail")String mail);            
}
