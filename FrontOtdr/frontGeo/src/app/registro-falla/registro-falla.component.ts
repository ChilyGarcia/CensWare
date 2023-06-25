import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import Swal from 'sweetalert2';
import { Rutas } from '../rutas';
import { UsuarioService } from '../services/usuario.service';

@Component({
  selector: 'app-registro-falla',
  templateUrl: './registro-falla.component.html',
  styleUrls: ['./registro-falla.component.css'],
})
export class RegistroFallaComponent {
  listaRutas: Rutas[];

  formData = new FormData();

  registroFalla = {
    ruta: '',
    punto: '',
  };

  constructor(private httpClient: HttpClient, private usuarioService: UsuarioService) {}

  ngOnInit()
  {
    this.obtenerListaRutas();
  }


  formSubmit() {}

  enviarRegistroFalla() {
    this.formData.append('plato', JSON.stringify(this.registroFalla));

    //Realizamos la petición a SpringBoot
    this.httpClient
      .post<any>(
        'http://localhost:8080/falla/calcular/' +
          this.registroFalla.ruta +
          '/' +
          this.registroFalla.punto,
        this.formData
      )
      .subscribe((data) => {
        //En este punto nuestra petición ha funcionado correctamente
        Swal.fire({
          title: '<strong>El plato ha sido creado</strong>',
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
      });

      window.location.href = "solucionar-fallo";
  }

  onFileSelected(event: any) {
    const file: File = event.target.files[0];

    this.formData.append('file', file);
  }

  obtenerListaRutas() {
    this.usuarioService.obtenerListaRutas().subscribe((dato) => {
      this.listaRutas = dato;
    });
  }
}
