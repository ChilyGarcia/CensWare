package com.otdr.otdr.Repositories;

import com.otdr.otdr.Data.Entidades.PuntoReferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PuntoRefRepository extends JpaRepository<PuntoReferencia, Long> {

    public PuntoReferencia findByPuntoNombre(String nombre);
}
