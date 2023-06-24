import { Component, ViewChild } from '@angular/core';
import { UsuarioService } from '../services/usuario.service';
import Swal from 'sweetalert2';
import { Rutas } from '../rutas';

declare function holaMundo(): void;

@Component({
  selector: 'app-caracterizacion',
  templateUrl: './caracterizacion.component.html',
  styleUrls: ['./caracterizacion.component.css'],
})
export class CaracterizacionComponent {
  @ViewChild('formRef') formRef: any;

  listaRutas: Rutas[];

  constructor(private usuarioService: UsuarioService) {}

  comprobacion = {
    medicion: '',
  };

  caracterizacion = {
    ruta: '',
    tipoPunto: '',
    nombrePunto: '',
    cantRemanente: '',
    longitud: '',
    latitud: '',
    medicion: false,
  };

  ngOnInit() {
    //holaMundo();

    this.obtenerListaRutas();
  }

  formSubmit() {}

  enviarCaracterizacion() {
    console.log(this.caracterizacion.nombrePunto);

    if (this.comprobacion.medicion == 'SI') {
      this.caracterizacion.medicion = true;
    } else if (this.comprobacion.medicion == 'NO') {
      this.caracterizacion.medicion = false;
    }

    console.log(this.caracterizacion.medicion);

    this.usuarioService
      .caracterizacion(this.caracterizacion)
      .subscribe((data) => {
        Swal.fire({
          title: 'Caracterización con éxito',
          text: 'Se ha caracterizado un punto con éxito',
          icon: 'success',
          confirmButtonText: 'Aceptar',
        }).then((result) => {
          // Verificar si el botón "Aceptar" se ha presionado
          if (result.isConfirmed) {
            // Aquí puedes agregar la lógica que deseas ejecutar después de que el usuario hace clic en "Aceptar"
            this.reload();
          }
        });
      });

      this.formRef.resetForm();
  }

  obtenerListaRutas() {
    this.usuarioService.obtenerListaRutas().subscribe((dato) => {
      this.listaRutas = dato;
    });
  }
  reload() {
    location.reload();
  }
}
