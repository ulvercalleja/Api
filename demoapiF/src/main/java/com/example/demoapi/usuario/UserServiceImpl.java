package com.example.demoapi.usuario;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
@Service
@Validated
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto findById(Long id) {

        Usuario userEntity = userRepo.getReferenceById(id);
        UserDto userDto = modelMapper.map(userEntity, UserDto.class);
        return userDto;
    }

    @Override
    public UserDto update(Long id, UserDto userDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {

        userRepo.deleteById(id);

    }

    @Override
    public List<UserDto> getAll() {
        List<Usuario> listaUsuarios = userRepo.findAll();
        List<UserDto> listaUserDtos = listaUsuarios.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        return listaUserDtos;
    }
    
    @Override
    public RespuestaLogin login(String email, String password) {
        Usuario usuario = userRepo.login(email, password);
        if (usuario != null) {
            return new RespuestaLogin(true, usuario.getId());
        } else {
            return new RespuestaLogin(false, null);
        }
    }

    @Override
    public UserDto save(@Valid UserDto userDto) {
       // Convertir el DTO a la entidad Usuario
        Usuario usuario = modelMapper.map(userDto, Usuario.class);

        // Guardar la entidad en la base de datos
        Usuario usuarioGuardado = userRepo.save(usuario);

        // Convertir la entidad guardada nuevamente a DTO
        UserDto userDtoGuardado = modelMapper.map(usuarioGuardado, UserDto.class);

        return userDtoGuardado;
    }

    @Override
    public void addImageToUsuario(Long id, byte[] imageData) {
        Usuario usuario = userRepo.findById(id)
                                  .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));
        usuario.setFotoPerfil(imageData);
        userRepo.save(usuario);
    }

    @Override
    public byte[] getImageFromUsuario(Long id) {
        Usuario usuario = userRepo.findById(id).orElse(null);
        return (usuario != null) ? usuario.getFotoPerfil() : null;
    }
}
