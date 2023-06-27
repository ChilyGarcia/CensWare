package com.otdr.otdr.Repositories;

import com.otdr.otdr.Data.Entidades.AuditoriaGestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuditoriaGestionRepository extends JpaRepository<AuditoriaGestion, Long> {

    @Query(nativeQuery = true,
    value = "SELECT * FROM auditoria_gestion a WHERE a.titulo= :titulo ORDER BY STR_TO_DATE(a.fecha, '%Y-%m-%d %H:%i:%s') DESC")
    public List<AuditoriaGestion> auditoriaTitulo(String titulo);

    @Query(nativeQuery = true,
            value = "SELECT * FROM auditoria_gestion a ORDER BY STR_TO_DATE(a.fecha, '%Y-%m-%d %H:%i:%s') DESC")
    public List<AuditoriaGestion> allAuditoria();

    @Query(
            nativeQuery = true,
            value = "SELECT COUNT(*) FROM auditoria_gestion"
    )
    public int numeroRegistros();
}
