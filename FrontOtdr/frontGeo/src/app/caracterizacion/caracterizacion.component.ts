import { Component } from '@angular/core';

declare function holaMundo() :void; 

@Component({
  selector: 'app-caracterizacion',
  templateUrl: './caracterizacion.component.html',
  styleUrls: ['./caracterizacion.component.css']
})
export class CaracterizacionComponent {

  ngOnInit()
  {
    //holaMundo();
  }
  
  formSubmit()
  {
    
  }

}
