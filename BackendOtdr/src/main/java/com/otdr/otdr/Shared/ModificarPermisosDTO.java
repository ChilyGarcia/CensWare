package com.otdr.otdr.Shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ModificarPermisosDTO implements Serializable {

    private static final long serialVersionUID=1L;

    private String perfil;
    private boolean maps;
    private boolean caracterizacion;
    private boolean fallo;
    private boolean dashboard;
    private String userLogeado;


}
