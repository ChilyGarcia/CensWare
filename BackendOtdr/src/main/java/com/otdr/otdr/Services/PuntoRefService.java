package com.otdr.otdr.Services;

import com.otdr.otdr.Data.Entidades.PuntoReferencia;
import com.otdr.otdr.Data.Entidades.Ruta;
import com.otdr.otdr.Models.Respuestas.ListarPuntoRefResponse;
import com.otdr.otdr.Models.Respuestas.PuntoFallo;
import com.otdr.otdr.Shared.CrearPuntoRefDTO;
import com.otdr.otdr.Shared.CrearRutaDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PuntoRefService {

    public void guardarArchivo(MultipartFile file, String rutaNombre) throws IOException;
    public CrearPuntoRefDTO guardarManual(CrearPuntoRefDTO puntoRefDTO);

    public CrearRutaDTO guardarRuta(CrearRutaDTO crearRutaDTO);

    public List<Ruta> listarRuta();
    public List<ListarPuntoRefResponse> listarPuntosRuta (String ruta);
    public List<PuntoReferencia> listarPuntosOrd();

}
