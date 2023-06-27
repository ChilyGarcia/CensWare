package com.otdr.otdr.Controllers;

import com.otdr.otdr.Data.Entidades.Mensaje;
import com.otdr.otdr.Data.Entidades.Ruta;
import com.otdr.otdr.Models.Peticiones.ActualizarPuntoRequest;
import com.otdr.otdr.Models.Peticiones.PuntoRefCrearRequest;
import com.otdr.otdr.Models.Peticiones.RutaCrearRequest;
import com.otdr.otdr.Models.Respuestas.PuntoRefCrearResponse;
import com.otdr.otdr.Models.Respuestas.RutaCrearResponse;
import com.otdr.otdr.Services.PuntoRefService;
import com.otdr.otdr.Shared.CrearPuntoRefDTO;
import com.otdr.otdr.Shared.CrearRutaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/caracterizacion")
@CrossOrigin("*")
public class CaracterizacionController {

    @Autowired
    private PuntoRefService puntoRefService;
    @Autowired
    private ModelMapper modelMapper;


    @PostMapping("/ruta-save")
    public ResponseEntity<?> crearRuta(@RequestBody RutaCrearRequest rutaCrearRequest){

        CrearRutaDTO crearRutaDTO = modelMapper.map(rutaCrearRequest, CrearRutaDTO.class);
        CrearRutaDTO crearRutaDTOSave = puntoRefService.guardarRuta(crearRutaDTO);

        RutaCrearResponse rutaCrearResponse = modelMapper.map(crearRutaDTOSave, RutaCrearResponse.class);

        return new ResponseEntity<>(rutaCrearResponse, HttpStatus.OK);
    }

    @PostMapping("/puntoref-auto-save/{ruta}/{email}")
    public ResponseEntity<?> guardarPuntoRef(@RequestParam("file")MultipartFile file, @PathVariable("ruta") String ruta,
                                             @PathVariable("email")String email){

        try {

            puntoRefService.guardarArchivo(file, ruta, email);

            return new ResponseEntity<>(new Mensaje("Datos guardados"), HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new Mensaje("Error al guardar los datos"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/puntoref-manual-save")
    public ResponseEntity<?> guardarPuntoManual(@RequestBody PuntoRefCrearRequest puntoRefCrearRequest){

        CrearPuntoRefDTO crearPuntoRefDTO = modelMapper.map(puntoRefCrearRequest, CrearPuntoRefDTO.class);

        CrearPuntoRefDTO puntoRefDTO = puntoRefService.guardarManual(crearPuntoRefDTO);
        puntoRefDTO.setPArchivo(false);

        PuntoRefCrearResponse crearResponse = modelMapper.map(puntoRefDTO, PuntoRefCrearResponse.class);


        return new ResponseEntity<>(crearResponse, HttpStatus.OK);

    }

    @GetMapping("/ruta-list")
    public ResponseEntity<?> listarRuta(){

        List<Ruta> rutaList = puntoRefService.listarRuta();

        return new ResponseEntity<>(rutaList, HttpStatus.OK);
    }

    @PostMapping("/agragar-rem")
    public ResponseEntity<?> actualizarPunto(@RequestBody ActualizarPuntoRequest puntoRequest){
        try {
            puntoRefService.actualizarRem(puntoRequest);
            return new ResponseEntity<>(new Mensaje("Se actualizo el punto"), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new Mensaje("Error al actualizar el punto"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
