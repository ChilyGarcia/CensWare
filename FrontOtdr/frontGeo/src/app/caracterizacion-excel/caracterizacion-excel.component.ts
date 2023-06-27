import { Component } from '@angular/core';
import { Rutas } from '../rutas';
import { UsuarioService } from '../services/usuario.service';
import { LoginServiceService } from '../services/login-service.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-caracterizacion-excel',
  templateUrl: './caracterizacion-excel.component.html',
  styleUrls: ['./caracterizacion-excel.component.css'],
})
export class CaracterizacionExcelComponent {
  constructor(
    private usuarioService: UsuarioService,
    private loginService: LoginServiceService,
    private httpClient: HttpClient
  ) {}

  formData = new FormData();

  selectedFileName: string = '';

  caracterizacion = {
    ruta: '',
    email: this.loginService.getUser().email,
  };

  listaRutas: Rutas[];

  formSubmit() {}

  ngOnInit() {
    this.obtenerListaRutas();
  }

  enviarRegistroCaracterizacion() {
    this.formData.append('plato', JSON.stringify(this.caracterizacion));

    //Realizamos la petición a SpringBoot
    this.httpClient
      .post<any>(
        'http://localhost:8080/caracterizacion/puntoref-auto-save/' +
          this.caracterizacion.ruta +
          '/' +
          this.caracterizacion.email,
        this.formData
      )
      .subscribe((data) => {
        //En este punto nuestra petición ha funcionado correctamente
        console.log(data);
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
