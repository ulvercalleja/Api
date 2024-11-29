package com.example.demoapi.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor

public class UserDto {
    @EqualsAndHashCode.Include
    private Long id ;
    
    @NotBlank
    @Size(max=25)
    private String nombreUsuario;

    @NotBlank
    @Size(max=25)
    private String email;
    @NotBlank
    @Size(max=25)
    private String password;
    
    private String foto_perfil;
    
    public UserDto (String nombreUsuario, String password){

        this.nombreUsuario = nombreUsuario;
        this.password = password;

    }
//     {
//         "nombreUsuario":"test",
//         "email":"test@email.com",
//         "password":"password",
//        "hijos": [
//           {"nombre":"hijo1",
//             "edad":"6"
//             }
//         ]
//       }
}
