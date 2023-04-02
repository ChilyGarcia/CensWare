package com.otdr.otdr.Models.Respuestas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UsuarioRegisterResponse {

    private String nombre;
    private String apellido;
    private String cedula;
    private String email;
    private String perfil;

}
