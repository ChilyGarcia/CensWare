import { Component, OnInit } from '@angular/core';
import { LoginServiceService } from '../services/login-service.service';
import { Chart } from 'chart.js/auto';
import { ListaGraficaEstadistica } from '../lista-grafica-estadistica';
import { UsuarioService } from '../services/usuario.service';

declare function graficasEstadisticas(): any;

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css'],
})
export class InicioComponent implements OnInit {
  listaDatosGratica: any[];

  listaRutas: any[];
  listaCantidadFallas: any[];

  constructor(
    public loginService: LoginServiceService,
    private userService: UsuarioService
  ) {}

  ngOnInit() {
    this.obtenerDatosEstadisticos();

    console.log(this.loginService.getUserRol());
  }

  obtenerDatosEstadisticos() {
    this.userService.obtenerDatosGrafica().subscribe((dato: any) => {
      console.log(dato);

      this.listaDatosGratica = dato;

      this.listaRutas = [];
      this.listaCantidadFallas = [];

      console.log(this.listaDatosGratica.length);
      for (let i = 0; i < this.listaDatosGratica.length; i++) {
        this.listaRutas.push(this.listaDatosGratica[i][0]);
      }

      console.log(this.listaDatosGratica.length);
      for (let i = 0; i < this.listaDatosGratica.length; i++) {
        this.listaCantidadFallas.push(this.listaDatosGratica[i][1]);
      }

      console.log(this.listaRutas);
      console.log(this.listaCantidadFallas);

   
      var ctx = document.getElementById('myChart') as HTMLCanvasElement | null;
      if (ctx) {
        var context = ctx.getContext('2d');
        if (context) {
          var data = {
            labels: this.listaRutas, //dinamico
            datasets: [
              {
                label: 'Registro de fallas por ruta',
                data: this.listaCantidadFallas, //Dinamico
                backgroundColor: 'rgba(0, 123, 255, 0.5)',
                borderColor: 'rgba(0, 123, 255, 1)',
                borderWidth: 1,
              },
            ],
          };

          var myChart = new Chart(context, {
            type: 'bar',
            data: data,
            options: {
              responsive: true,
              scales: {
                y: {
                  beginAtZero: true,
                },
              },
            },
          });
        }
      }
    });
  }

  formSubmit() {}
}
