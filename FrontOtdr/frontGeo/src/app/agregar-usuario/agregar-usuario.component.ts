import { Component } from '@angular/core';
import { UsuarioService } from '../services/usuario.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-agregar-usuario',
  templateUrl: './agregar-usuario.component.html',
  styleUrls: ['./agregar-usuario.component.css']
})
export class AgregarUsuarioComponent {

  constructor(private usuarioServices:UsuarioService)
  {

  }

  public user ={
    nombre:'',
    apellido:'',
    email:'',
    celular:'',
    cedula:'',
    password:'',
    perfil:'',
    userLogeado:'alguien'
  }
  ngOnInit()
  {

    

  }

  formSubmit()
  {

    this.usuarioServices.añadirUsuario(this.user).subscribe(
      (data) =>
      {
        console.log(data);

        Swal.fire({title: '<strong>Usuario registrado con éxito</strong>',
        icon: 'success',
        html:
          '<form (ngSubmit) ="recargar()">'+
          '<button id="but" type="submit" class="btn">'+
          'Hecho'+
          '</button>'+
        '</form>',
        showCloseButton: true,
        showConfirmButton: false,

      });
      }, (error) =>
      {
        console.log(error);
      }
    )

  }

}
