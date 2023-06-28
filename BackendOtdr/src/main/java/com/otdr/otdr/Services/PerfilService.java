package com.otdr.otdr.Services;

import com.otdr.otdr.Data.Entidades.Perfil;
import com.otdr.otdr.Models.Peticiones.PerfilCrearRequest;
import com.otdr.otdr.Models.Respuestas.ListarPerfilResponse;

import java.util.List;

public interface PerfilService {

    public List<ListarPerfilResponse> listarPerfil();
    public List<ListarPerfilResponse> listarPerfilHabilitado();

    public void crearPerfil(PerfilCrearRequest perfilCrearRequest);
    public String deshabilitarPerfil(String perfilNombre,boolean estado);

}
