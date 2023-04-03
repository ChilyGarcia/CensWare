package com.otdr.otdr.Services.impl;

import com.otdr.otdr.Data.Entidades.Perfil;
import com.otdr.otdr.Data.Entidades.Usuario;
import com.otdr.otdr.Repositories.RolRepository;
import com.otdr.otdr.Repositories.UsuarioRepository;
import com.otdr.otdr.Security.Exceptions.MyException;
import com.otdr.otdr.Services.UsuarioService;
import com.otdr.otdr.Shared.CrearUsuarioDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<Usuario> listarUsuario() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario guardarAdmin(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public CrearUsuarioDTO guardarUsuario(CrearUsuarioDTO crearUsuarioDTO) {

        Usuario usuario = usuarioRepository.findByEmail(crearUsuarioDTO.getEmail());
        if(usuario != null){
            throw new MyException("Ya existe un usuario registrado con este correo");
        }

        Perfil perfil = rolRepository.findByRolNombre(crearUsuarioDTO.getPerfil());
        if (perfil == null){
            throw new MyException("El perfil no existe");
        }

        Usuario user = modelMapper.map(crearUsuarioDTO, Usuario.class);
        user.setPassword(passwordEncoder.encode(crearUsuarioDTO.getPassword()));
        user.setRoles(perfil);

        Usuario usuarioSave = usuarioRepository.save(user);

        CrearUsuarioDTO usuarioDTO = modelMapper.map(usuarioSave, CrearUsuarioDTO.class);
        usuarioDTO.setPerfil(usuarioSave.getRoles().getRolNombre());

        return usuarioDTO;
    }
}
