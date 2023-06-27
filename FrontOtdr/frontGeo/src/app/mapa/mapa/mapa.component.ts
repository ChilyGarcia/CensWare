import { Component } from '@angular/core';

declare function holaMundo(lista: any): void;

import { Puntos } from 'src/app/puntos';
import { Rutas } from 'src/app/rutas';
import { LoginServiceService } from 'src/app/services/login-service.service';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-mapa',
  templateUrl: './mapa.component.html',
  styleUrls: ['./mapa.component.css'],
})
export class MapaComponent {
  listaRutas: Rutas[];

  permissions ={
    maps:false,
    caracterizacion:false,
    fallo:false,
    dashboard:false

  }

  listaPuntos: Puntos[];

  ruta = {
    ruta:''
  }

  constructor(public userService: UsuarioService, public loginService:LoginServiceService) {}

  ngOnInit() {

    let user = this.loginService.getUser();

    let permisoMaps = user.maps;
    let permisoCaracterizacion = user.caracterizacion;
    let permisoFallo = user.fallo;
    let permisoDashboard = user.dashboard;

    this.permissions.maps = permisoMaps;
    this.permissions.caracterizacion = permisoCaracterizacion;
    this.permissions.fallo = permisoFallo;
    this.permissions.dashboard = permisoDashboard;

    console.log(this.permissions.maps);
    console.log(this.permissions.caracterizacion);
    console.log(this.permissions.fallo);
    console.log(this.permissions.dashboard);

    this.obtenerListaRutas();

  }

  envioRuta(){

    console.log(this.ruta.ruta)

    this.userService.obtenerListaPuntos(this.ruta.ruta).subscribe((dato) => {
      this.listaPuntos = dato;

      console.log(this.listaPuntos);

      for (let i = 0; i < this.listaPuntos.length; i++) {
        // console.log(this.listaPuntos[i].longitud);
        // console.log(this.listaPuntos[i].latitud);
      }

      holaMundo(this.listaPuntos);
    });

  }

  ngAfterViewInit(): void {
    /*
    const map = L.map('map').setView([4.708507228806381, -74.08241517458728], 5);
  
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors',
      maxZoom: 18,
    }).addTo(map);

    */
  }

  obtenerListaRutas() {
    this.userService.obtenerListaRutas().subscribe((dato) => {
      this.listaRutas = dato;

      console.log(dato);
    });
  }
}

export function getMyList() {
  const myList = ['elemento1', 'elemento2', 'elemento3'];
  return myList;
}
