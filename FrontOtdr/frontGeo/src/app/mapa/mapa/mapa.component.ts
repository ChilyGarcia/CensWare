import { Component } from '@angular/core';
import { Map, tileLayer } from 'leaflet';

declare function holaMundo() :void; 
  


import * as L from 'leaflet';

@Component({
  selector: 'app-mapa',
  templateUrl: './mapa.component.html',
  styleUrls: ['./mapa.component.css']
})
export class MapaComponent {

  ngOnInit()
  {
    holaMundo();
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
