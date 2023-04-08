package com.otdr.otdr.Models.Peticiones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class RutaCrearRequest {

    private String rutaInicio;
    private String rutaFin;
    private String userLogeado; //Email del usuario logeado;

}
