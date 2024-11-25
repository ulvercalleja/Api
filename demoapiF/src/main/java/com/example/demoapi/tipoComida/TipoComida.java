package com.example.demoapi.tipoComida;

import java.util.List;
import com.example.demoapi.comida.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TipoComida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

   
    @OneToMany(mappedBy = "tipoComida", cascade = CascadeType.ALL)    
    private List<TusComidas> listaComidas;    
}
