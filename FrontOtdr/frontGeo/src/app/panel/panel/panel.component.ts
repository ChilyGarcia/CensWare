import { Component, OnInit } from '@angular/core';
import { LoginServiceService } from 'src/app/services/login-service.service';

@Component({
  selector: 'app-panel',
  templateUrl: './panel.component.html',
  styleUrls: ['./panel.component.css'],
})
export class PanelComponent implements OnInit {

  usuario:string = '';

  permissions ={
    maps:false,
    caracterizacion:false,
    fallo:false,
    dashboard:false

  }

  constructor(public loginService: LoginServiceService) {}

  ngOnInit(): void {
    let user = this.loginService.getUser();

    this.usuario = user.email;

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
    

    console.log()
  }
}
