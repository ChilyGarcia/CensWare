import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import Swal from 'sweetalert2';
import { Rutas } from '../rutas';
import { UsuarioService } from '../services/usuario.service';
import { PuntoFallo } from '../punto-fallo';

@Component({
  selector: 'app-registro-falla',
  templateUrl: './registro-falla.component.html',
  styleUrls: ['./registro-falla.component.css'],
})
export class RegistroFallaComponent {
  listaRutas: Rutas[];

  listaPuntoFalla: PuntoFallo[];

  formData = new FormData();

  selectedFileName: string = '';

  registroFalla = {
    ruta: '',
    punto: '',
  };

  constructor(
    private httpClient: HttpClient,
    private usuarioService: UsuarioService
  ) {}

  ngAfterInit() {}

  ngOnInit() {
    this.obtenerListaRutas();

    localStorage.removeItem('lt1');
    localStorage.removeItem('lt2');
    localStorage.removeItem('lng1');
    localStorage.removeItem('lng2');
    localStorage.removeItem("nombre1");
    localStorage.removeItem("nombre2");
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
        console.log(data);

        this.listaPuntoFalla = data;

        const tamaño: number = this.listaPuntoFalla.length;

        if (tamaño == 2) {
          const lt1: string = this.listaPuntoFalla[0].latitud.toString();
          const lng1: string = this.listaPuntoFalla[0].longitud.toString();
          const nombre1: string = this.listaPuntoFalla[0].nombre.toString();

          const lt2: string = this.listaPuntoFalla[1].latitud.toString();
          const lng2: string = this.listaPuntoFalla[1].longitud.toString();
          const nombre2: string = this.listaPuntoFalla[1].nombre.toString();

          console.log(lt1);
          console.log(lng1);
          console.log('- - - -- - - -');
          console.log(lt2);
          console.log(lng2);

          localStorage.setItem('lt1', lt1);
          localStorage.setItem('lng1', lng1);
          localStorage.setItem('nombre1',nombre1 );

          localStorage.setItem('lt2', lt2);
          localStorage.setItem('lng2', lng2);
          localStorage.setItem('nombre2', nombre2);

          window.location.href = 'solucionar-fallo';
        } else if (tamaño == 1) {
          const lt1: string = this.listaPuntoFalla[0].latitud.toString();
          const lng1: string = this.listaPuntoFalla[0].longitud.toString();
          const nombre1: string = this.listaPuntoFalla[0].nombre.toString();

          localStorage.setItem('lt1', lt1);
          localStorage.setItem('lng1', lng1);
          localStorage.setItem('nombre1', nombre1);

          localStorage.setItem('lt2', '0');
          localStorage.setItem('lng2', '0');
          localStorage.setItem('nombre2', '0');

          window.location.href = 'solucionar-fallo';
        }
      });
  }

  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    const files: FileList = event.target.files;
    if (files.length > 0) {
      this.selectedFileName = files[0].name;
    } else {
      this.selectedFileName = '';
    }

    this.formData.append('file', file);
  }

  obtenerListaRutas() {
    this.usuarioService.obtenerListaRutas().subscribe((dato) => {
      this.listaRutas = dato;
    });
  }
}
