package com.example.demoapi.usuario;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("user")
public class UserController {
    
    @Autowired
    UserService userService;

    @PostMapping("/crear")
    public ResponseEntity<UserDto> crearUsuario(@RequestBody UserDto user) {
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @PostMapping("/actualizar")
    public ResponseEntity<UserDto> updateUsuario(@RequestBody UserDto user) {
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    
    @GetMapping("/obtenerUsuario/{id}")
    public ResponseEntity<UserDto> getUsuario(@PathVariable String id) {
        UserDto user = userService.findById(Long.parseLong(id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    @GetMapping("/login/{email}/{password}")
    public ResponseEntity<RespuestaLogin> login(@PathVariable String email, @PathVariable String password) {
        RespuestaLogin respuestaLogin = userService.login(email, password);
        if (respuestaLogin.isDatosCorrectos()) {
            return new ResponseEntity<>(respuestaLogin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(respuestaLogin, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/{id}/uploadImage")
    public ResponseEntity<String> uploadImage(@PathVariable Long id, @RequestParam("image") MultipartFile file) {
        try {
            userService.addImageToUsuario(id, file.getBytes());
            return new ResponseEntity<>("Imagen subida correctamente.", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Error al subir la imagen.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/getImage")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        byte[] imageData = userService.getImageFromUsuario(id);
        if (imageData != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
