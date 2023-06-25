package com.otdr.otdr.Shared;

import lombok.*;

import java.io.Serializable;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class SolucionFalloDTO implements Serializable {

    private static final long serialVersionUID=1L;

    private String ruta;
    private int nombreP;
    private String mensaje;
    private String descripcion;
    private String remutilizado;
    private String email;
    private String fallo;

}
