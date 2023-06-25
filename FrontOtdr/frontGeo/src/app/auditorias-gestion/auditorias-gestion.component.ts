import { Component } from '@angular/core';
import { AuditoriasGestion } from '../auditorias-gestion';
import { UsuarioService } from '../services/usuario.service';

@Component({
  selector: 'app-auditorias-gestion',
  templateUrl: './auditorias-gestion.component.html',
  styleUrls: ['./auditorias-gestion.component.css']
})
export class AuditoriasGestionComponent {

  constructor(private usuarioService:UsuarioService){

  }

  listaAuditorias : AuditoriasGestion[];

  ngOnInit(){

    this.obtenerListaGestion();

  }

  obtenerListaGestion()
  {
    return this.usuarioService.obtenerListaAuditoriasGestion().subscribe((dato) =>{
      this.listaAuditorias = dato;
      console.log(dato);
    })

  }

}
