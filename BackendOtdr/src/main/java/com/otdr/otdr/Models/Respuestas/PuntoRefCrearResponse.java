package com.otdr.otdr.Models.Respuestas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PuntoRefCrearResponse {

    private String ruta;
    private String tipoPunto;
    private String nombrePunto;
    private String kmAnterior;
    private String cantRemanente;
    private double longitud;
    private double latitud;
}
