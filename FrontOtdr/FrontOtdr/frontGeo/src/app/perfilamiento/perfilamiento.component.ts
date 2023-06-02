import { Component } from '@angular/core';
import { Perfilamiento } from '../perfilamiento';
import { UsuarioService } from '../services/usuario.service';
import { LoginServiceService } from '../services/login-service.service';

@Component({
  selector: 'app-perfilamiento',
  templateUrl: './perfilamiento.component.html',
  styleUrls: ['./perfilamiento.component.css'],
})
export class PerfilamientoComponent {
  constructor(
    public usuarioService: UsuarioService,
    public loginService: LoginServiceService
  ) {}

  cuerpo = {
    perfil: '',
    maps: true,
    caracterizacion: true,
    dashboard: true,
    fallo: true,
    userLogeado: 'adminFesc@fesc.edu.co',
  };

  listaPerfil: Perfilamiento[];

  ngOnInit(): void {
    this.obtenerListaPerfiles();
  }

  actualizarPerfil(
    perfilNombre: string,
    maps: boolean,
    caracterizacion: boolean,
    dashboard: boolean,
    fallo: boolean
  ) {
    this.cuerpo.perfil = perfilNombre;
    this.cuerpo.maps = maps;
    this.cuerpo.caracterizacion = caracterizacion;
    this.cuerpo.dashboard = dashboard;
    this.cuerpo.fallo = fallo;

    console.log(this.cuerpo.perfil);
    console.log(this.cuerpo.maps);
    console.log(this.cuerpo.caracterizacion);
    console.log(this.cuerpo.dashboard);
    console.log(this.cuerpo.fallo);
    console.log(this.cuerpo.userLogeado);

    this.usuarioService.actualizarPerfil(this.cuerpo).subscribe((dato) => {
      console.log(dato);
    });
  }

  obtenerListaPerfiles() {
    return this.usuarioService.obtenerListaPerfiles().subscribe((dato) => {
      this.listaPerfil = dato;
    });
  }
}
