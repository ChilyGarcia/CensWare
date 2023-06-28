package com.otdr.otdr.Data.Entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "perfiles")
public class Perfil implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rolNombre;
    private boolean estado;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "permiso_id")
    private Permisos permisos;

}

