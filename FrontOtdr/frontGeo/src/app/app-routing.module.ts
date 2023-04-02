import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MapaComponent } from './mapa/mapa/mapa.component';
import { LoginComponent } from './login/login/login.component';
import { PanelComponent } from './panel/panel/panel.component';
import { InicioComponent } from './inicio/inicio.component';

const routes: Routes = [
  {
    path:'',
    component:InicioComponent,
    pathMatch:'full'
  },
  {
    path:'mapa',
    component:MapaComponent,
    pathMatch:'full'

  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
