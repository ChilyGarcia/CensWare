package com.otdr.otdr.Models.Respuestas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class AuditoriaGestionResponse {

    private String titulo;
    private String desc;
    private String fecha;
    private String nombreUser;
    private String cedula;
}
