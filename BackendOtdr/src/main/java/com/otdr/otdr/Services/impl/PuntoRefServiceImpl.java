package com.otdr.otdr.Services.impl;

import com.otdr.otdr.Data.Entidades.*;
import com.otdr.otdr.Models.Respuestas.ListarPuntoRefResponse;
import com.otdr.otdr.Repositories.*;
import com.otdr.otdr.Security.Exceptions.MyException;
import com.otdr.otdr.Services.PuntoRefService;
import com.otdr.otdr.Shared.CrearPuntoRefDTO;
import com.otdr.otdr.Shared.CrearRutaDTO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PuntoRefServiceImpl implements PuntoRefService {


    @Autowired
    private RutaRepository rutaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TipoPuntoRepository puntoRepository;
    @Autowired
    private PuntoRefRepository puntoRefRepository;
    @Autowired
    private AuditoriaGestionRepository gestionRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void guardarArchivo(MultipartFile file, String rutaNombre, String userLogeado) {

        Ruta ruta = rutaRepository.findByRutaNombre(rutaNombre);
        if (ruta == null){
            throw new MyException("La ruta no existe");
        }
        try {
            List<PuntoReferencia> puntoReferenciaList = leerExcel(file.getInputStream(), ruta);
            for (PuntoReferencia punto : puntoReferenciaList){
                CrearPuntoRefDTO puntoRefDTO = modelMapper.map(punto, CrearPuntoRefDTO.class);
                puntoRefDTO.setRuta(punto.getRuta().getRutaNombre());
                puntoRefDTO.setTipoPunto(punto.getTipoPunto().getTipoNombre());

                guardarManual(puntoRefDTO);
            }
            System.out.println("Se caracterizo los postes");

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM--dd");
            String fecha = simpleDateFormat.format(calendar.getTime());
            Usuario usuario = usuarioRepository.findByEmail(userLogeado);

            UsuarioServiceImpl usuarioService = new UsuarioServiceImpl();
            usuarioService.auditoriaGestion("CARACTERIZO","Caracterizo por el excel puntos en la ruta: "+ruta.getRutaNombre(),fecha,usuario);

        }catch (Exception e){
            e.printStackTrace();
            throw new MyException("Error al leer el excel");
        }


    }

    @Override
    public CrearPuntoRefDTO guardarManual(CrearPuntoRefDTO puntoRefDTO) {


        Ruta ruta = rutaRepository.findByRutaNombre(puntoRefDTO.getRuta());
        TipoPunto tipoPunto = puntoRepository.findByTipoNombre(puntoRefDTO.getTipoPunto());
        if (ruta == null){
            throw new MyException("la ruta no existe");
        }
        if (tipoPunto == null){
            throw new MyException("El tipo del punto no esta registrado");
        }

        String kmAnt;

        if (puntoRefDTO.getNombrePunto() > 1){
            PuntoReferencia puntoAnt = puntoRefRepository.findByNombrePunto(puntoRefDTO.getNombrePunto() - 1, ruta.getId());
            double lat1 = puntoAnt.getLatitud();
            double long1 = puntoAnt.getLongitud();
            double lat2 = puntoRefDTO.getLatitud();
            double long2 = puntoRefDTO.getLongitud();

            kmAnt = calcularDistancia(lat1, long1, lat2, long2);
        }else {
            kmAnt = "0";
        }


        List<PuntoReferencia> puntoReferenciaList = puntoRefRepository.listarPuntos(puntoRefDTO.getNombrePunto(), ruta.getId());
        int cont =0, name = puntoRefDTO.getNombrePunto();

        if (!puntoReferenciaList.isEmpty()){
            for (PuntoReferencia punto : puntoReferenciaList){
                name++;
                if (cont != 1){
                    double lat2 = punto.getLatitud();
                    double long2 = punto.getLongitud();
                    double lat1 = puntoRefDTO.getLatitud();
                    double long1 = puntoRefDTO.getLongitud();
                    punto.setKmAnterior(calcularDistancia(lat1, long1, lat2, long2));
                    cont++;
                }

                punto.setNombrePunto(name);
                puntoRefRepository.save(punto);
            }
        }


        PuntoReferencia puntoReferencia1 = modelMapper.map(puntoRefDTO, PuntoReferencia.class);
        puntoReferencia1.setRuta(ruta);
        puntoReferencia1.setTipoPunto(tipoPunto);
        puntoReferencia1.setNombrePunto(puntoReferencia1.getNombrePunto());
        puntoReferencia1.setKmAnterior(kmAnt);

        CrearPuntoRefDTO crearPuntoRefDTO = modelMapper.map(puntoRefRepository.save(puntoReferencia1), CrearPuntoRefDTO.class);
        crearPuntoRefDTO.setTipoPunto(puntoReferencia1.getTipoPunto().getTipoNombre());
        crearPuntoRefDTO.setRuta(puntoReferencia1.getRuta().getRutaNombre());
        crearPuntoRefDTO.setKmAnterior(puntoReferencia1.getKmAnterior());

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM--dd");
        String fecha = simpleDateFormat.format(calendar.getTime());
        Usuario usuario = usuarioRepository.findByEmail(puntoRefDTO.getUserLogeado());

        auditoriaGestion("CARACTERIZO","Caracterizo el punto: "+crearPuntoRefDTO.getNombrePunto()+" de la ruta: "+crearPuntoRefDTO.getRuta(),fecha,usuario);


        return crearPuntoRefDTO;
    }

    private List<PuntoReferencia> leerExcel(InputStream inputStream, Ruta ruta) throws IOException {
        List<PuntoReferencia> puntoReferencias = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);

        for (Row row: sheet){
            if (row.getRowNum() == 0){
                continue;
            }
            Iterator<Cell> cellIterator = row.iterator();
            int cellIndex =0;
            String tipo="", long2="", lat2="", med="";
            int nombre=0, cantR=0;

            while (cellIterator.hasNext()){
                Cell cell = cellIterator.next();

                switch (cellIndex){
                    case 0 : tipo = cell.getStringCellValue();break;
                    case 1 : nombre = (int) cell.getNumericCellValue();break;
                    case 2 : cantR = (int) cell.getNumericCellValue();break;
                    case 3 : long2 = cell.getStringCellValue();break;
                    case 4 : lat2 = cell.getStringCellValue();break;
                    case 5 : med = cell.getStringCellValue();break;
                    default: {}
                }
                cellIndex++;
            }
            puntoReferencias.add(configPunto(tipo, long2, lat2, med, nombre, cantR, ruta));
        }

        workbook.close();
        inputStream.close();

        return puntoReferencias;

    }

    @Override
    public CrearRutaDTO guardarRuta(CrearRutaDTO rutaDTO) {

        System.out.println(rutaDTO.toString());

        String rutaN = rutaDTO.getRutaInicio()+" - "+rutaDTO.getRutaFin();
        String rutaM = rutaN.toUpperCase();

        Ruta rutaB = rutaRepository.findByRutaNombre(rutaM);
        if (rutaB != null){
            throw new MyException("Ya esta registrada esta ruta");
        }

        Ruta ruta = new Ruta();
        ruta.setRutaNombre(rutaM);

        rutaRepository.save(ruta);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM--dd");
        String fecha = simpleDateFormat.format(calendar.getTime());
        Usuario usuario = usuarioRepository.findByEmail(rutaDTO.getUserLogeado());

        auditoriaGestion("CREO","Creo la ruta: "+ruta.getRutaNombre(),fecha,usuario);


        return modelMapper.map(ruta, CrearRutaDTO.class);
    }

    @Override
    public List<Ruta> listarRuta() {

        List<Ruta> rutaList = rutaRepository.findAll();
        if (rutaList.isEmpty()){
            throw new MyException("No hay rutas registradas");
        }


        return rutaList;
    }

    @Override
    public List<ListarPuntoRefResponse> listarPuntosRuta(String ruta) {

        Ruta ruta1 = rutaRepository.findByRutaNombre(ruta);

        if (ruta1 == null){
            throw new MyException("La ruta no existe");
        }
        List<PuntoReferencia> puntoReferenciaList = puntoRefRepository.listarPuntoRuta(ruta1.getId());

        if (puntoReferenciaList.isEmpty()){
            throw new MyException("No hay puntos en esta ruta");
        }

        List<ListarPuntoRefResponse> listarPuntoRefResponses = new ArrayList<>();
        for (PuntoReferencia punto : puntoReferenciaList){
            ListarPuntoRefResponse refResponse = modelMapper.map(punto, ListarPuntoRefResponse.class);
            refResponse.setTipoPunto(punto.getTipoPunto().getTipoNombre());

            listarPuntoRefResponses.add(refResponse);
        }

        return listarPuntoRefResponses;
    }

    @Override
    public List<PuntoReferencia> listarPuntosOrd() {
        return puntoRefRepository.listarPuntosOrdenados();
    }

    public static String calcularDistancia (double lat1, double long1, double lat2, double long2){

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        long1 = Math.toRadians(long1);
        long2 = Math.toRadians(long2);

        DecimalFormat decimalFormat = new DecimalFormat("#.###");

        double difLat = lat2 - lat1;
        double difLong = long2 - long1;
        final double kmTierra = 6378.137;

        double a = Math.sin(difLat / 2) * Math.sin(difLat / 2) + Math.cos(lat1)
                * Math.cos(lat2) * Math.sin(difLong / 2) * Math.sin(difLong / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt( 1 - a ));

        double d = kmTierra * c;

        double dMetros = d * 1000;
        String mString="", dMetro = decimalFormat.format(dMetros);

        for (int i=0; i< dMetro.length(); i++){

            if (dMetro.charAt(i) == ','){
                mString += '.';
            }else {
                mString += dMetro.charAt(i);
            }

        }
        dMetros = Double.parseDouble(mString);
        double dFinal = dMetros * 1000;

        return String.valueOf(dFinal);

    }

    public PuntoReferencia configPunto(String tipo,String longt,String lat,String med,int nombre,int cantR, Ruta ruta){

        TipoPunto tipoPunto = puntoRepository.findByTipoNombre(tipo);
        String cantRem = String.valueOf(cantR);
        double long2 = Double.parseDouble(longt);
        double lat2 = Double.parseDouble(lat);
        boolean medicion;
        String kmAnt="0";

        if (Objects.equals(med, "si") || Objects.equals(med, "Si") || Objects.equals(med, "SI") || Objects.equals(med, "sI")){
            medicion = true;
        } else if (Objects.equals(med, "no") || Objects.equals(med, "No") || Objects.equals(med, "NO") || Objects.equals(med, "nO")) {
            medicion = false;
        } else {
            throw new MyException("Diligencio el campo MEDICION mal");
        }

        if (nombre > 1){
            PuntoReferencia puntoAnt = puntoRefRepository.findByNombrePunto(nombre - 1, ruta.getId());
            double lat1 = puntoAnt.getLatitud();
            double long1 = puntoAnt.getLongitud();

            kmAnt = calcularDistancia(lat1, long1, lat2, long2);
        }

        PuntoReferencia puntoReferencia = new PuntoReferencia();
        puntoReferencia.setTipoPunto(tipoPunto);
        puntoReferencia.setNombrePunto(nombre);
        puntoReferencia.setCantRemanente(cantRem);
        puntoReferencia.setLongitud(long2);
        puntoReferencia.setLatitud(lat2);
        puntoReferencia.setKmAnterior(kmAnt);
        puntoReferencia.setMedicion(medicion);

        return puntoReferencia;
    }

    public void auditoriaGestion(String titulo, String desc, String fecha, Usuario user){

        int nRegistros = gestionRepository.numeroRegistros();
        System.out.println(nRegistros+" ***********");

        AuditoriaGestion auditoriaGestion = new AuditoriaGestion();
        auditoriaGestion.setId(nRegistros +1);
        auditoriaGestion.setTitulo(titulo);
        auditoriaGestion.setDescripcion(desc);
        auditoriaGestion.setFecha(fecha);
        auditoriaGestion.setUser(user);

        System.out.println(auditoriaGestion.toString());

        gestionRepository.save(auditoriaGestion);

    }
}
