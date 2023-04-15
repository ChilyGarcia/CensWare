package com.otdr.otdr.Services.impl;

import com.otdr.otdr.Data.Entidades.PuntoReferencia;
import com.otdr.otdr.Data.Entidades.Ruta;
import com.otdr.otdr.Data.Entidades.TipoPunto;
import com.otdr.otdr.Repositories.PuntoRefRepository;
import com.otdr.otdr.Repositories.RutaRepository;
import com.otdr.otdr.Repositories.TipoPuntoRepository;
import com.otdr.otdr.Security.Exceptions.MyException;
import com.otdr.otdr.Services.PuntoRefService;
import com.otdr.otdr.Shared.CrearPuntoRefDTO;
import com.otdr.otdr.Shared.CrearRutaDTO;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class PuntoRefServiceImpl implements PuntoRefService {


    @Autowired
    private RutaRepository rutaRepository;
    @Autowired
    private TipoPuntoRepository puntoRepository;
    @Autowired
    private PuntoRefRepository puntoRefRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void guardarArchivo(MultipartFile file, String rutaNombre) throws IOException {

        Ruta ruta = rutaRepository.findByRutaNombre(rutaNombre);
        if (ruta == null){
            throw new MyException("La ruta no existe");
        }

        List<PuntoReferencia> puntoReferenciaList = leerExcel(file.getInputStream(), ruta);

        puntoRefRepository.saveAll(puntoReferenciaList);

    }

    @Override
    public CrearPuntoRefDTO guardarManual(CrearPuntoRefDTO puntoRefDTO) {

        PuntoReferencia puntoReferencia = puntoRefRepository.findByPuntoNombre(puntoRefDTO.getPuntoNombre().toUpperCase());
        if (puntoReferencia != null){
            throw new MyException("ya Existe un punto con este nombre");
        }

        Ruta ruta = rutaRepository.findByRutaNombre(puntoRefDTO.getRuta());
        TipoPunto tipoPunto = puntoRepository.findByTipoNombre(puntoRefDTO.getTipoPunto());
        if (ruta == null){
            throw new MyException("la ruta no existe");
        }
        if (tipoPunto == null){
            throw new MyException("El tipo del punto no esta registrado");
        }

        PuntoReferencia puntoReferencia1 = modelMapper.map(puntoRefDTO, PuntoReferencia.class);
        puntoReferencia1.setRuta(ruta);
        puntoReferencia1.setTipoPunto(tipoPunto);
        puntoReferencia1.setPuntoNombre(puntoReferencia1.getPuntoNombre().toUpperCase());

        CrearPuntoRefDTO crearPuntoRefDTO = modelMapper.map(puntoRefRepository.save(puntoReferencia1), CrearPuntoRefDTO.class);
        crearPuntoRefDTO.setTipoPunto(puntoReferencia1.getTipoPunto().getTipoNombre());
        crearPuntoRefDTO.setRuta(puntoReferencia1.getRuta().getRutaNombre());

        return crearPuntoRefDTO;
    }

    private List<PuntoReferencia> leerExcel(InputStream inputStream, Ruta ruta) throws IOException {
        List<PuntoReferencia> puntoReferencias = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row: sheet){
            if (row.getRowNum() == 0){
                continue;
            }
            TipoPunto tipoPunto = puntoRepository.findByTipoNombre(row.getCell(0).getStringCellValue().toUpperCase());
            String kmAnt = String.valueOf((int) row.getCell(2).getNumericCellValue());
            String kmSig = String.valueOf((int) row.getCell(3).getNumericCellValue());
            String cantRem = String.valueOf((int) row.getCell(4).getNumericCellValue());

            PuntoReferencia puntoReferencia = new PuntoReferencia();
            puntoReferencia.setTipoPunto(tipoPunto);
            puntoReferencia.setPuntoNombre(row.getCell(1).getStringCellValue().toUpperCase());
            puntoReferencia.setRuta(ruta);
            puntoReferencia.setKmAnterior(kmAnt);
            puntoReferencia.setKmSiguiente(kmSig);
            puntoReferencia.setCantRemanente(cantRem);
            puntoReferencia.setLongitud(row.getCell(5).getStringCellValue());
            puntoReferencia.setLatitud(row.getCell(6).getStringCellValue());

            puntoReferencias.add(puntoReferencia);
        }

        workbook.close();
        inputStream.close();

        return puntoReferencias;

    }

    @Override
    public CrearRutaDTO guardarRuta(CrearRutaDTO rutaDTO) {

        String rutaN = rutaDTO.getRutaInicio()+" - "+rutaDTO.getRutaFin();
        String rutaM = rutaN.toUpperCase();

        Ruta rutaB = rutaRepository.findByRutaNombre(rutaM);
        if (rutaB != null){
            throw new MyException("Ya esta registrada esta ruta");
        }

        Ruta ruta = new Ruta();
        ruta.setRutaNombre(rutaM);

        rutaRepository.save(ruta);

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

}
