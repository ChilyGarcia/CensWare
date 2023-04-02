package com.otdr.otdr.Data.Entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "soluc_fallo")
public class SolucionFallo implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String fecha;
    private String descSolucion;
    private String puntoRef2;
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "usuario_id")
    private Usuario usuario;
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "punto_ref1")
    private PuntoReferencia puntoReferencia;
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "fallo_id")
    private Fallo fallo;

}
