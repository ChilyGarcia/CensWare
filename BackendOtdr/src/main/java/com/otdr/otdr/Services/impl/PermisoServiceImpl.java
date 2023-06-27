package com.otdr.otdr.Services.impl;

import com.otdr.otdr.Data.Entidades.AuditoriaGestion;
import com.otdr.otdr.Data.Entidades.Perfil;
import com.otdr.otdr.Data.Entidades.Permisos;
import com.otdr.otdr.Data.Entidades.Usuario;
import com.otdr.otdr.Repositories.AuditoriaGestionRepository;
import com.otdr.otdr.Repositories.PermisoRepository;
import com.otdr.otdr.Repositories.RolRepository;
import com.otdr.otdr.Repositories.UsuarioRepository;
import com.otdr.otdr.Security.Exceptions.MyException;
import com.otdr.otdr.Services.PermisoService;
import com.otdr.otdr.Shared.ModificarPermisosDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Service
public class PermisoServiceImpl implements PermisoService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private PermisoRepository permisoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AuditoriaGestionRepository gestionRepository;


    @Override
    public void modificarPermiso(ModificarPermisosDTO permisosDTO) {

        Perfil perfil = rolRepository.findByRolNombre(permisosDTO.getPerfil());
        if (perfil == null){
            throw new MyException("El perfil no existe");
        }

        Permisos permisos = permisoRepository.findByNombre(permisosDTO.getPerfil());
        List<Usuario> usuarios = usuarioRepository.findAll();

        Usuario userAdmin = null;
        if (!usuarios.isEmpty()){
            for(Usuario user : usuarios){
                if (Objects.equals(user.getRoles().getRolNombre(), "ADMIN")
                    && Objects.equals(user.getEmail(), permisosDTO.getUserLogeado())
                ){
                    userAdmin = user;
                }
            }
        }
        if (userAdmin == null){
            throw new MyException("No eres Admin");
        }
        permisos.setMaps(permisosDTO.isMaps());
        permisos.setCaracterizacion(permisosDTO.isCaracterizacion());
        permisos.setFallo(permisosDTO.isFallo());
        permisos.setDashboard(permisosDTO.isDashboard());

        perfil.setPermisos(permisos);

        Perfil perfil1 = rolRepository.save(perfil);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fecha = simpleDateFormat.format(calendar.getTime());
        Usuario usuario = usuarioRepository.findByEmail(permisosDTO.getUserLogeado());

        auditoriaGestion("MODIFICO","Modifico los permisos del perfil: "+perfil1.getRolNombre(),fecha,usuario);


    }

    @Override
    public Permisos obtenerPermisosRol(String rol) {

        Permisos permisos = permisoRepository.findByNombre(rol);
        if (permisos == null){
            throw new MyException("Este perfil no existe");
        }

        return permisos;
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
