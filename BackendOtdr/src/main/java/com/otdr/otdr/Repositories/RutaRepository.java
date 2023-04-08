package com.otdr.otdr.Repositories;

import com.otdr.otdr.Data.Entidades.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Long> {

    public Ruta findByRutaNombre(String nombre);
}
