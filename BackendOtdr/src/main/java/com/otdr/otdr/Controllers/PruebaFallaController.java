package com.otdr.otdr.Controllers;

import com.otdr.otdr.Models.Peticiones.CalcularFallaRequest;
import com.otdr.otdr.Services.FallaService;
import com.otdr.otdr.Services.PuntoRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/falla")
@CrossOrigin("*")
public class PruebaFallaController {

    @Autowired
    private PuntoRefService puntoRefService;
    @Autowired
    private FallaService fallaService;

    @PostMapping("/calcular")
    public ResponseEntity<?> calcularFalla (@RequestBody CalcularFallaRequest fallaRequest){

        return new ResponseEntity<>(fallaService.calcularFallo(fallaRequest.getPath(), fallaRequest.getNombreP()), HttpStatus.OK);
    }



}
