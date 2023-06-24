import { Component, ViewChild } from '@angular/core';
import { UsuarioService } from '../services/usuario.service';
import { LoginServiceService } from '../services/login-service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-rutas',
  templateUrl: './rutas.component.html',
  styleUrls: ['./rutas.component.css'],
})
export class RutasComponent {
  @ViewChild('formRef') formRef: any;

  constructor(
    public LoginService: LoginServiceService,
    public userService: UsuarioService
  ) {}

  cuerpo = {
    rutaInicio: '',
    rutaFin: '',
    userLogeado: this.LoginService.getUser().email,
  };

  formSubmit() {
    this.userService.crearRuta(this.cuerpo).subscribe((dato) => {
      console.log(dato);

      Swal.fire({
        title: 'Ruta creada con éxito',
        text: 'Se ha creado la ruta correctamente',
        icon: 'success',
        confirmButtonText: 'Aceptar',
      });
    });

    this.formRef.resetForm();
  }
}
