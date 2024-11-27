package com.example.demoapi.comida;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demoapi.usuario.UserRepo;
import com.example.demoapi.usuario.Usuario;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class TusComidasService {

    @Autowired
    private TusComidasRepository comidaRepository;
    @Autowired
    private UserRepo usuarioRepository;

    public TusComidas addComida(TusComidas comida, Long usuario_id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuario_id);

        if (!usuarioOptional.isPresent()) {
            throw new IllegalArgumentException("Usuario con ID " + usuario_id + " no encontrado");
        }

        // Asociar el usuario a la comida
        Usuario usuario = usuarioOptional.get();
        comida.setUsuario(usuario);

        // Guardar la comida en el repositorio
        return comidaRepository.save(comida);
    }

    @Transactional(readOnly = true)
    public List<TusComidas> getAllMisComidas(Long usuario_id) {
        return comidaRepository.findAllByUsuario_Id(usuario_id);
    }
    
}
