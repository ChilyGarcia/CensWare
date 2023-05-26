package com.otdr.otdr.Controllers;

import com.otdr.otdr.Services.PuntoRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mostrar")
@CrossOrigin("*")
public class MostrarRutasController {


    @Autowired
    private PuntoRefService puntoRefService;

    @GetMapping("/puntos-ruta/{ruta}")
    public ResponseEntity<?> listarPuntosRuta (@PathVariable("ruta")String ruta){
        return new ResponseEntity<>(puntoRefService.listarPuntosRuta(ruta), HttpStatus.OK);
    }


}
