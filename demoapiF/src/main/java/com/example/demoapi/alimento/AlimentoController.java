package com.example.demoapi.alimento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alimentos")
public class AlimentoController {

   
    
    @Autowired
    private AlimentoService alimentoService;

    @PostMapping("/addSeveral")
    public ResponseEntity<String> addSeveralAlimentos(@RequestBody List<Alimento> alimentos) {
        alimentoService.addSeveralAlimentos(alimentos);
        return new ResponseEntity<>("Alimentos agregados correctamente.", HttpStatus.CREATED);
    }

    @PostMapping("/addOne")
    public ResponseEntity<Alimento> addOneAlimento(@RequestBody Alimento alimento) {
        Alimento savedAlimento = alimentoService.addAlimento(alimento);
        return new ResponseEntity<>(savedAlimento, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Alimento>> getAllAlimentos() {
        List<Alimento> alimentos = alimentoService.getAllAlimentos();
        return new ResponseEntity<>(alimentos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alimento> getAlimentoById(@PathVariable Long id) {
        Optional<Alimento> alimento = alimentoService.getAlimentoById(id);
        return alimento.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/uploadImage")
    public ResponseEntity<String> uploadImage(@PathVariable Long id, @RequestParam("image") MultipartFile file) {
        try {
            alimentoService.addImageToAlimento(id, file.getBytes());
            return new ResponseEntity<>("Imagen subida correctamente.", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Error al subir la imagen.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/getImage")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Optional<byte[]> imageData = alimentoService.getImageFromAlimento(id);

        if (imageData.isPresent()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imageData.get(), headers, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
    