package com.otdr.otdr.Controllers;

import com.otdr.otdr.Models.Peticiones.SolucionFalloRequest;
import com.otdr.otdr.Services.FallaService;
import com.otdr.otdr.Services.PuntoRefService;
import com.otdr.otdr.Shared.SolucionFalloDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/falla")
@CrossOrigin("*")
public class PruebaFallaController {

    @Autowired
    private PuntoRefService puntoRefService;
    @Autowired
    private FallaService fallaService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/calcular/{ruta}/{nombreP}")
    public ResponseEntity<?> calcularFalla (@PathVariable("ruta")String ruta,
                                            @PathVariable("nombreP")int nombreP,
                                            @RequestParam("file")MultipartFile file) throws IOException {

        return new ResponseEntity<>(fallaService.calcularFallo(ruta, nombreP,file), HttpStatus.OK);
    }

    @PostMapping("/soluc-falla")
    public ResponseEntity<?> solucionFalla(@RequestBody SolucionFalloRequest falloRequest){

        SolucionFalloDTO falloDTO = modelMapper.map(falloRequest, SolucionFalloDTO.class);

        return new ResponseEntity<>(fallaService.saveSolucionFallo(falloDTO), HttpStatus.OK);

    }

}
