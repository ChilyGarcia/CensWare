package com.otdr.otdr.Models.Peticiones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class SolucionFalloRequest {

    private String ruta;
    private int nombreP; //nombre del primer punto, desde donde va la falla
    private String mensaje; //mensaje que se le envia cuando calcula la falla
    private String descripcion; //Descripcion de la solucion. La ingresa el usuario
    private String remutilizado; //Cantidad de remanente utilizado
    private String email; //Email del usuario logeado
    private String tFallo; //tipo de fallo que soluciono

}
