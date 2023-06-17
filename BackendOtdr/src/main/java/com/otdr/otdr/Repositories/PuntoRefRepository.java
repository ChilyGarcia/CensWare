package com.otdr.otdr.Repositories;

import com.otdr.otdr.Data.Entidades.PuntoReferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PuntoRefRepository extends JpaRepository<PuntoReferencia, Long> {

    @Query(nativeQuery = true,
    value = "SELECT * FROM punto_ref p WHERE p.nombre_punto= :nombre AND p.ruta_id= :ruta")
    public PuntoReferencia findByNombrePunto(Integer nombre, long ruta);

    @Query(nativeQuery = true,
    value = "SELECT * FROM punto_ref p WHERE p.ruta_id = :ruta ORDER BY p.nombre_punto ASC")
    public List<PuntoReferencia> listarPuntoRuta(long ruta);

    @Query(nativeQuery = true,
    value = "SELECT * FROM punto_ref ORDER BY nombre_punto ASC")
    public List<PuntoReferencia> listarPuntosOrdenados();

    @Query(nativeQuery = true,
    value = "SELECT * FROM punto_ref p WHERE p.nombre_punto >= :nombre AND p.ruta_id= :ruta ORDER BY p.nombre_punto ASC")
    public List<PuntoReferencia> listarPuntos(int nombre, long ruta);
}
