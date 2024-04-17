package com.proyectoV1.reservaSalones.services;

import com.proyectoV1.reservaSalones.dto.CorreoRequestDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final String destinatario = "jean.pool.mauricioproyecto@gmail.com";

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void enviarCorreo(CorreoRequestDTO correoRequestDTO) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        String asunto = "Aplicacion gestion de salones";

        String cuerpo = "Nombre: " + correoRequestDTO.getNombre() + "<br>"
                + "Apellido: " + correoRequestDTO.getApellido() + "<br>"
                + "Correo: " + correoRequestDTO.getCorreo() + "<br>"
                + "Teléfono: " + correoRequestDTO.getTelefono() + "<br>"
                + "Mensaje: " + correoRequestDTO.getMensaje();

        helper.setTo(destinatario);
        helper.setSubject(asunto);
        helper.setText(cuerpo, true);

        javaMailSender.send(mimeMessage);
    }
}
