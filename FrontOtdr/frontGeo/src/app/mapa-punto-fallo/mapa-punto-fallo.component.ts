import { Component } from '@angular/core';
import { UsuarioService } from '../services/usuario.service';
import { LoginServiceService } from '../services/login-service.service';
import { PuntoFallo } from '../punto-fallo';
import { LngLt } from '../lng-lt';

declare function mapaFallo(lista: any): void;

@Component({
  selector: 'app-mapa-punto-fallo',
  templateUrl: './mapa-punto-fallo.component.html',
  styleUrls: ['./mapa-punto-fallo.component.css'],
})
export class MapaPuntoFalloComponent {
  listaLnglt: LngLt[] = [];

  /*
   = [
    {latitud:123213123, longitud:12123123, mensaje:"", nombre:""}
  ]

  */

  
  fallo : string = ''


  permissions = {
    maps: false,
    caracterizacion: false,
    fallo: false,
    dashboard: false,
  };

  constructor(
    public userService: UsuarioService,
    public loginService: LoginServiceService
  ) {}

  

  ngOnInit() {

    this.fallo = localStorage.getItem('lugFalla')?.toLocaleLowerCase() || '';
    this.fallo = this.fallo.charAt(0).toUpperCase() + this.fallo.substring(1)
    

    const lt1 = localStorage.getItem('lt1') || '0';
    const lt1Number = parseFloat(lt1);

    const lng1 = localStorage.getItem('lng1') || '0';
    const lng1Number = parseFloat(lng1);

    const lt2 = localStorage.getItem('lt2') || '0';
    const lt2Number = parseFloat(lt2);

    const lng2 = localStorage.getItem('lng2') || '0';
    const lng2Number = parseFloat(lng2);

    const nombre1 = localStorage.getItem("nombre1") || '0';
    const nombre2 = localStorage.getItem("nombre2") || '0';

    if(lt1 != null && lng1 != null && lt2 != null && lng2 != null) {
      const dato1 = { latitud: lt1Number, longitud: lng1Number, nombre:nombre1 };
      const dato2 = { latitud: lt2Number, longitud: lng2Number, nombre:nombre2};

      this.listaLnglt.push(dato1);
      this.listaLnglt.push(dato2);
    }

    console.log(this.listaLnglt);

    let user = this.loginService.getUser();

    let permisoMaps = user.maps;
    let permisoCaracterizacion = user.caracterizacion;
    let permisoFallo = user.fallo;
    let permisoDashboard = user.dashboard;

    this.permissions.maps = permisoMaps;
    this.permissions.caracterizacion = permisoCaracterizacion;
    this.permissions.fallo = permisoFallo;
    this.permissions.dashboard = permisoDashboard;

    console.log(this.permissions.maps);
    console.log(this.permissions.caracterizacion);
    console.log(this.permissions.fallo);
    console.log(this.permissions.dashboard);
    mapaFallo(this.listaLnglt);
  }
}
