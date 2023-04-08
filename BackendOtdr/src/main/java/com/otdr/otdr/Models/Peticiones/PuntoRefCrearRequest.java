package com.otdr.otdr.Models.Peticiones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PuntoRefCrearRequest {

    private String ruta; // ruta en la que va a guardar el punto
    private String tipoPunto; // ejemplo: si es poste o estacion
    private String puntoNombre; // nombre que le va a dar al punto
    private String kmAnterior; // distancia en kilometros del punto anterior
    private String kmSiguiente; //distancia en kilometros del punto siguiente
    private String cantRemanente; // cantidad de remanente del punto a regisrar
    private String longitud;
    private String latitud;
    private String userLogeado;

}
