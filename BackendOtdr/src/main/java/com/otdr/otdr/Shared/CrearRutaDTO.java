package com.otdr.otdr.Shared;

import lombok.*;

import java.io.Serializable;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class CrearRutaDTO implements Serializable {

    private static final long serialVersionUID=1L;

    private String rutaInicio;
    private String rutaFin;
    private String rutaNombre;
    private String userLogeado;

}
