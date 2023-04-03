package com.otdr.otdr.Models.Peticiones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PerfilCrearRequest {

    private String nombrePerfil;
    private boolean maps;
    private boolean caracterizacion;
    private boolean fallo;
    private boolean dashboard;
    private String userLogeado;

}
