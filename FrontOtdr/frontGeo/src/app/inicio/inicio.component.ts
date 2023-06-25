import { Component, OnInit } from '@angular/core';
import { LoginServiceService } from '../services/login-service.service';
import { Chart } from 'chart.js/auto';

// Resto del código...

declare function graficasEstadisticas(): any;

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css'],
})
export class InicioComponent implements OnInit {
  constructor(public loginService: LoginServiceService) {}

  ngOnInit() {
    console.log(this.loginService.getUserRol());

    console.log('Hola graficas');

    var ctx = document.getElementById('myChart') as HTMLCanvasElement | null;

    if (ctx) {
      var context = ctx.getContext('2d');
      if (context) {
        var data = {
          labels: ['Cúcuta - Ocaña', 'Cúcuta - Bochalema', 'Cúcuta - Durania', 'Cúcuta - Srabena', 'Çúcuta - Puerto', 'Cúcuta - Pamplona'], //dinamico
          datasets: [
            {
              label: 'Fallas',
              data: [1200, 1700, 800, 1500, 2000, 1300], //Dinamico
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
  }

  formSubmit() {}
}
