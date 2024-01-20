package com.proyectoV1.reservaSalones.web.rest;

import com.proyectoV1.reservaSalones.domain.entities.ImagenSalon;
import com.proyectoV1.reservaSalones.domain.entities.Salon;
import com.proyectoV1.reservaSalones.dto.ImagenSalonDTO;
import com.proyectoV1.reservaSalones.dto.UsuarioDTO;
import com.proyectoV1.reservaSalones.error.Mensaje;
import com.proyectoV1.reservaSalones.services.ImagenSalonService;
import com.proyectoV1.reservaSalones.services.implement.CloudinaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final ImagenSalonService imagenSalonService;

    public ImagenSalonController(ImagenSalonService imagenSalonService) {
        this.imagenSalonService = imagenSalonService;
    }
    @GetMapping
    public ResponseEntity<List<ImagenSalonDTO>> listarImagenes() {
        return ResponseEntity.ok().body(imagenSalonService.listarImagenes());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ImagenSalonDTO> getImagenSalonById(@PathVariable final Long id) {
        return ResponseEntity
                .ok()
                .body(imagenSalonService.getImagenById(id).orElseThrow(() -> new IllegalArgumentException("Recurso no encontrado: " + id)));
    }
    @PostMapping
    public ResponseEntity<ImagenSalonDTO> crear(@RequestParam MultipartFile multipartFile, @RequestParam Long idSalon) throws IOException, URISyntaxException {
        BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
        if (bufferedImage == null){
            return new ResponseEntity(new Mensaje("Imagen no valida"), HttpStatus.BAD_REQUEST);
        }


        Map result = cloudinaryService.upload(multipartFile);
        ImagenSalonDTO imagenSalonDTO = new ImagenSalonDTO();
        imagenSalonDTO.setNombre((String) result.get("original_filename"));
        imagenSalonDTO.setImagen_id((String) result.get("public_id"));
        imagenSalonDTO.setImagen_url((String) result.get("url"));
        //imagenSalonDTO.setSalon(salonService.findById(idSalon).orElse(null));

        imagenSalonService.save(imagenSalonDTO);
        return ResponseEntity.created(new URI("/v1/imagen-salon/" + imagenSalonDTO.getId())).body(imagenSalonDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map> eliminar(@PathVariable("id") Long id) throws IOException{
        ImagenSalonDTO imagenSalonDTO = imagenSalonService.getImagenById(id).get();
        Map result = cloudinaryService.delete(imagenSalonDTO.getImagen_id());
        imagenSalonService.delete(id);
        return new ResponseEntity(new Mensaje("imagen eliminada"), HttpStatus.OK);
    }

}