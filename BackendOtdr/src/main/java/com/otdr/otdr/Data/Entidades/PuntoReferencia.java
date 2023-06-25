package com.otdr.otdr.Data.Entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
@Table(name = "punto_ref")
public class PuntoReferencia implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer nombrePunto;
    private double longitud;
    private double latitud;
    private String kmAnterior;
    private String cantRemanente;
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "tipo_id")
    private TipoPunto tipoPunto;
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "ruta_id")
    private Ruta ruta;
    private boolean estado = true;
    private boolean medicion;
}
