import { Component } from '@angular/core';

declare function holaMundo(lista: any): void;

import { Puntos } from 'src/app/puntos';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-mapa',
  templateUrl: './mapa.component.html',
  styleUrls: ['./mapa.component.css'],
})
export class MapaComponent {
  listaPuntos: Puntos[];

  ruta = {
    ruta:''
  }

  constructor(public userService: UsuarioService) {}

  ngOnInit() {

  }

  envioRuta(){

    console.log(this.ruta.ruta)

    this.userService.obtenerListaPuntos(this.ruta.ruta).subscribe((dato) => {
      this.listaPuntos = dato;

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
}

export function getMyList() {
  const myList = ['elemento1', 'elemento2', 'elemento3'];
  return myList;
}
