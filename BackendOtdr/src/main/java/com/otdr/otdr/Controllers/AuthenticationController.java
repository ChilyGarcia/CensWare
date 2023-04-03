package com.otdr.otdr.Controllers;

import com.otdr.otdr.Data.Entidades.*;
import com.otdr.otdr.Models.Peticiones.JwtRequest;
import com.otdr.otdr.Models.Peticiones.PermisosRequest;
import com.otdr.otdr.Models.Respuestas.AdminResponse;
import com.otdr.otdr.Models.Respuestas.JwtResponse;
import com.otdr.otdr.Repositories.PermisoRepository;
import com.otdr.otdr.Repositories.RolRepository;
import com.otdr.otdr.Security.JwtUtils;
import com.otdr.otdr.Services.UsuarioService;
import com.otdr.otdr.Services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtils jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RolRepository rolRepository;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest jwtRequest){


        String mensaje = autenticar(jwtRequest.getEmail(),jwtRequest.getPassword(),jwtRequest.getRol());
        System.out.println("------- "+jwtRequest.getRol()+" --------");
        switch (mensaje){
            case "OK":
                UserDetails userDetails =  this.userDetailsService.loadUser(jwtRequest.getEmail(),jwtRequest.getRol());
                String token = this.jwtUtil.generateToken(userDetails);
                return ResponseEntity.ok(new JwtResponse(token));
            case "FAIL":
                return new  ResponseEntity<>(new Mensaje("Credenciales invalidas"), HttpStatus.NOT_FOUND);
            case "NX":
                return new ResponseEntity<>(new Mensaje("No existe ningun usuario con esas credenciales"),HttpStatus.NOT_FOUND);
            default:
                return new ResponseEntity<>(new Mensaje("..."),HttpStatus.NOT_FOUND);
        }

    }

    private String autenticar(String username,String password,String rol) {

        List<Usuario> user = usuarioService.listarUsuario();
        if (!user.isEmpty()){
            String msj="";
            for (Usuario users:user){
                if (passwordEncoder.matches(password, users.getPassword()) && Objects.equals(users.getRoles().getRolNombre(), rol)&&
                        Objects.equals(users.getEmail(), username)) {
                    msj = "OK";
                    break;
                }else {
                    msj = "FAIL";
                }
            }

            return msj;
        }else {
            return "NX";
        }

    }
    @GetMapping("/save-admin")
    public ResponseEntity<AdminResponse> guardarAdmin() throws Exception {

        Perfil perfil = rolRepository.findByRolNombre("ADMIN");

        Usuario usuario = new Usuario();
        usuario.setNombre("admin");
        usuario.setApellido("fesc");
        usuario.setCedula("800235151");
        usuario.setCelular("+573227613865");
        usuario.setEmail("adminFesc@fesc.edu.co");
        String pass = "Ac.ñ@1p!87Da$-";
        usuario.setPassword(passwordEncoder.encode(pass));
        usuario.setRoles(perfil);

        Usuario usuarioGuardado = usuarioService.guardarAdmin(usuario);
        System.out.println(usuarioGuardado.getNombre());

        return ResponseEntity.ok(new AdminResponse(usuario.getEmail(), pass));
    }

}
