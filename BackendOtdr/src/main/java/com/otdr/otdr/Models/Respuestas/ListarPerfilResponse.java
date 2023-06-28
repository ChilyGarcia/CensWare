package com.otdr.otdr.Models.Respuestas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ListarPerfilResponse {

    private String perfilNombre;
    private boolean maps;
    private boolean caracterizacion;
    private boolean fallo;
    private boolean dashboard;
    private boolean estado;
}
