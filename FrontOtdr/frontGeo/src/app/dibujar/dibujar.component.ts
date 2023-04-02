import { Component } from '@angular/core';
import { Map, tileLayer } from 'leaflet';

@Component({
  selector: 'app-dibujar',
  templateUrl: './dibujar.component.html',
  styleUrls: ['./dibujar.component.css']
})
export class DibujarComponent {

  ngAfterViewInit():void{

    const map = new Map('map').setView([4.708507228806381, -74.08241517458728], 5);

    tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
    attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
}).addTo(map);

  }

}
