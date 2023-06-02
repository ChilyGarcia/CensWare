import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';
import { Observable } from 'rxjs';
import { Perfilamiento } from '../perfilamiento';
import { Puntos } from '../puntos';

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

  
}
