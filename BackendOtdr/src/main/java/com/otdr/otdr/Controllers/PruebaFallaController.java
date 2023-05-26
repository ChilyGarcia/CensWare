package com.otdr.otdr.Controllers;

import com.opencsv.CSVWriter;
import com.otdr.otdr.Data.Entidades.PuntoReferencia;
import com.otdr.otdr.Models.Respuestas.PuntoFallo;
import com.otdr.otdr.Services.PuntoRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("/falla")
@CrossOrigin("*")
public class PruebaFallaController {

    @Autowired
    private PuntoRefService puntoRefService;

    private List<PuntoReferencia> puntosPrueba(){

        List<PuntoReferencia> puntoReferenciaList = new ArrayList<>();

        PuntoReferencia punto1 = new PuntoReferencia();
        punto1.setNombrePunto(1);
        punto1.setLatitud(848393973);
        punto1.setLongitud(9826262);
        punto1.setCantRemanente("20");
        punto1.setKmAnterior("0.0");

        puntoReferenciaList.add(punto1);

        PuntoReferencia punto2 = new PuntoReferencia();
        punto2.setNombrePunto(2);
        punto2.setLatitud(12030398);
        punto2.setLongitud(233819);
        punto2.setCantRemanente("50");
        punto2.setKmAnterior("500.0");

        puntoReferenciaList.add(punto2);

        PuntoReferencia punto3 = new PuntoReferencia();
        punto3.setNombrePunto(3);
        punto3.setLatitud(735222234);
        punto3.setLongitud(65329022);
        punto3.setCantRemanente("30");
        punto3.setKmAnterior("400.0");

        puntoReferenciaList.add(punto3);

        return puntoReferenciaList;

    }

    @PostMapping("/calcular/{dist}")
    public ResponseEntity<?> calcularFalla (@PathVariable("dist") double distFalla){
        List<PuntoReferencia> list = puntoRefService.listarPuntosOrd();



        for(PuntoReferencia puntoReferencia:list){
            System.out.println(puntoReferencia.getNombrePunto());
        }

        String mensaje;

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

            return new ResponseEntity<>(puntoFallo, HttpStatus.OK);

        }else {

            List<PuntoFallo> puntoFalloList = new ArrayList<>();

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

            return new ResponseEntity<>(puntoFalloList, HttpStatus.OK);
        }


    }

    @GetMapping(value = "/sor", produces = MediaType.TEXT_PLAIN_VALUE)
    public String convertSorToCsv() {
        try {
            // Lee el archivo .sor
            File sorFile = new ClassPathResource("otdr13ERR.sor").getFile();

            // Crea un lector de archivos
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(sorFile), StandardCharsets.UTF_8));

            // Crea un escritor de archivos CSV
            StringWriter writer = new StringWriter();
            CSVWriter csvWriter = new CSVWriter(writer);

            // Lee línea por línea del archivo .sor
            String line;
            while ((line = reader.readLine()) != null) {
                // Realiza la lógica para procesar y convertir cada línea a una lista de valores

                // Ejemplo: Separa la línea por comas y guarda los valores en una lista
                String[] values = line.split(",");
                csvWriter.writeNext(values);
            }

            // Cierra los recursos
            reader.close();
            csvWriter.close();

            // Devuelve el contenido del archivo CSV
            return writer.toString();
        } catch (IOException e) {
            // Manejo de excepciones
            e.printStackTrace();
            return "Error al leer el archivo .sor";
        }
    }

    @GetMapping("/sorT")
    public ResponseEntity<String> getSorContent() {
        // Ruta del archivo .sor
        String sorFilePath = "C:/Users/ING.Derian/Desktop/otdr13ERR.sor";

        try {
            // Lee el contenido del archivo .sor
            String sorContent = readFileContent(sorFilePath);

            // Devuelve el contenido en formato JSON
            return ResponseEntity.ok(sorContent);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error al leer el archivo .sor: " + e.getMessage());
        }
    }

    private String readFileContent(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        BufferedReader reader = Files.newBufferedReader(path);
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line);
        }
        reader.close();
        return content.toString();
    }

}
