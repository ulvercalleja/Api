package com.example.demoapi.usuario;

import java.util.List;

import jakarta.validation.Valid;

public interface UserService {

    public UserDto save(@Valid UserDto userDto);     
    public UserDto findById(Long id);
    public UserDto update(Long id, UserDto userDto);
    public void delete(Long id);
    public List<UserDto> getAll();
    public RespuestaLogin login(String email, String password);
    public void addImageToUsuario(Long id, byte[] imageData);
    public byte[] getImageFromUsuario(Long id);
}
