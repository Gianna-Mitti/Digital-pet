/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.DigitalPet.servicios;
//import com.proyecto.DigitalPet.entidades.Vacuna;
import com.proyecto.DigitalPet.repositorios.MascotaRepo;
//import java.time.LocalDate;
//import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class NotificacionServ {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    MascotaRepo mascotaRepo;

    @Async
    public void notificacionRegistro(String cuerpo, String titulo, String mail) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(mail);
        mensaje.setFrom("digitalpet05@gmail.com");
        mensaje.setSubject(titulo);
        mensaje.setText(cuerpo);
        mailSender.send(mensaje);
    }

//    @Async
//    public void notificacionVacPend(String id, String mail) {
//        if (id != null) {
//            List<Vacuna> vacs = mascotaRepo.listVacPend(id);
//            LocalDate fechaActual= LocalDate.now();
//            for (Vacuna vac : vacs) {
//                if (vac.getFechaAplicacion().minusDays(15).equals(fechaActual)){
//                    SimpleMailMessage mensaje = new SimpleMailMessage();
//                    mensaje.setTo(mail);
//                    mensaje.setFrom("digitalpet05@gmail.com");
//                    mensaje.setSubject("Recordatorio vacunacion DigitalPet");
//                    mensaje.setText("Su mascota " + mascotaRepo.getById(id).getNombre() + " necesita recibir su proxima vacuna (" + vac.getTipoVac() + ") dentro de 15 días. Recuerda registrar cuando se la haya aplicado. Saludos DigitalPet");
//                    mailSender.send(mensaje);
//                }
//            }
//        }
//    }
//
}
