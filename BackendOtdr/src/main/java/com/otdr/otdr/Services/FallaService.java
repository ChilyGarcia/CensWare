package com.otdr.otdr.Services;

import com.otdr.otdr.Models.Respuestas.PuntoFallo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FallaService {

    public List<PuntoFallo> calcularFallo(String ruta, int nombreP);
}
