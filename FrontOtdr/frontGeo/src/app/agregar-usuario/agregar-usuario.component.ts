import { Component } from '@angular/core';
import { UsuarioService } from '../services/usuario.service';
import Swal from 'sweetalert2';
import { Perfilamiento } from '../perfilamiento';

@Component({
  selector: 'app-agregar-usuario',
  templateUrl: './agregar-usuario.component.html',
  styleUrls: ['./agregar-usuario.component.css'],
})
export class AgregarUsuarioComponent {
  listaPerfil: Perfilamiento[];

  constructor(private usuarioServices: UsuarioService) {}

  public user = {
    nombre: '',
    apellido: '',
    email: '',
    celular: '',
    cedula: '',
    password: '',
    perfil: '',
    userLogeado: 'adminFesc@fesc.edu.co',
  };
  ngOnInit() {
    this.obtenerListaPerfiles();
  }

  formSubmit() {
    
    this.usuarioServices.añadirUsuario(this.user).subscribe(
      (data) => {
        console.log(data);

        Swal.fire({
          title: '<strong>Usuario registrado con éxito</strong>',
          icon: 'success',
          html:
            '<form (ngSubmit) ="recargar()">' +
            '<button id="but" type="submit" class="btn">' +
            'Hecho' +
            '</button>' +
            '</form>',
          showCloseButton: true,
          showConfirmButton: false,
        });
      },
      (error) => {
        console.log(error);
      }
    );

    
  }

  obtenerListaPerfiles() {
    return this.usuarioServices.obtenerListaPerfiles().subscribe((dato) => {
      this.listaPerfil = dato;

      console.log(dato);
    });
  }
}
