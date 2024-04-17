package com.proyectoV1.reservaSalones.web.rest;

import com.proyectoV1.reservaSalones.dto.CorreoRequestDTO;
import com.proyectoV1.reservaSalones.services.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/correo")
public class EmailController {
    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public ResponseEntity<String> enviarCorreo(@RequestBody CorreoRequestDTO correoRequestDTO) {
        try {
            emailService.enviarCorreo(correoRequestDTO);
            return ResponseEntity.ok("Correo enviado con éxito");
        } catch (MessagingException e) {
            return ResponseEntity.status(500).body("Error al enviar el correo");
        }
    }
}
