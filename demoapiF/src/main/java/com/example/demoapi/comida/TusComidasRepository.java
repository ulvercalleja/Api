package com.example.demoapi.comida;

import java.util.List;

// ComidaRepository.java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TusComidasRepository extends JpaRepository<TusComidas, Integer> {
    List<TusComidas> findAllByUsuario_Id(Long usuario_id);
}
