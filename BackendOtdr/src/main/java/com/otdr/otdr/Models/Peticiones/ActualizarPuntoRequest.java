package com.otdr.otdr.Models.Peticiones;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class ActualizarPuntoRequest {

    private int nombreP;
    private String cantRem;
    private String ruta;
}
