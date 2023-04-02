package com.otdr.otdr.Services;

import com.otdr.otdr.Data.Entidades.Usuario;
import com.otdr.otdr.Shared.CrearUsuarioDTO;

import java.util.List;

public interface UsuarioService {

    public List<Usuario> listarUsuario();
    public Usuario guardarAdmin(Usuario usuario);
    public CrearUsuarioDTO guardarUsuario(CrearUsuarioDTO crearUsuarioDTO);
}
