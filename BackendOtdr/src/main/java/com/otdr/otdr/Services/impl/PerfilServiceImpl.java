package com.otdr.otdr.Services.impl;

import com.otdr.otdr.Data.Entidades.AuditoriaGestion;
import com.otdr.otdr.Data.Entidades.Perfil;
import com.otdr.otdr.Data.Entidades.Permisos;
import com.otdr.otdr.Data.Entidades.Usuario;
import com.otdr.otdr.Models.Peticiones.PerfilCrearRequest;
import com.otdr.otdr.Models.Respuestas.ListarPerfilResponse;
import com.otdr.otdr.Repositories.AuditoriaGestionRepository;
import com.otdr.otdr.Repositories.PermisoRepository;
import com.otdr.otdr.Repositories.RolRepository;
import com.otdr.otdr.Repositories.UsuarioRepository;
import com.otdr.otdr.Security.Exceptions.MyException;
import com.otdr.otdr.Services.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class PerfilServiceImpl implements PerfilService {

    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private PermisoRepository permisoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AuditoriaGestionRepository gestionRepository;


    @Override
    public List<ListarPerfilResponse> listarPerfil() {

        List<Perfil> perfils = rolRepository.findAll();

        if (perfils.isEmpty()){
            throw new MyException("No hay perfiles ha mostrar");
        }

        List<ListarPerfilResponse> listarPerfilResponseList = new ArrayList<>();

        for (Perfil perfil:perfils){
            Permisos permiso = permisoRepository.findByNombre(perfil.getRolNombre());
            ListarPerfilResponse perfilResponse = new ListarPerfilResponse();
            perfilResponse.setPerfilNombre(perfil.getRolNombre());
            perfilResponse.setMaps(permiso.isMaps());
            perfilResponse.setCaracterizacion(permiso.isCaracterizacion());
            perfilResponse.setFallo(permiso.isFallo());
            perfilResponse.setDashboard(permiso.isDashboard());

            listarPerfilResponseList.add(perfilResponse);
        }

        return listarPerfilResponseList;
    }

    @Override
    public void crearPerfil(PerfilCrearRequest perfilCrearRequest) {

        Perfil perfil = rolRepository.findByRolNombre(perfilCrearRequest.getNombrePerfil());

        if (perfil != null){
            throw new MyException("Ya existe un perfil con este nombre");
        }

        List<Perfil> perfilList = rolRepository.findAll();
        Long id = 0L;
        for (Perfil perfil1:perfilList){
            id = perfil1.getId();
        }

        Perfil perfilNuevo = new Perfil();
        perfilNuevo.setRolNombre(perfilCrearRequest.getNombrePerfil());
        perfilNuevo.setPermisos(new Permisos(id+1,perfilCrearRequest.getNombrePerfil(),
                perfilCrearRequest.isMaps(),
                perfilCrearRequest.isCaracterizacion(),
                perfilCrearRequest.isFallo(),
                perfilCrearRequest.isDashboard()));

        rolRepository.save(perfilNuevo);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = simpleDateFormat.format(calendar.getTime());
        Usuario usuario = usuarioRepository.findByEmail(perfilCrearRequest.getUserLogeado());

        auditoriaGestion("CREO","Creo el perfil: "+perfilNuevo.getRolNombre(),fecha,usuario);

    }

    public void auditoriaGestion(String titulo, String desc, String fecha, Usuario user){

        int nRegistros = gestionRepository.numeroRegistros();
        System.out.println(nRegistros+" ***********");

        AuditoriaGestion auditoriaGestion = new AuditoriaGestion();
        auditoriaGestion.setId(nRegistros +1);
        auditoriaGestion.setTitulo(titulo);
        auditoriaGestion.setDescripcion(desc);
        auditoriaGestion.setFecha(fecha);
        auditoriaGestion.setUser(user);

        System.out.println(auditoriaGestion.toString());

        gestionRepository.save(auditoriaGestion);

    }
}
