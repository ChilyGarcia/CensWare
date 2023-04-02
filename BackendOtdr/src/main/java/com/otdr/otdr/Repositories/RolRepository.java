package com.otdr.otdr.Repositories;

import com.otdr.otdr.Data.Entidades.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Perfil, Long> {
    public Perfil findByRolNombre(String rolNombre);
}
