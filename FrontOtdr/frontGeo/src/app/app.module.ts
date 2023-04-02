import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PanelComponent } from './panel/panel/panel.component';
import { MapaComponent } from './mapa/mapa/mapa.component';
import { LoginComponent } from './login/login/login.component';
import { DibujarComponent } from './dibujar/dibujar.component';
import { InicioComponent } from './inicio/inicio.component';

@NgModule({
  declarations: [
    AppComponent,
    PanelComponent,
    MapaComponent,
    LoginComponent,
    DibujarComponent,
    InicioComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
