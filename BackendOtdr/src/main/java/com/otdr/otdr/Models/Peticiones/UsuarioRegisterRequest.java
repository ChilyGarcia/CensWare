package com.otdr.otdr.Models.Peticiones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UsuarioRegisterRequest {

    private String nombre;
    private String apellido;
    private String email;
    private String celular;
    private String cedula;
    private String password;
    private String perfil;
    private String userLogeado;


}
