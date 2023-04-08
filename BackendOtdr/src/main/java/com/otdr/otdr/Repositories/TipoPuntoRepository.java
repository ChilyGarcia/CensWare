package com.otdr.otdr.Repositories;

import com.otdr.otdr.Data.Entidades.TipoPunto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPuntoRepository extends JpaRepository<TipoPunto, Long> {
    public TipoPunto findByTipoNombre(String nombre);
}
