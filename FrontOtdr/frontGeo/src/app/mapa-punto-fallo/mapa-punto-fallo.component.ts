import { Component } from '@angular/core';
import { UsuarioService } from '../services/usuario.service';
import { LoginServiceService } from '../services/login-service.service';

declare function mapaFallo(): void;

@Component({
  selector: 'app-mapa-punto-fallo',
  templateUrl: './mapa-punto-fallo.component.html',
  styleUrls: ['./mapa-punto-fallo.component.css'],
})
export class MapaPuntoFalloComponent {
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
    mapaFallo();
  }
}
