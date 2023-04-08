package com.otdr.otdr.Models.Respuestas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PuntoRefCrearResponse {

    private String ruta;
    private String tipoPunto;
    private String puntoNombre;
    private String kmAnterior;
    private String kmSiguiente;
    private String cantRemanente;
    private String longitud;
    private String latitud;
}
