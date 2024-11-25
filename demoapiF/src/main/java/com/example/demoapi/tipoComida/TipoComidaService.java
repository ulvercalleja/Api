package com.example.demoapi.tipoComida;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class TipoComidaService {
    @Autowired
    private TipoComidaRepository tipoComidaRepository;

    public TipoComida saveTipoComida(TipoComida tipoComida) {
        return tipoComidaRepository.save(tipoComida);
    }

    public Optional<TipoComida> getTipoComidaById(int id) {
        return tipoComidaRepository.findById(id);
    }
    public TipoComida updateTipoComida(TipoComida tipoComida) {
        return tipoComidaRepository.save(tipoComida);
    }
}
