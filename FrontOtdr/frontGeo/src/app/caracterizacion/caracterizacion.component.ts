import { Component } from '@angular/core';
import { UsuarioService } from '../services/usuario.service';
import Swal from 'sweetalert2';

declare function holaMundo(): void;

@Component({
  selector: 'app-caracterizacion',
  templateUrl: './caracterizacion.component.html',
  styleUrls: ['./caracterizacion.component.css'],
})
export class CaracterizacionComponent {
  constructor(private usuarioService: UsuarioService, ) {}

  caracterizacion = {
    ruta: '',
    tipoPunto: '',
    nombrePunto: '',
    cantRemanente: '',
    longitud: '',
    latitud: '',
    medicion: true,
  };

  ngOnInit() {
    //holaMundo();
  }

  formSubmit() {}

  enviarCaracterizacion() {
    console.log(this.caracterizacion.nombrePunto);

    this.usuarioService
      .caracterizacion(this.caracterizacion)
      .subscribe((data) => {
        Swal.fire({
          title: 'Caracterización con éxito',
          text: 'Se ha caracterizado un punto con éxito',
          icon: 'success',
          confirmButtonText: 'Aceptar'
        }).then((result) => {
          // Verificar si el botón "Aceptar" se ha presionado
          if (result.isConfirmed) {
            // Aquí puedes agregar la lógica que deseas ejecutar después de que el usuario hace clic en "Aceptar"
            this.reload();
          }
        });

    

        
      });


  }

  reload()
  {
    location.reload()
  }
}
