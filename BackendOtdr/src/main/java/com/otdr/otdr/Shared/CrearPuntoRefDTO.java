package com.otdr.otdr.Shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CrearPuntoRefDTO implements Serializable {

    private static final long serialVersionUID=1L;

    private String ruta;
    private String tipoPunto;
    private String puntoNombre;
    private String kmAnterior;
    private String kmSiguiente;
    private String cantRemanente;
    private String longitud;
    private String latitud;
    private String userLogeado;

}
