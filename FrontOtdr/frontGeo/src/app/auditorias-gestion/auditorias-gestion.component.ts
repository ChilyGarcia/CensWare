import { Component } from '@angular/core';
import { AuditoriasGestion } from '../auditorias-gestion';

@Component({
  selector: 'app-auditorias-gestion',
  templateUrl: './auditorias-gestion.component.html',
  styleUrls: ['./auditorias-gestion.component.css']
})
export class AuditoriasGestionComponent {

  listaAuditorias : AuditoriasGestion[];

}
