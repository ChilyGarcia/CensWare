import { Component } from '@angular/core';
import { Fallas } from '../fallas';
import { UsuarioService } from '../services/usuario.service';

@Component({
  selector: 'app-auditoria-fallo',
  templateUrl: './auditoria-fallo.component.html',
  styleUrls: ['./auditoria-fallo.component.css']
})
export class AuditoriaFalloComponent {

  constructor(private userService:UsuarioService){

  }

  listaFallos : Fallas[];

  ngOnInit(){
    this.obtenerListaFallos();

  }

  obtenerListaFallos()
  {
    return this.userService.obtenerListaFallos().subscribe((dato) =>{
      console.log(dato);

      this.listaFallos = dato;
    })
  }

}
