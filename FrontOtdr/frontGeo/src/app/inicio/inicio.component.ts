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
  listaFallaComun: any[];

  listaRutas: any[];
  listaCantidadFallas: any[];

  listaNombreFallaComun: any[];
  listaCantidadFallaComun: any[];

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
            labels: this.listaRutas,
            datasets: [
              {
                label: 'Registro de fallas por ruta',
                data: this.listaCantidadFallas,
                backgroundColor: 'rgba(0, 123, 255, 0.5)',
                borderColor: 'rgba(0, 123, 255, 1)',
                borderWidth: 1,
              },
            ],
          };

          var myBarChart = new Chart(context, {
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

      this.userService.obtenerDatosTipoFallaComun().subscribe((dato) => {
        this.listaFallaComun = dato;

        console.log(this.listaFallaComun);

        this.listaNombreFallaComun = [];
        this.listaCantidadFallaComun = [];

        for (let i = 0; i < this.listaFallaComun.length; i++) {
          this.listaNombreFallaComun.push(this.listaFallaComun[i][0]);
          this.listaCantidadFallaComun.push(this.listaFallaComun[i][1]);
        }

        console.log('-----------------------');
        console.log(this.listaNombreFallaComun);
        console.log(this.listaCantidadFallaComun);

        var ctx2 = document.getElementById(
          'myChart2'
        ) as HTMLCanvasElement | null;
        if (ctx2) {
          var context2 = ctx2.getContext('2d');
          if (context2) {
            var data2 = {
              labels: this.listaNombreFallaComun,
              datasets: [
                {
                  label: 'Cantidad: ',
                  data: this.listaCantidadFallaComun,
                  backgroundColor: [
                    'rgba(255, 99, 132, 0.5)',
                    'rgba(54, 162, 235, 0.5)',
                  ],
                  borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                  ],
                  borderWidth: 1,
                },
              ],
            };

            var myPieChart = new Chart(context2, {
              type: 'pie',
              data: data2,
              options: {
                responsive: true,
              },
            });
          }
        }
      });
    });
  }

  formSubmit() {}
}
