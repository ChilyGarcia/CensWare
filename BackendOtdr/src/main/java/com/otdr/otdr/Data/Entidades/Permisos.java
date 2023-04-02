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

}
