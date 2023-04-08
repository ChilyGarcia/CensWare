package com.otdr.otdr.Shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CrearRutaDTO implements Serializable {

    private static final long serialVersionUID=1L;

    private String rutaInicio;
    private String rutaFin;
    private String rutaNombre;
    private String userLogeado;

}
