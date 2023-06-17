package com.otdr.otdr.Repositories;

import com.otdr.otdr.Data.Entidades.Fallo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FalloRepository extends JpaRepository<Fallo, Long> {

    public Fallo findByFalloNombre(String nombre);
}
