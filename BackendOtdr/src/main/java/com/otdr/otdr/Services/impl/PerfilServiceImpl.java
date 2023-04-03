package com.otdr.otdr.Services.impl;

import com.otdr.otdr.Data.Entidades.Perfil;
import com.otdr.otdr.Models.Respuestas.ListarPerfilResponse;
import com.otdr.otdr.Repositories.RolRepository;
import com.otdr.otdr.Security.Exceptions.MyException;
import com.otdr.otdr.Services.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PerfilServiceImpl implements PerfilService {

    @Autowired
    private RolRepository rolRepository;
    @Override
    public List<ListarPerfilResponse> listarPerfil() {

        List<Perfil> perfils = rolRepository.findAll();

        if (perfils.isEmpty()){
            throw new MyException("No hay perfiles ha mostrar");
        }

        List<ListarPerfilResponse> listarPerfilResponseList = new ArrayList<>();

        for (Perfil perfil:perfils){
            ListarPerfilResponse perfilResponse = new ListarPerfilResponse();
            perfilResponse.setPerfilNombre(perfil.getRolNombre());

            listarPerfilResponseList.add(perfilResponse);
        }

        return listarPerfilResponseList;
    }
}
