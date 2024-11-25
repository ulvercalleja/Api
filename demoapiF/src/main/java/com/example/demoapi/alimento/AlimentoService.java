package com.example.demoapi.alimento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AlimentoService {

    @Autowired
    private AlimentoRepository alimentoRepository;

    @Transactional
    public void addSeveralAlimentos(List<Alimento> alimentos) {
        alimentoRepository.saveAll(alimentos);
    }

    @Transactional
    public Alimento addAlimento(Alimento alimento) {
        return alimentoRepository.save(alimento);
    }

    @Transactional(readOnly = true)
    public List<Alimento> getAllAlimentos() {
        return alimentoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Alimento> getAlimentoById(Long id) {
        return alimentoRepository.findById(id);
    }
    
    @Transactional
    public void addImageToAlimento(Long id, byte[] imageData) {
        Optional<Alimento> alimentoOptional = alimentoRepository.findById(id);
        if (alimentoOptional.isPresent()) {
            Alimento alimento = alimentoOptional.get();
            alimento.setImagen(imageData);
            alimentoRepository.save(alimento);
        }
    }

    @Transactional(readOnly = true)
    public Optional<byte[]> getImageFromAlimento(Long id) {
        return alimentoRepository.findById(id).map(Alimento::getImagen);
    }
}
