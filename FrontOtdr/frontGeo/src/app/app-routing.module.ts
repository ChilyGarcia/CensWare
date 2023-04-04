import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MapaComponent } from './mapa/mapa/mapa.component';
import { LoginComponent } from './login/login/login.component';
import { PanelComponent } from './panel/panel/panel.component';
import { InicioComponent } from './inicio/inicio.component';
import { AgregarUsuarioComponent } from './agregar-usuario/agregar-usuario.component';
import { PerfilamientoComponent } from './perfilamiento/perfilamiento.component';

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
  }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
