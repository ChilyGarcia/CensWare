import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PanelComponent } from './panel/panel/panel.component';
import { MapaComponent } from './mapa/mapa/mapa.component';
import { LoginComponent } from './login/login/login.component';
import { DibujarComponent } from './dibujar/dibujar.component';
import { InicioComponent } from './inicio/inicio.component';
import { AgregarUsuarioComponent } from './agregar-usuario/agregar-usuario.component';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { PerfilamientoComponent } from './perfilamiento/perfilamiento.component';



@NgModule({
  declarations: [
    AppComponent,
    PanelComponent,
    MapaComponent,
    LoginComponent,
    DibujarComponent,
    InicioComponent,
    AgregarUsuarioComponent,
    PerfilamientoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, 
    HttpClientModule, 
    BrowserAnimationsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule

    
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
