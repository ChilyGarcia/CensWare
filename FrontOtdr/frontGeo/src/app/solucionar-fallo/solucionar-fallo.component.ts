import { Component, ViewChild } from '@angular/core';
import { Rutas } from '../rutas';
import { UsuarioService } from '../services/usuario.service';
import { LoginServiceService } from '../services/login-service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-solucionar-fallo',
  templateUrl: './solucionar-fallo.component.html',
  styleUrls: ['./solucionar-fallo.component.css'],
})
export class SolucionarFalloComponent {
  @ViewChild('formRef') formRef: any;

  constructor(
    private userService: UsuarioService,
    private loginService: LoginServiceService
  ) {}
  listaRutas: Rutas[];


  containsNumbers(word: string): boolean {
    
    const pattern = /^[\d.]+$/;
    console.log(pattern.test(word))
    return pattern.test(word);
  }

  cuerpo = {
    ruta: '',
    nombreP: 0,
    mensaje: '',
    descripcion: '',
    remutilizado: '',
    email: this.loginService.getUser().email,
    fallo: '',
  };

  ngOnInit() {
    this.obtenerListaRutas();
  }

  formSubmit() {
    console.log(this.cuerpo.ruta);
    console.log(this.cuerpo.nombreP);
    console.log(this.cuerpo.mensaje);
    console.log(this.cuerpo.descripcion);
    console.log(this.cuerpo.remutilizado);
    console.log(this.cuerpo.email);
    console.log(this.cuerpo.fallo);

    this.solucionarFallo();
    this.formRef.resetForm();
    window.location.href = "auditoria-fallo";

  }

  solucionarFallo() {
    return this.userService.solucionarFalla(this.cuerpo).subscribe((dato) => {
      console.log(dato);

      Swal.fire({
        title: 'Ruta creada con éxito',
        text: 'Se ha creado la ruta correctamente',
        icon: 'success',
        confirmButtonText: 'Aceptar',
      });
    });

    
  }

  obtenerListaRutas() {
    this.userService.obtenerListaRutas().subscribe((dato) => {
      this.listaRutas = dato;

      console.log(dato);
    });
  }
}
