package com.otdr.otdr.Services.impl;

import com.otdr.otdr.Data.Entidades.PuntoReferencia;
import com.otdr.otdr.Models.Respuestas.PuntoFallo;
import com.otdr.otdr.Repositories.PuntoFalloRepository;
import com.otdr.otdr.Repositories.PuntoRefRepository;
import com.otdr.otdr.Security.Exceptions.MyException;
import com.otdr.otdr.Services.FallaService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class FallaServiceImpl implements FallaService {

    @Autowired
    private PuntoRefRepository puntoRefRepository;
    @Autowired
    private PuntoFalloRepository falloRepository;


    @Override
    public List<PuntoFallo> calcularFallo(String file, int nombreP) {
        List<PuntoReferencia> list = puntoRefRepository.listarPuntos(nombreP);

        for(PuntoReferencia puntoReferencia:list){
            System.out.println(puntoReferencia.getNombrePunto());
        }

        String mensaje;
        double distFalla = leerDistancia(file);
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

            return puntoFalloList;

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

            return puntoFalloList;
        }

    }

    public double leerDistancia(String ruta){

        try (Workbook workbook = WorkbookFactory.create(new File(ruta))){

            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(17);
            Cell cell = row.getCell(6);

            String dCell="", sNum="";

            if (cell != null){
                dCell = cell.getStringCellValue();
            }
            System.out.println(dCell + " ---------");

            for (int i = 0; i<dCell.length(); i++){
                if (dCell.charAt(i) == ' '){
                    break;
                }
                if (dCell.charAt(i) == ','){
                    sNum += '.';
                }else {
                    sNum += dCell.charAt(i);
                }
            }
            System.out.println(sNum +"-----------");

            double sNumC = Double.parseDouble(sNum);
            double sNumM = sNumC * 1000;

            System.out.println(sNumM + " ------------");

            return sNumM;

        }catch (Exception e){
            e.printStackTrace();

            throw new MyException("Error al leer el archivo");
        }


    }
}
