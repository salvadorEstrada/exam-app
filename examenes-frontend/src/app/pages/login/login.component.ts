import { AsyncPipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit { 

  loginData={
    "username": '',
    "password": ''

  }

  constructor(private snack: MatSnackBar, private loginService: LoginService, private router: Router) { }

  ngOnInit(): void { 

  }

    formSubmit(){ 
      if(this.loginData.username.trim()=='' || this.loginData.username==null){
        this.snack.open('El nombre de usuario es requerido!','Aceptar',{
          duration: 3000
        });
        return;
      } 

      if(this.loginData.password.trim()=='' || this.loginData.password==null){
        this.snack.open('El password es requerido!','Aceptar',{
          duration: 3000
        });
        return;
      }

      this.loginService.generateToken(this.loginData).subscribe(
        (data:any)=>{
          console.log(data);

          this.loginService.loginUser(data.token);
          this.loginService.getCurentUser().subscribe((user: any)=>{
            this.loginService.setUser(user)
            console.log(user);
            if(this.loginService.getUserRole()== "ADMIN"){
              //Dashboard del arministrador
              //window.location.href='/admin';  
              this.router.navigate(['admin']); 
              this.loginService.loginStatusSubject.next(true);

            }
            else if(this.loginService.getUserRole()== "STANDAR"){
              //user-dashboard 
              //window.location.href='/user-dashboard';
              this.router.navigate(['user-dashboard/0']);
              this.loginService.loginStatusSubject.next(true);
            }
            else{
              this.loginService.logOut();
            }
          })
        },(error)=>{
          console.log(error);
          this.snack.open('Detalles inválidos, intente más tarde!', 'Aceptar', {
            duration:3000
          });
        }
      )
    } 

}  

    



