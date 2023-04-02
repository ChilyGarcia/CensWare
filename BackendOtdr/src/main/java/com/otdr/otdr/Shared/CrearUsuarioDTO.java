package com.otdr.otdr.Shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CrearUsuarioDTO implements Serializable {

    private static final long serialVersionUID=1L;


    private String nombre;
    private String apellido;
    private String email;
    private String celular;
    private String cedula;
    private String password;
    private String perfil;
    private String userLogeado;

}
