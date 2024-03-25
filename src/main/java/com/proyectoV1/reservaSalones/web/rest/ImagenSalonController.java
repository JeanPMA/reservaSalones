package com.proyectoV1.reservaSalones.web.rest;

import com.proyectoV1.reservaSalones.domain.entities.ImagenSalon;
import com.proyectoV1.reservaSalones.domain.entities.Salon;
import com.proyectoV1.reservaSalones.dto.ImagenSalonDTO;
import com.proyectoV1.reservaSalones.dto.UsuarioDTO;
import com.proyectoV1.reservaSalones.error.Mensaje;
import com.proyectoV1.reservaSalones.repositories.ImagenSalonRepository;
import com.proyectoV1.reservaSalones.services.ImagenSalonService;
import com.proyectoV1.reservaSalones.services.SalonService;
import com.proyectoV1.reservaSalones.services.implement.CloudinaryServiceImpl;
import com.proyectoV1.reservaSalones.services.mapper.SalonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/v1/imagen-salon")
@CrossOrigin
public class ImagenSalonController {
    @Autowired
    CloudinaryServiceImpl cloudinaryService;
    @Autowired
    SalonMapper salonMapper;
    @Autowired
    private ImagenSalonRepository imagenSalonRepository;
    private final ImagenSalonService imagenSalonService;
    private final SalonService salonService;

    public ImagenSalonController(ImagenSalonService imagenSalonService, SalonService salonService) {
        this.imagenSalonService = imagenSalonService;
        this.salonService = salonService;
    }
    @GetMapping("/{salonId}/imagenes")
    @PreAuthorize("hasAnyRole('OWNER','ADMIN','USER')")
    public ResponseEntity<List<ImagenSalonDTO>> listarImagenes(@PathVariable Integer salonId) {
        return ResponseEntity.ok().body(imagenSalonService.listarImagenes(salonId));
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('OWNER','ADMIN')")
    public ResponseEntity<ImagenSalonDTO> getImagenSalonById(@PathVariable final Long id) {
        return ResponseEntity
                .ok()
                .body(imagenSalonService.getImagenById(id).orElseThrow(() -> new IllegalArgumentException("Recurso no encontrado: " + id)));
    }
    @PostMapping
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<ImagenSalonDTO> crear(@RequestParam MultipartFile multipartFile, @RequestParam Integer idSalon) throws IOException, URISyntaxException {
        BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
        if (bufferedImage == null){
            return new ResponseEntity(new Mensaje("Imagen no valida"), HttpStatus.BAD_REQUEST);
        }

        long count = imagenSalonRepository.countBySalonId(idSalon);
        if (count >= 6) {
            return new ResponseEntity(new Mensaje("Ya hay 6 imágenes ingresadas"), HttpStatus.UNAUTHORIZED);
        }

        Map result = cloudinaryService.upload(multipartFile);
        ImagenSalonDTO imagenSalonDTO = new ImagenSalonDTO();
        imagenSalonDTO.setNombre((String) result.get("original_filename"));
        imagenSalonDTO.setImagen_id((String) result.get("public_id"));
        imagenSalonDTO.setImagen_url((String) result.get("url"));
        Salon salon = salonService.getSalonById(idSalon)
                .map(salonMapper::toEntity)
                .orElseThrow(() -> new RuntimeException("Salon no encontrado con el ID: " + idSalon));
        imagenSalonDTO.setSalon(salon);



        imagenSalonService.save(imagenSalonDTO);
        return ResponseEntity.created(new URI("/v1/imagen-salon/" + imagenSalonDTO.getId())).body(imagenSalonDTO);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<Map> eliminar(@PathVariable("id") Long id) throws IOException{
        ImagenSalonDTO imagenSalonDTO = imagenSalonService.getImagenById(id).get();
        Map result = cloudinaryService.delete(imagenSalonDTO.getImagen_id());
        imagenSalonService.delete(id);
        return new ResponseEntity(new Mensaje("imagen eliminada"), HttpStatus.OK);
    }

}
