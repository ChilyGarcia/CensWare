package com.otdr.otdr.Services.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.otdr.otdr.Data.Entidades.PuntoReferencia;
import com.otdr.otdr.Data.Entidades.Ruta;
import com.otdr.otdr.Models.Respuestas.PuntoFallo;
import com.otdr.otdr.Repositories.PuntoFalloRepository;
import com.otdr.otdr.Repositories.PuntoRefRepository;
import com.otdr.otdr.Repositories.RutaRepository;
import com.otdr.otdr.Security.Exceptions.MyException;
import com.otdr.otdr.Services.FallaService;
import org.apache.poi.ss.usermodel.*;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FallaServiceImpl implements FallaService {

    @Autowired
    private PuntoRefRepository puntoRefRepository;
    @Autowired
    private PuntoFalloRepository falloRepository;
    @Autowired
    private RutaRepository rutaRepository;


    @Override
    public List<PuntoFallo> calcularFallo(String ruta, int nombreP, MultipartFile file) throws IOException {
        Ruta ruta2 = rutaRepository.findByRutaNombre(ruta);
        List<PuntoReferencia> list = puntoRefRepository.listarPuntos(nombreP, ruta2.getId());

        for(PuntoReferencia puntoReferencia:list){
            System.out.println(puntoReferencia.getNombrePunto());
        }

        String mensaje;
        double distFalla = obtenerDist(file);

        double x =0,dist;

        //Datos punto Anterior
        String nombre = " ";
        String longitud = " ";
        String latitud = " ";
        double kmTotal = 0;

        //Datos punto Actual
        String nombreAct = " ";
        String longitudAct = " ";
        String latitudAct = " ";
        String remanenteAct ="0";
        double kmAnterior = 0;
        double kmTotalAct = 0;

        List<PuntoFallo> puntoFalloList = new ArrayList<>();
        for (PuntoReferencia puntoReferencia : list){
            x += (Double.parseDouble(puntoReferencia.getKmAnterior())+ Double.parseDouble(puntoReferencia.getCantRemanente()));

            System.out.println(x);
            if (x >= distFalla){

                nombreAct = String.valueOf(puntoReferencia.getNombrePunto());
                longitudAct = String.valueOf(puntoReferencia.getLongitud());
                latitudAct = String.valueOf(puntoReferencia.getLatitud());
                remanenteAct = puntoReferencia.getCantRemanente();
                kmTotalAct = Double.parseDouble(puntoReferencia.getKmAnterior()) / 2;
                kmAnterior = Double.parseDouble(puntoReferencia.getKmAnterior());

                break;
            }
            nombre = String.valueOf(puntoReferencia.getNombrePunto());
            longitud = String.valueOf(puntoReferencia.getLongitud());
            latitud = String.valueOf(puntoReferencia.getLatitud());
            kmTotal = x;

        }

        if (distFalla >= (x - Double.parseDouble(remanenteAct)) && distFalla <= x){

            PuntoFallo puntoFallo = new PuntoFallo();
            puntoFallo.setNombre(nombreAct);
            puntoFallo.setLongitud(longitudAct);
            puntoFallo.setLatitud(latitudAct);
            puntoFallo.setMensaje("El fallo esta en el poste "+nombreAct);

            puntoFalloList.add(puntoFallo);

        }else {



            if (kmTotal < distFalla){
                dist =  distFalla - kmTotal;
            }else {
                dist = kmTotal - distFalla;
            }

            if (dist <= kmTotalAct){
                mensaje = "EL FALLO ESTA A "+dist+" METROS DEL POSTE "+nombre;
            }else {
                mensaje = "EL FALLO ESTA A "+(kmAnterior - dist)+" METROS ANTES DEL POSTE "+nombreAct;
            }




            PuntoFallo puntoFallo = new PuntoFallo();
            puntoFallo.setNombre(nombre);
            puntoFallo.setLongitud(longitud);
            puntoFallo.setLatitud(latitud);
            puntoFallo.setMensaje(mensaje);

            PuntoFallo puntoFallo2 = new PuntoFallo();
            puntoFallo2.setNombre(nombreAct);
            puntoFallo2.setLongitud(longitudAct);
            puntoFallo2.setLatitud(latitudAct);
            puntoFallo2.setMensaje(mensaje);

            puntoFalloList.add(puntoFallo);
            puntoFalloList.add(puntoFallo2);

        }
        return puntoFalloList;

    }

    public Double obtenerDist(MultipartFile file) throws IOException {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String dirFile = "file/sor";

        Path uploadPath = Paths.get(dirFile);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = file.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }

        clojure.lang.PersistentHashMap results;

        Boolean verbose = true; // display results on screen
        results = cljotdr.parse.sorparse(dirFile + "/"+fileName, "trace.dat", verbose);
        // save result in JSON format
        cljotdr.dump.save_file(results,"C:\\Users\\ING.Derian\\Desktop\\otdr13ERRJS.json", 1);
        System.out.println("**********************");

        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get("C:\\Users\\ING.Derian\\Desktop\\otdr13ERRJS.json")));
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(jsonContent);
            JSONObject KeyEvents = (JSONObject) json.get("KeyEvents");
            JSONObject sumary = (JSONObject) KeyEvents.get("Summary");
            Double resultado = (Double) sumary.get("loss end");
            System.err.println("\n\n\n\n\n\n\n\nFalla km: " + resultado);

            return resultado;

        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
            throw new MyException("Error al leer el sor");
        }

    }
}
