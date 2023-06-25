package com.otdr.otdr.Repositories;

import com.otdr.otdr.Data.Entidades.Fallo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FalloRepository extends JpaRepository<Fallo, Long> {


    @Query(
            nativeQuery = true,
            value = "SELECT * FROM fallo f WHERE f.fallo_nombre= :nombre"
    )
    public Fallo findByFalloNombre(String nombre);
}
