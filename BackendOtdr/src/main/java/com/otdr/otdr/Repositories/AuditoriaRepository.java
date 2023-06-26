package com.otdr.otdr.Repositories;

import com.otdr.otdr.Data.Entidades.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Long> {

    @Query(
            nativeQuery = true,
            value = "SELECT a.ruta, COUNT(*) AS cantidad\n" +
                    "FROM auditorias a\n" +
                    "WHERE STR_TO_DATE(a.fecha, '%Y-%m-%d') >= :fecha\n" +
                    "GROUP BY a.ruta DESC LIMIT 5"
    )
    public List<Object[]> rutasMasFallas(LocalDate fecha);

    @Query(
            nativeQuery = true,
            value = "SELECT a.titulo, COUNT(*) AS cantidad\n" +
                    "FROM auditorias a\n" +
                    "GROUP BY a.titulo"
    )
    public List<Object[]> tiposFalloComun();

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM auditorias a ORDER BY STR_TO_DATE(a.fecha, '%Y-%m-%d') DESC"
    )
    public List<Auditoria> allAuditoria();
}
