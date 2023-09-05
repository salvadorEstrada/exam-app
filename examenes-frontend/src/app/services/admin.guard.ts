import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Route, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate { 

constructor(private loginService: LoginService, private router: Router){

}


  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      if(this.loginService.isLoggedIn() && this.loginService.getUserRole()== 'ADMIN'){
        return true;
      }
      //si lo anterior no es verdad me retorna a la página de inicio, el login
      this.router.navigate(['login']);
      return false;
  }
  
}
