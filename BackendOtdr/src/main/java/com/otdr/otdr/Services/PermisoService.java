package com.otdr.otdr.Services;

import com.otdr.otdr.Data.Entidades.Permisos;
import com.otdr.otdr.Shared.ModificarPermisosDTO;

public interface PermisoService {

    public void modificarPermiso (ModificarPermisosDTO permisosDTO);

    public Permisos obtenerPermisosRol(String rol);

}
