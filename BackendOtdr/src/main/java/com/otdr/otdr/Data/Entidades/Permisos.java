package com.otdr.otdr.Data.Entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "permiso")
public class Permisos implements Serializable {

    @Id
    private Long id;
    private String nombre;
    private boolean maps;
    private boolean caracterizacion;
    private boolean fallo;
    private boolean dashboard;

    public Permisos(String nombre, boolean maps, boolean caracterizacion, boolean fallo, boolean dashboard) {
        this.nombre = nombre;
        this.maps = maps;
        this.caracterizacion = caracterizacion;
        this.fallo = fallo;
        this.dashboard = dashboard;
    }
}
