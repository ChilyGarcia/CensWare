import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MapaComponent } from './mapa/mapa/mapa.component';
import { LoginComponent } from './login/login/login.component';
import { PanelComponent } from './panel/panel/panel.component';
import { InicioComponent } from './inicio/inicio.component';
import { AgregarUsuarioComponent } from './agregar-usuario/agregar-usuario.component';
import { PerfilamientoComponent } from './perfilamiento/perfilamiento.component';
import { CapturaComponent } from './captura/captura.component';
import { CaracterizacionComponent } from './caracterizacion/caracterizacion.component';
import { AgregarPerfilComponent } from './agregar-perfil/agregar-perfil.component';
import { Path } from 'leaflet';
import { RutasComponent } from './rutas/rutas.component';
import { RegistroFallaComponent } from './registro-falla/registro-falla.component';
import { AuditoriasGestionComponent } from './auditorias-gestion/auditorias-gestion.component';
import { SolucionarFalloComponent } from './solucionar-fallo/solucionar-fallo.component';
import { AuditoriaFalloComponent } from './auditoria-fallo/auditoria-fallo.component';

const routes: Routes = [
  {
    path:'',
    component:LoginComponent,
    pathMatch:'full'
  },
  {
    path:'mapa',
    component:MapaComponent,
    pathMatch:'full'

  },
  {
    path:'agregar-usuarios',
    component:AgregarUsuarioComponent,
    pathMatch:'full'
  },
  {
    path:'perfilamiento',
    component:PerfilamientoComponent,
    pathMatch:'full'
  },
  {
    path:'inicio',
    component:InicioComponent,
    pathMatch:'full'
  },
  {
    path:'captura',
    component:CapturaComponent,
    pathMatch:'full'
  },
  {
    path:'caracterizacion',
    component:CaracterizacionComponent,
    pathMatch:'full'
  },
  {
    path:'agregar-perfil',
    component:AgregarPerfilComponent,
    pathMatch:'full'
  },
  {
    path:'rutas',
    component:RutasComponent,
    pathMatch:'full'
  },
  {
    path:'registro-fallas',
    component:RegistroFallaComponent,
    pathMatch:'full'
  },
  {
    path:'auditorias-gestion',
    component:AuditoriasGestionComponent,
    pathMatch:'full'
  },
  {
    path:'solucionar-fallo',
    component:SolucionarFalloComponent,
    pathMatch:'full'
  },
  {
    path:'auditoria-fallo',
    component:AuditoriaFalloComponent,
    pathMatch:'full'
  }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
