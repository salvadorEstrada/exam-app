import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/internal/Subject';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class LoginService { 


  public  loginStatusSubject =  new Subject<boolean>();


  constructor(private http: HttpClient) { } 

  //Generamos mel token 

  public generateToken(loginData: any){
    return this.http.post(`${baseUrl}/generate-token`,loginData);
                     
  } 

  //Inicaimos sesión y establecemos el token en le local storage 

  public loginUser(token: any){
    localStorage.setItem('token',token);
  }

  //Comprobar que está en el local storage
  public isLoggedIn(){
    let tokenStr=localStorage.getItem('token'); 

    if(tokenStr==undefined || tokenStr==''|| tokenStr==null){
      return false;
    }else{
      return true; //token en el localStorage
    }
  } 

  //Cerramos sesión y eliminamos el token del local Storage
  public logOut(){
     localStorage.removeItem('token');
     localStorage.removeItem('user');
     return true;
  }

  //Obtenemos el token 

  public getToken(){
     return localStorage.getItem('token');
  }

  //Establcer un usuario

  public setUser(user:any){
    localStorage.setItem('user' ,JSON.stringify(user));
  }


  public getUser(){
    let userStr = localStorage.getItem('user');

    if(userStr !=null ){
      return JSON.parse(userStr);
    }else{
      this.logOut();
      return null;
    }
  }



  
  public getUserRole(){
     let user= this.getUser();
     return  user.authorities[0].authority;
  } 


  public getCurentUser(){
    return this.http.get(`${baseUrl}/usuario-actual`);
  }


}
