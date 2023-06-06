import { Component, OnInit } from '@angular/core';
import { LoginServiceService } from 'src/app/services/login-service.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Perfilamiento } from 'src/app/perfilamiento';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{


  listaPerfil: Perfilamiento[];

  loginData = {
    email: '',
    password: '',
    rol: '',
  };

  constructor(private loginService:LoginServiceService, private snack:MatSnackBar, private usuarioServices:UsuarioService)
  {

  }

  ngOnInit(): void {
      console.log("Inicio de la aplicacion");
    
      this.loginService.logout();

      this.obtenerListaPerfiles();
  }


  formSubmit()
  {

    console.log(this.loginData.email)
    console.log(this.loginData.password)
    console.log(this.loginData.rol)

    
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

  obtenerListaPerfiles() {
    return this.usuarioServices.obtenerListaPerfiles().subscribe((dato) => {
      this.listaPerfil = dato;

      console.log(dato);
    });
  }

}
