package com.otdr.otdr.Controllers;

import com.otdr.otdr.Data.Entidades.Mensaje;
import com.otdr.otdr.Models.Peticiones.PermisosRequest;
import com.otdr.otdr.Models.Peticiones.UsuarioRegisterRequest;
import com.otdr.otdr.Models.Respuestas.UsuarioRegisterResponse;
import com.otdr.otdr.Repositories.RolRepository;
import com.otdr.otdr.Security.Exceptions.MyException;
import com.otdr.otdr.Services.PerfilService;
import com.otdr.otdr.Services.PermisoService;
import com.otdr.otdr.Services.UsuarioService;
import com.otdr.otdr.Shared.CrearUsuarioDTO;
import com.otdr.otdr.Shared.ModificarPermisosDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PermisoService permisoService;
    @Autowired
    private PerfilService perfilService;

    @PostMapping("/agregar") //metodo para agregar usuarios al sistema
    public ResponseEntity<?> agregarUsuario(@RequestBody UsuarioRegisterRequest registerRequest){

        CrearUsuarioDTO usuarioDTO = modelMapper.map(registerRequest, CrearUsuarioDTO.class);

        CrearUsuarioDTO usuarioDTOSave = usuarioService.guardarUsuario(usuarioDTO);
        UsuarioRegisterResponse response = modelMapper.map(usuarioDTOSave, UsuarioRegisterResponse.class);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/permisos-edit")
    public ResponseEntity<?> editarPerAdmin(@RequestBody PermisosRequest permisosRequest){

        ModificarPermisosDTO permisosDTO = modelMapper.map(permisosRequest, ModificarPermisosDTO.class);

        permisoService.modificarPermiso(permisosDTO);

        return new ResponseEntity<>(new Mensaje("Se modificaron los permisos del peril "+permisosRequest.getPerfil())
                ,HttpStatus.OK);


    }
    @GetMapping("/perfiles-list")
    public ResponseEntity<?> listarPerfiles() {
        return new ResponseEntity<>(perfilService.listarPerfil(), HttpStatus.OK);
    }
}
