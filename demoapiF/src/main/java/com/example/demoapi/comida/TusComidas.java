package com.example.demoapi.comida;

import com.example.demoapi.tipoComida.TipoComida;
import com.example.demoapi.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tuscomidas")
public class TusComidas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(unique = false)
    private String nombre;
    private int valorCalorico;
    private int hidratos;
    private int proteinas;
    private int grasas;
    
    @Lob
    @Column(name = "imagen", columnDefinition = "LONGBLOB")
    private byte[] imagen; // Campo para almacenar la imagen
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "tipo_comida_id")
    @JsonIgnore
    private TipoComida tipoComida;

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public TipoComida getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(TipoComida tipoComida) {
        this.tipoComida = tipoComida;
    }
    /* Las tablas cumplen la primera y segunda forma normal, sin embargo por comodidad y legibilidad la tabla "TusComidas" no está en 3FN.
        Para que cumpliera la 3FN la calumna "imagen" "nombre" "valor_calorico" se sustituirian por una única columna llamada "ID" para poder
        relacionar las comidas con el usuario respetando la regla de la transitividad. */
}
