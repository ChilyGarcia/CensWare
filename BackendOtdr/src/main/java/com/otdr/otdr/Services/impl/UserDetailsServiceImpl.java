package com.otdr.otdr.Services.impl;

import com.otdr.otdr.Data.Entidades.Usuario;
import com.otdr.otdr.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;


    public UserDetails loadUser(String email, String rol){
        List<Usuario> usuario = usuarioRepository.findAll();
        Usuario user = null;
        for (Usuario users:usuario){
            if (users.getRoles().getRolNombre().equals(rol)&&
                    Objects.equals(users.getEmail(), email)){
                user = users;
            }
        }

        if (user == null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
