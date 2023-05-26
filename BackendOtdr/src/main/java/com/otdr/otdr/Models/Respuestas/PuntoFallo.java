package com.otdr.otdr.Models.Respuestas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PuntoFallo {

    private String nombre;
    private String longitud;
    private String latitud;
    private String mensaje;

}
