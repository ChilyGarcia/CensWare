package com.otdr.otdr.Shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class SolucionFalloDTO implements Serializable {

    private static final long serialVersionUID=1L;

    private String ruta;
    private int nombreP;
    private String mensaje;
    private String descripcion;
    private String remutilizado;
    private String email;
    private String tFallo;

}
