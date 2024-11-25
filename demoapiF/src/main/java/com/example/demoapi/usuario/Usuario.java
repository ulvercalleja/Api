package com.example.demoapi.usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode( onlyExplicitlyIncluded = true)

@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @EqualsAndHashCode.Include
    @ToString.Exclude
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  
    
    @Column(unique = true)
    private String nombreUsuario;

    @Column(unique = true)
    private String email;
    
    @Column(unique = false)
    private String password;

    @Lob
    @Column(name = "fotoPerfil", columnDefinition = "LONGBLOB")
    private byte[] fotoPerfil; // Campo para almacenar la imagen
}

