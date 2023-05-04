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
import { MatSelectModule } from '@angular/material/select';
import { FormsModule } from '@angular/forms';
import { PerfilamientoComponent } from './perfilamiento/perfilamiento.component';
import { CapturaComponent } from './captura/captura.component';
import { CaracterizacionComponent } from './caracterizacion/caracterizacion.component';



@NgModule({
  declarations: [
    AppComponent,
    PanelComponent,
    MapaComponent,
    LoginComponent,
    DibujarComponent,
    InicioComponent,
    AgregarUsuarioComponent,
    PerfilamientoComponent,
    CapturaComponent,
    CaracterizacionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, 
    HttpClientModule, 
    BrowserAnimationsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatSelectModule

    
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
