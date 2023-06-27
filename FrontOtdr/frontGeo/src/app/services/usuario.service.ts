import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';
import { Observable } from 'rxjs';
import { Perfilamiento } from '../perfilamiento';
import { Puntos } from '../puntos';
import { Rutas } from '../rutas';
import { AuditoriasGestion } from '../auditorias-gestion';
import { Fallas } from '../fallas';
import { ListaGraficaEstadistica } from '../lista-grafica-estadistica';
import { FallaComun } from '../falla-comun';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private httpClient:HttpClient) { }

  //Añadir usuarios nuevos a la plataforma
  public añadirUsuario(user:any)
  {
    return this.httpClient.post(`${baseUrl}/usuario/agregar`, user);
  }

  public caracterizacion(caracterizacion:any)
  {
    return this.httpClient.post(`${baseUrl}/caracterizacion/puntoref-manual-save`, caracterizacion);

  }

  public obtenerListaPerfiles(): Observable<Perfilamiento[]>
  {

    return this.httpClient.get<Perfilamiento[]>(`${baseUrl}/usuario/perfiles-list`)
  }
   

  public actualizarPerfil(cuerpo:any)
  {
    return this.httpClient.post(`${baseUrl}/usuario/permisos-edit`,cuerpo);

  }

  public obtenerListaPuntos(ruta:string): Observable<Puntos[]>
  {

    return this.httpClient.get<Puntos[]>(`${baseUrl}/mostrar/puntos-ruta/`+ ruta);
  }

  public crearNuevoPerfil(cuerpo:any)
  {
    return this.httpClient.post(`${baseUrl}/usuario/perfil-save`,cuerpo);
  }

  public crearRuta(cuerpo:any)
  {
    return this.httpClient.post(`${baseUrl}/caracterizacion/ruta-save`,cuerpo);

  }

  public obtenerListaRutas(): Observable<Rutas[]>
  {

    return this.httpClient.get<Rutas[]>(`${baseUrl}/caracterizacion/ruta-list`)
  }


  public obtenerListaAuditoriasGestion(): Observable<AuditoriasGestion[]>
  {

    return this.httpClient.get<AuditoriasGestion[]>(`${baseUrl}/usuario/auditoria-gestion`)
  }

  public solucionarFalla(cuerpo:any)
  {
    return this.httpClient.post(`${baseUrl}/falla/soluc-falla`,cuerpo);

  }
  
  public obtenerListaFallos(): Observable<Fallas[]>
  {

    return this.httpClient.get<Fallas[]>(`${baseUrl}/usuario/auditoria-falla`)
  }

  public obtenerDatosGrafica(): Observable<ListaGraficaEstadistica[]>
  {

    return this.httpClient.get<ListaGraficaEstadistica[]>(`${baseUrl}/usuario/auditoria-falla-trimestre`)
  }

  public obtenerDatosTipoFallaComun(): Observable<FallaComun[]>{
    return this.httpClient.get<FallaComun[]>(`${baseUrl}/usuario/tipos-fallos-comunes`)
  }

  

  
}
