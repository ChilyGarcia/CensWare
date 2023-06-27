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
    private Integer nombrePunto;
    private String cantRemanente;
    private double longitud;
    private double latitud;
    private String userLogeado;
    private boolean medicion;
    private String kmAnterior;
    private boolean pArchivo;
}
