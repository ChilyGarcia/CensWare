import { Component } from '@angular/core';
import { LoginServiceService } from 'src/app/services/login-service.service';

@Component({
  selector: 'app-panel',
  templateUrl: './panel.component.html',
  styleUrls: ['./panel.component.css']
})
export class PanelComponent {
  constructor(public loginService:LoginServiceService)
  {
    
  }

  salir()
  {

    console.log("aaaaaaaaaaaaaaaaaaa");

  }

}
