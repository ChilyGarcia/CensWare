package com.otdr.otdr.Repositories;

import com.otdr.otdr.Data.Entidades.Permisos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisoRepository extends JpaRepository<Permisos, Long> {
    public Permisos findByNombre(String nombre);
}
