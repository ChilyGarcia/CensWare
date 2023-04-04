import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

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
}
