import { Component, OnInit } from '@angular/core';
import { LoginServiceService } from 'src/app/services/login-service.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{



  loginData = {
    email: '',
    password: '',
    rol: "ADMIN",
  };

  constructor(private loginService:LoginServiceService, private snack:MatSnackBar)
  {

  }

  ngOnInit(): void {
      console.log("Inicio de la aplicacion");

      this.loginService.logout();
  }


  formSubmit()
  {

    
    this.loginService.generateToken(this.loginData).subscribe(
      (data: any) => {
        console.log(data);

        this.loginService.loginUser(data.token);
        this.loginService
          .getCurrentUser(this.loginData)
          .subscribe((user: any) => {
            this.loginService.setUsuario(user);
            console.log(user);
            //close();
            //open("/admin");

            window.location.href = '/inicio';
          });
      },
      (error) => {
        console.log(error);

        this.snack.open('Las credenciales no son validas', 'Aceptar', {
          duration: 5000,
          verticalPosition: 'top',
          horizontalPosition: 'center',
        });
      }
    );
    

  }

}
