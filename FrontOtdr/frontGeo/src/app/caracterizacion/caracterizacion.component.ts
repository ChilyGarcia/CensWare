import { Component } from '@angular/core';
import { UsuarioService } from '../services/usuario.service';

declare function holaMundo() :void; 

@Component({
  selector: 'app-caracterizacion',
  templateUrl: './caracterizacion.component.html',
  styleUrls: ['./caracterizacion.component.css']
})
export class CaracterizacionComponent {

  constructor(private usuarioService:UsuarioService)
  {

  }

  caracterizacion ={
    ruta:'',
    tipoPunto:'',
    nombrePunto:'',
    cantRemanente:'',
    longitud:'',
    latitud:'',
    medicion:true,
    

  }


  ngOnInit()
  {
    //holaMundo();
  }
  
  formSubmit()
  {
    
  }

  enviarCaracterizacion(){
    

    console.log(this.caracterizacion.nombrePunto);

    this.usuarioService.caracterizacion(this.caracterizacion).subscribe(
      (data) =>{
        console.log(data);
      }
    )
  }
}
