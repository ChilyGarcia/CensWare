package com.otdr.otdr.Services.impl;

import com.otdr.otdr.Data.Entidades.Auditoria;
import com.otdr.otdr.Data.Entidades.AuditoriaGestion;
import com.otdr.otdr.Data.Entidades.Perfil;
import com.otdr.otdr.Data.Entidades.Usuario;
import com.otdr.otdr.Models.Respuestas.AuditoriaGestionResponse;
import com.otdr.otdr.Repositories.AuditoriaGestionRepository;
import com.otdr.otdr.Repositories.AuditoriaRepository;
import com.otdr.otdr.Repositories.RolRepository;
import com.otdr.otdr.Repositories.UsuarioRepository;
import com.otdr.otdr.Security.Exceptions.MyException;
import com.otdr.otdr.Services.UsuarioService;
import com.otdr.otdr.Shared.CrearUsuarioDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
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
    @Autowired
    private AuditoriaGestionRepository gestionRepository;
    @Autowired
    private AuditoriaRepository auditoriaRepository;

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
        Usuario userL = usuarioRepository.findByEmail(crearUsuarioDTO.getUserLogeado());
        if(usuario != null){
            throw new MyException("Ya existe un usuario registrado con este correo");
        }
        System.out.println(userL.getEmail()+" ********");

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

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = simpleDateFormat.format(calendar.getTime());

        auditoriaGestion("REGISTRO","Registro al usuario: "+usuarioSave.getNombre()+" "+usuarioSave.getApellido()+" C.C: "+usuarioSave.getCedula(),fecha,userL);

        return usuarioDTO;
    }

    @Override
    public List<AuditoriaGestionResponse> allAuditorias() {

        List<AuditoriaGestion> auditoriaGestions = gestionRepository.allAuditoria();
        List<AuditoriaGestionResponse> gestionResponse = new ArrayList<>();

        for (AuditoriaGestion auditoria: auditoriaGestions){
            AuditoriaGestionResponse gestionResponse1 = new AuditoriaGestionResponse();
            gestionResponse1.setTitulo(auditoria.getTitulo());
            gestionResponse1.setDesc(auditoria.getDescripcion());
            gestionResponse1.setFecha(auditoria.getFecha());
            gestionResponse1.setNombreUser(auditoria.getUser().getNombre()+" "+auditoria.getUser().getApellido());
            gestionResponse1.setCedula(auditoria.getUser().getCedula());

            gestionResponse.add(gestionResponse1);
        }

        return gestionResponse;
    }

    @Override
    public List<AuditoriaGestionResponse> auditoriaTitulo(String titulo) {

        List<AuditoriaGestion> auditoriaGestions = gestionRepository.auditoriaTitulo(titulo);
        List<AuditoriaGestionResponse> gestionResponse = new ArrayList<>();

        for (AuditoriaGestion auditoria: auditoriaGestions){
            AuditoriaGestionResponse gestionResponse1 = new AuditoriaGestionResponse();
            gestionResponse1.setTitulo(auditoria.getTitulo());
            gestionResponse1.setDesc(auditoria.getDescripcion());
            gestionResponse1.setFecha(auditoria.getFecha());
            gestionResponse1.setNombreUser(auditoria.getUser().getNombre()+" "+auditoria.getUser().getApellido());
            gestionResponse1.setCedula(auditoria.getUser().getCedula());

            gestionResponse.add(gestionResponse1);
        }

        return gestionResponse;
    }

    @Override
    public List<Object[]> auditoriaFallaTresMeses() {
        LocalDate fecha = LocalDate.now().minusMonths(3);
        return auditoriaRepository.rutasMasFallas(fecha);
    }

    @Override
    public List<Auditoria> allAuditoriaFalla(){
        return auditoriaRepository.findAll();
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
