import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit { 

   //Para evitar el error ERROR TypeError: Cannot read properties of null (reading 'username') 
  isLoggedIn=false; 
  user: any =null;

  constructor(public login: LoginService) { }

  ngOnInit(): void { 
    this.isLoggedIn = this.login.isLoggedIn();
    this.user=this.login.getUser()
    this.login.loginStatusSubject.asObservable().subscribe(
      data =>{
        this.isLoggedIn= this.login.isLoggedIn();
        this.user=this.login.getUser();
      }
    )
  } 
   //Cerrar la sesión
  public logout(){
    this.login.logOut(); 
    window.location.reload();
  }
}
