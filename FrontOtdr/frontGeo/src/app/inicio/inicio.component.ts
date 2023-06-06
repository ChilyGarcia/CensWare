import { Component, OnInit } from '@angular/core';
import { LoginServiceService } from '../services/login-service.service';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit{

  constructor(public loginService:LoginServiceService)
  {

  }  
  ngOnInit()
  {
    console.log(this.loginService.getUserRol());
  }

  formSubmit()
  {
    
  }

}
