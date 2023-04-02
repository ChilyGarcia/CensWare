package com.otdr.otdr.Controllers;

import com.otdr.otdr.Models.Peticiones.UsuarioRegisterRequest;
import com.otdr.otdr.Models.Respuestas.UsuarioRegisterResponse;
import com.otdr.otdr.Services.UsuarioService;
import com.otdr.otdr.Shared.CrearUsuarioDTO;
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

    @PostMapping("/agregar") //metodo para agregar usuarios al sistema
    public ResponseEntity<?> agregarUsuario(@RequestBody UsuarioRegisterRequest registerRequest){

        CrearUsuarioDTO usuarioDTO = modelMapper.map(registerRequest, CrearUsuarioDTO.class);

        CrearUsuarioDTO usuarioDTOSave = usuarioService.guardarUsuario(usuarioDTO);
        UsuarioRegisterResponse response = modelMapper.map(usuarioDTOSave, UsuarioRegisterResponse.class);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }


}
