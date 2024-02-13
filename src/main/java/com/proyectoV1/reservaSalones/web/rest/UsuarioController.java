package com.proyectoV1.reservaSalones.web.rest;

import com.proyectoV1.reservaSalones.domain.entities.Usuario;
import com.proyectoV1.reservaSalones.dto.UsuarioDTO;
import com.proyectoV1.reservaSalones.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/v1/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final BCryptPasswordEncoder passwordEncoder;


    public UsuarioController(UsuarioService usuarioService, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioDTO>> listarUsuario() {
        return ResponseEntity.ok().body(usuarioService.listarUsuario());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('OWNER','ADMIN')")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable final Long id) {
        return ResponseEntity
                .ok()
                .body(usuarioService.getUsuarioById(id).orElseThrow(() -> new IllegalArgumentException("Recurso no encontrado: " + id)));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioDTO> create(@RequestBody final UsuarioDTO dto) throws URISyntaxException {
        if (dto.getId() != null) {
            throw new IllegalArgumentException("El usuario no puede tener ya un id ingresado.");
        }
        String passwordEncriptado = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(passwordEncriptado);

        UsuarioDTO usuarioDTO = usuarioService.save(dto);

        return ResponseEntity.created(new URI("/v1/usuario/" + usuarioDTO.getId())).body(usuarioDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('OWNER','ADMIN')")
    public ResponseEntity<UsuarioDTO> editUsuario(@RequestBody final UsuarioDTO dto,
                                                    @PathVariable final Long id) throws URISyntaxException {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("Invalid usuario id, valor nulo");
        }
        if (!Objects.equals(dto.getId(), id)) {
            throw new IllegalArgumentException("Invalid id");
        }
        Optional<UsuarioDTO> optionalUsuario = usuarioService.getUsuarioById(id);

        if (optionalUsuario.isPresent()) {
            UsuarioDTO existingUsuario = optionalUsuario.get();

            if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
                String passwordEncriptado = passwordEncoder.encode(dto.getPassword());
                dto.setPassword(passwordEncriptado);
            } else {
                dto.setPassword(existingUsuario.getPassword());
            }

            return ResponseEntity.ok().body(this.usuarioService.save(dto));
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        usuarioService.delete(id);

        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioDTO> actualizarParcial(@RequestBody UsuarioDTO dto, @PathVariable final Long id) {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("Invalid usuario id, valor nulo");
        }
        if (!Objects.equals(dto.getId(), id)) {
            throw new IllegalArgumentException("Invalid id");
        }
        usuarioService.parcial(dto, id);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
