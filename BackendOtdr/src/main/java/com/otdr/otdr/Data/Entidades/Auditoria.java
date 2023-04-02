package com.otdr.otdr.Data.Entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "auditorias")
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    private String fecha;

    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(name = "soluc_fallo_id", nullable = false)
    private SolucionFallo solucionFallo;
}
