package com.example.demoapi.comida;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class TusComidasService {
      @Autowired
    private TusComidasRepository comidaRepository;

    public TusComidas addComida(TusComidas comida) {
        return comidaRepository.save(comida);
    }

    @Transactional(readOnly = true)
    public List<TusComidas> getAllMisComidas(Long usuario_id) {
        return comidaRepository.findAllByUsuario_Id(usuario_id);
    }
    
}
