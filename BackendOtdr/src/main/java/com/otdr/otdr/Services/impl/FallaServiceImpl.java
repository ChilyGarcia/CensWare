package com.otdr.otdr.Services.impl;

import com.otdr.otdr.Data.Entidades.*;
import com.otdr.otdr.Models.Respuestas.PuntoFallo;
import com.otdr.otdr.Repositories.*;
import com.otdr.otdr.Security.Exceptions.MyException;
import com.otdr.otdr.Services.FallaService;
import com.otdr.otdr.Shared.SolucionFalloDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FallaServiceImpl implements FallaService {

    @Autowired
    private PuntoRefRepository puntoRefRepository;
    @Autowired
    private FalloRepository falloRepository;
    @Autowired
    private SolucionFalloRepository solucionFalloRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RutaRepository rutaRepository;
    @Autowired
    private AuditoriaRepository auditoriaRepository;


    @Override
    public List<PuntoFallo> calcularFallo(String ruta, int nombreP, MultipartFile file) throws IOException {
        Ruta ruta2 = rutaRepository.findByRutaNombre(ruta);
        List<PuntoReferencia> list = puntoRefRepository.listarPuntos(nombreP, ruta2.getId());

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
        int cont = 0;

        List<PuntoFallo> puntoFalloList = new ArrayList<>();
        for (PuntoReferencia puntoReferencia : list){

            if (cont == 0){
                x += Double.parseDouble(puntoReferencia.getCantRemanente());
            }else {
                x += (Double.parseDouble(puntoReferencia.getKmAnterior())+ Double.parseDouble(puntoReferencia.getCantRemanente()));
            }

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
            cont++;

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

    @Override
    public String saveSolucionFallo(SolucionFalloDTO solucionFalloDTO) {

        System.out.println(solucionFalloDTO.toString());

        Usuario usuario = usuarioRepository.findByEmail(solucionFalloDTO.getEmail());
        Ruta ruta = rutaRepository.findByRutaNombre(solucionFalloDTO.getRuta());
        Fallo fallo = falloRepository.findFalloNombre(solucionFalloDTO.getFallo());

        if (ruta == null){
            throw new MyException("La ruta no existe");
        } else if (usuario == null) {
            throw new MyException("El usuario no esta registrado");
        } else if (fallo == null) {
            throw new MyException("El tipo de fallo no esta especificado");
        }
        Integer nombrePunto = solucionFalloDTO.getNombreP();
        Integer nPunto=solucionFalloDTO.getNombreP() + 1;
        System.out.println(nPunto+" **********");
        PuntoReferencia punto1 = puntoRefRepository.findByNombrePunto(nombrePunto, ruta.getId());
        PuntoReferencia punto2 = puntoRefRepository.findByNombrePunto(nPunto, ruta.getId());
        System.out.println(punto2.toString());
        String nRem;
        if (!(Double.parseDouble(punto1.getCantRemanente()) > Double.parseDouble(solucionFalloDTO.getRemutilizado()))){
            throw new MyException("El remanente utilizado es mayor a la cantidad que estaba registrada");
        }else {
            nRem = String.valueOf(Double.parseDouble(punto1.getCantRemanente()) - Double.parseDouble(solucionFalloDTO.getRemutilizado()));
        }

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String fecha = simpleDateFormat.format(calendar.getTime());


        SolucionFallo solucionFallo = new SolucionFallo();
        solucionFallo.setDescSolucion(solucionFalloDTO.getDescripcion());
        solucionFallo.setMensaje(solucionFalloDTO.getMensaje());
        solucionFallo.setRemUtilizado(solucionFalloDTO.getRemutilizado());
        solucionFallo.setFecha(fecha);
        solucionFallo.setUsuario(usuario);
        solucionFallo.setFallo(fallo);
        solucionFallo.setRuta(ruta);
        solucionFallo.setPuntoReferencia(punto1);
        solucionFallo.setPuntoRef2(punto2);

        solucionFalloRepository.save(solucionFallo);

        punto1.setCantRemanente(nRem);
        puntoRefRepository.save(punto1);

        Auditoria auditoria = new Auditoria();
        auditoria.setTitulo(fallo.getFalloNombre());
        auditoria.setRuta(ruta.getRutaNombre());
        auditoria.setFecha(solucionFallo.getFecha());
        auditoria.setSolucionFallo(solucionFallo);

        auditoriaRepository.save(auditoria);

        return "Se registro la solucion del fallo";
    }

    public double leerDistancia(MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();
        byte[] fileContent = file.getBytes();
        String ruta;

        try {
            assert fileName != null;
            File tempFile = File.createTempFile(fileName, null);

            Files.write(tempFile.toPath(), fileContent);

            ruta = tempFile.getAbsolutePath();

        } catch (IOException e) {
            e.printStackTrace();
            throw new MyException("Error al sacar el path del file");
        }

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
