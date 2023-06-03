import { Component } from '@angular/core';
import { UsuarioService } from '../services/usuario.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-agregar-perfil',
  templateUrl: './agregar-perfil.component.html',
  styleUrls: ['./agregar-perfil.component.css'],
})
export class AgregarPerfilComponent {
  formulario = {
    nombrePerfilForm: '',
    mapsForm: '',
    caracterizacionForm: '',
    falloForm: '',
    dashboardForm: '',
    userLogeadoForm: 'adminFesc@fesc.edu.co',
  };

  cuerpo = {
    nombrePerfil:'',
    maps:false,
    caracterizacion:false,
    fallo:false,
    dashboard:false,
    userLogeado: 'adminFesc@fesc.edu.co'
  }

  constructor(public userService: UsuarioService) {}

  formSubmit()
  {

    this.cuerpo.nombrePerfil = this.formulario.nombrePerfilForm;

    if(this.formulario.mapsForm == "SI"){
      this.cuerpo.maps = true;
    }else if(this.formulario.mapsForm == "NO"){
      this.cuerpo.maps = false;
    }

    if(this.formulario.caracterizacionForm == "SI"){
      this.cuerpo.caracterizacion = true;
    } else if(this.formulario.caracterizacionForm == "NO"){
      this.cuerpo.caracterizacion = false;
    }

    if(this.formulario.falloForm== "SI"){
      this.cuerpo.fallo = true;
    }else if(this.formulario.falloForm == "NO"){
      this.cuerpo.fallo = false;
    }

    if(this.formulario.dashboardForm == "SI"){
      this.cuerpo.dashboard = true;
    } else if(this.formulario.dashboardForm == "NO"){
      this.cuerpo.dashboard = false;
    }

    console.log(this.cuerpo.nombrePerfil);
    console.log(this.cuerpo.maps);
    console.log(this.cuerpo.caracterizacion);
    console.log(this.cuerpo.fallo);
    console.log(this.cuerpo.dashboard);


    this.userService.crearNuevoPerfil(this.cuerpo).subscribe((dato) =>{
      console.log(dato);

      Swal.fire({
        title: 'Perfil creado',
        text: 'Se ha creado un nuevo perfil con éxito',
        icon: 'success',
        confirmButtonText: 'Aceptar'
      });
    })

  }
}
