package com.otdr.otdr.Models.Peticiones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PermisosRequest {

    private boolean maps;
    private boolean registrar;

}
